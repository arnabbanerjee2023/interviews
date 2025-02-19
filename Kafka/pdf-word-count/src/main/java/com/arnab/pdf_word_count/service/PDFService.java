package com.arnab.pdf_word_count.service;

import com.arnab.pdf_word_count.common.Constants;
import com.arnab.pdf_word_count.models.PDFModel;
import com.arnab.pdf_word_count.models.WordCountModel;
import com.arnab.pdf_word_count.models.WordsCount;
import com.arnab.pdf_word_count.repository.PDFModelRepo;
import com.arnab.pdf_word_count.repository.WordCountModelRepo;
import com.arnab.pdf_word_count.utils.Utils;
import com.google.gson.Gson;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class PDFService {

    @Autowired
    private PDFModelRepo pdfModelRepo;

    @Autowired
    private WordCountModelRepo wordCountModelRepo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson gson;

    private static final String UPLOAD_DIR = "C:\\Users\\ARNAB\\Documents\\GitHub\\interviews\\Kafka\\TestData";

    public String upload(final String name, final MultipartFile multipartFile) {
        try {
            PDFModel pdfModel = this.getPdfModelDomain(name, this.getFile(multipartFile));
            pdfModelRepo.save(pdfModel);

            WordCountModel wordCountModel = this.getWordCountModelDomainSorted(pdfModel);
            wordCountModelRepo.save(wordCountModel);

            this.publishToKafka(wordCountModel);
            return "SUCCESS";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFile(MultipartFile multipartFile) throws IOException {
        Path path = Path.of(UPLOAD_DIR + multipartFile.getOriginalFilename());
        File file = new File(path.toUri());
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return file;
    }

    private PDFModel getPdfModelDomain(String name, File file) throws IOException {
        PDDocument pdfDocument = Loader.loadPDF(file);
        String text = new PDFTextStripper()
                .getText(pdfDocument)
                .replace("\n", " ")
                .replace("\r", " ");
        return new PDFModel(Utils.getUUID(), name.toUpperCase(), text);
    }

    private WordCountModel getWordCountModelDomainSorted(PDFModel pdfModel) {
        WordCountModel wordCountModel = new WordCountModel(pdfModel.getId(), pdfModel.getName(), new ArrayList<>());

        Arrays.stream(pdfModel.getContent().split(" "))
                .forEach(text -> {
                    String word = text.trim().replaceAll("[^a-zA-Z0-9]", " ");
                    if (wordCountModel.getWordCount()
                            .stream()
                            .noneMatch(eachWord -> eachWord.getWord().equals(word))) {
                        wordCountModel.getWordCount().add(new WordsCount(word, 1));
                    } else {
                        wordCountModel.getWordCount()
                                .stream()
                                .filter(eachWord -> eachWord.getWord().equals(word))
                                .forEach(eachWord -> eachWord.setCount(eachWord.getCount() + 1));
                    }
                });

        wordCountModel.getWordCount()
                .sort((word1, word2) -> -word1.getCount().compareTo(word2.getCount()));
        return wordCountModel;
    }

    private void publishToKafka(WordCountModel wordCountModel) {
        wordCountModel.getWordCount()
                .forEach(word ->
                        kafkaTemplate.send(Constants.WORD_COUNT_TOPIC, Utils.getUUID(), gson.toJson(word)));
    }
}

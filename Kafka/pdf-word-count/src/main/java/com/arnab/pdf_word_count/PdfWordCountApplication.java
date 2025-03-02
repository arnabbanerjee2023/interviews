package com.arnab.pdf_word_count;

import com.arnab.pdf_word_count.repository.PDFModelRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = PDFModelRepo.class)
public class PdfWordCountApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfWordCountApplication.class, args);
	}

}

package com.arnab.pdf_word_count.listener;

import com.arnab.pdf_word_count.common.Constants;
import com.arnab.pdf_word_count.models.WordsCountStore;
import com.arnab.pdf_word_count.repository.WordsCountStoreRepo;
import com.arnab.pdf_word_count.utils.Utils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WordCountListener {

    @Autowired
    private Gson gson;

    @Autowired
    private WordsCountStoreRepo repo;

    @KafkaListener(groupId = Constants.GROUP_ID, topics = Constants.WORD_COUNT_TOPIC)
    public void listener(String message) {
        WordsCountStore store = gson.fromJson(message, WordsCountStore.class);
        store.setId(Utils.getUUID());
        repo.save(store);
    }
}

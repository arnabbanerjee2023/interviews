package com.arnab.pdf_word_count.repository;

import com.arnab.pdf_word_count.models.WordsCountStore;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordsCountStoreRepo extends MongoRepository<WordsCountStore, String> {
}

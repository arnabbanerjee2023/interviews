package com.arnab.pdf_word_count.repository;

import com.arnab.pdf_word_count.models.WordCountModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordCountModelRepo extends MongoRepository<WordCountModel, String> {
}

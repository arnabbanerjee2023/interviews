package com.arnab.pdf_word_count.repository;

import com.arnab.pdf_word_count.models.PDFModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PDFModelRepo extends MongoRepository<PDFModel, String> {
}

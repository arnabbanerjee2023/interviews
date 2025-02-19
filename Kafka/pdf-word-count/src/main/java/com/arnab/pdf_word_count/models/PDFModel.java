package com.arnab.pdf_word_count.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pdf_model")
public class PDFModel {
    private String id;
    private String name;
    private String content;
}

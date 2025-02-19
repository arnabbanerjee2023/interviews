package com.arnab.pdf_word_count.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "word_count_store")
public class WordsCountStore {
    private String id;
    private String word;
    private Integer count;
}

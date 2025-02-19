package com.arnab.pdf_word_count.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "word_count_model")
public class WordCountModel {
    private String id;
    private String name;
    private List<WordsCount> wordCount;
}

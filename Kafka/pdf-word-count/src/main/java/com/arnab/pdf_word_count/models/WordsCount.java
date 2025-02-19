package com.arnab.pdf_word_count.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordsCount {
    private String word;
    private Integer count;
}

package com.arnab.pdf_word_count.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Utils {
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}

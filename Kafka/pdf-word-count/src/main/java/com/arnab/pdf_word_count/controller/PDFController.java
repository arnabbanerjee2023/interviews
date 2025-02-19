package com.arnab.pdf_word_count.controller;

import com.arnab.pdf_word_count.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1")
public class PDFController {

    @Autowired
    private PDFService service;

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public String upload(@RequestParam("name") String name, @RequestParam("document") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return "<h1>EMPTY FILE!</h1>";
        }
        return service.upload(name, multipartFile);
    }

}

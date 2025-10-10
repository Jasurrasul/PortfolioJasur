/*
package com.example.portfoliojasur.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class IndexController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping({"/", "/index", "/index.html"})
    public ResponseEntity<Resource> index() {
        Resource resource = resourceLoader.getResource("classpath:/static/index.html");
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(resource);
    }
}
*/
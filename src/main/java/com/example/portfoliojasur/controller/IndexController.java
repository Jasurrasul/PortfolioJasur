package com.example.portfoliojasur.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;

@Controller
public class IndexController {

    @GetMapping({"/", "/index", "/index.html"})
    public ResponseEntity<Resource> index() throws IOException {
        Resource resource = new ClassPathResource("/static/index.html");
        if (!resource.exists()) {
            // fallback: kanske ligger direkt på classpath utan /static/
            resource = new ClassPathResource("index.html");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(resource);
    }
}

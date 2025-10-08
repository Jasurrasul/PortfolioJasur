package com.example.portfoliojasur.controller;

import com.example.portfoliojasur.model.ContactRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(
        origins = {
                "http://localhost:5500",
                "http://127.0.0.1:5500",
                "http://localhost:5173",
                "https://playful-chaja-84cba3.netlify.app/"
        },
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS }
)
public class ContactController {

    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @PostMapping(value = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED) // 201 Created
    public Map<String, Boolean> contact(@Valid @RequestBody ContactRequest req) {
        // 🖨️ "Skrivas ut i backend": logga allt tydligt
        log.info("Kontaktförfrågan mottagen: name='{}', email='{}', message='{}'",
                req.getName(), req.getEmail(), req.getMessage());
        return Map.of("ok", true);
    }

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> health() {
        return Map.of("status", "okeyy");
    }
}

package com.example.portfoliojasur.controller;





import com.example.portfoliojasur.model.ContactRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.time.OffsetDateTime;
import java.util.Map;


@RestController
public class ContactController {


    private static final Logger log = LoggerFactory.getLogger(ContactController.class);


    @PostMapping("/api/contact")
    public ResponseEntity<?> contact(@Valid @RequestBody ContactRequest req) {
// Skriv ut i backend-loggen (kravet):
        log.info("[CONTACT] at {} | name='{}' email='{}' message='{}'",
                OffsetDateTime.now(), req.getName(), req.getEmail(), req.getMessage());


// Returnera enkelt OK-svar till frontend
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "receivedAt", OffsetDateTime.now().toString()
        ));
    }
}
package com.example.portfoliojasur.service;
/*
import com.example.portfoliojasur.entity.ContactMessage;
import com.example.portfoliojasur.model.ContactRequest;
import com.example.portfoliojasur.repo.ContactMessageRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender; // <-- BYT TILL DENNA
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactMessageRepository repo;
    private final JavaMailSender mailSender; // <-- BYT TYP

    public ContactService(ContactMessageRepository repo, JavaMailSender mailSender) {
        this.repo = repo;
        this.mailSender = mailSender;
    }

    public ContactMessage handle(ContactRequest req) {
        // Använd vanlig String#trim i stället för deprecated trimWhitespace
        String name = req.getName() != null ? req.getName().trim() : "";
        String email = req.getEmail() != null ? req.getEmail().trim() : "";
        String message = req.getMessage() != null ? req.getMessage().trim() : "";

        ContactMessage cm = new ContactMessage();
        cm.setName(name);
        cm.setEmail(email);
        cm.setMessage(message);

        cm = repo.save(cm);

        String to = System.getenv("NOTIFY_EMAIL");
        if (to != null && !to.isBlank()) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject("Nytt kontaktmeddelande");
            msg.setText("Name: " + name + "\nEmail: " + email + "\nMessage:\n" + message);
            mailSender.send(msg);
        }
        return cm;
    }
}
*/
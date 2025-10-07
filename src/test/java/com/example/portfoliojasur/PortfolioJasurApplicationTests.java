package com.example.portfoliojasur;

import com.example.portfoliojasur.entity.ContactMessage;
import com.example.portfoliojasur.repo.ContactMessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PortfolioJasurApplicationTests {

    @Autowired MockMvc mvc;

    @Autowired ContactMessageRepository repo;

    // Vi vill inte skicka riktiga mail i test: mocka JavaMailSender
    @MockBean JavaMailSender mailSender;

    // 1) Giltig kontakt → 201
    @Test
    void contact_should_return_201_on_valid_payload() throws Exception {
        mvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {"name":"Alice","email":"a@b.com","message":"Hej"}
                """))
                .andExpect(status().isCreated());
    }


}

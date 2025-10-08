package com.example.portfoliojasur;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PortfolioJasurApplicationTests {

    @Autowired
    MockMvc mvc;

    // 1) Giltig kontakt → 201
    @Test
    void giltig_kontaktinfo() throws Exception {
        mvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"name":"Alice","email":"a@b.com","message":"Hej"}
                        """))
                .andExpect(status().isCreated());
    }

    // 2) Ogiltig e-post → 400
    @Test
    void ogiltig_post() throws Exception {
        mvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"name":"Alice","email":"inte-en-email","message":"Hej"}
                        """))
                .andExpect(status().isBadRequest());
    }

    // 3) Health → 200
    @Test
    void health_should_return_200() throws Exception {
        mvc.perform(get("/api/health"))
                .andExpect(status().isOk());
    }
}





























/*
// 5) E-post skickas när kontakt tas emot (via controller → service)
'@Test
void posting_contact_should_send_email() throws Exception {
    mvc.perform(post("/api/contact")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                    {"name":"Eve","email":"eve@mail.com","message":"Ping"}
                """))
            .andExpect(status().isCreated());

    verify(mailSender).send(any(SimpleMailMessage.class));
}
*/
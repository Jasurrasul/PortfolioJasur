package com.example.portfoliojasur;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PortfolioJasurApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOk_whenValidContactRequest() throws Exception {
        // JSON som uppfyller valideringskraven
        String validJson = """
            {
              "name": "Jasur Rasulov",
              "email": "jasur@example.com",
              "message": "Hej! Det här är ett testmeddelande."
            }
            """;

        mockMvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequest_whenInvalidEmail() throws Exception {
        // Ogiltig e-post triggar valideringsfel
        String invalidJson = """
            {
              "name": "Test Namn",
              "email": "inte-en-epost",
              "message": "Testar validering av e-post."
            }
            """;

        mockMvc.perform(post("/api/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
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
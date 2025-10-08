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
    //@MockBean JavaMailSender mailSender;
    @MockBean
    private JavaMailSender mailSender; // <-- rätt bean-typ

    // 1) Giltig kontakt → 201
    @Test
    void Giltig_kontaktinfo() throws Exception {
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

    // 4) Repo: spara & hämta
    @Test
    void repository_should_save_and_find_message() {
        var cm = new ContactMessage();
        cm.setName("Bob");
        cm.setEmail("bob@mail.com");
        cm.setMessage("Hej från Bob");
        var saved = repo.save(cm);

        assertThat(saved.getId()).isNotNull();
        assertThat(repo.findById(saved.getId())).isPresent();
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
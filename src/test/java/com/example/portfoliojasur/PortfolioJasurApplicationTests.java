package com.example.portfoliojasur;

import com.example.portfoliojasur.controller.ContactController;
import com.example.portfoliojasur.model.ContactRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class  PortfolioJasurApplicationTests {

    private final ContactController controller = new ContactController();

    @Test
    @DisplayName("contact() ska returnera status=ok och innehålla receivedAt vid giltig request")
    void contact_validRequest_returnsOk() {
        // Arrange
        ContactRequest req = new ContactRequest();
        req.setName("Jasurbek");
        req.setEmail("jasur@example.com");
        req.setMessage("Hej från testet!");

        // Act
        ResponseEntity<?> response = controller.contact(req);

        // Assert
        assertEquals(200, response.getStatusCodeValue(), "Statuskod ska vara 200 OK");

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertNotNull(body, "Response body ska inte vara null");
        assertEquals("ok", body.get("status"), "status ska vara 'ok'");
        assertTrue(body.containsKey("receivedAt"), "ska innehålla fältet 'receivedAt'");

        // Kontrollera att tiden är ett giltigt ISO-format
        assertDoesNotThrow(() -> OffsetDateTime.parse((String) body.get("receivedAt")));
    }

    @Test
    @DisplayName("contact() ska logga även när message är tomt (men fortfarande returnera 200)")
    void contact_emptyMessage_stillReturnsOk() {
        // Arrange
        ContactRequest req = new ContactRequest();
        req.setName("Test");
        req.setEmail("test@example.com");
        req.setMessage("");

        // Act
        ResponseEntity<?> response = controller.contact(req);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("ok", body.get("status"));
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
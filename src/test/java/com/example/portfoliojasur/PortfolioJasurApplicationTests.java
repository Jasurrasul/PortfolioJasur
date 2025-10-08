




























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
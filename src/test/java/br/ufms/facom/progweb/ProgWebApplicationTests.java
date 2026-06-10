package br.ufms.facom.progweb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
class ProgWebApplicationTests {

    @Test
    void contextLoads() {
        // Teste básico só para validar se o contexto sobe com o profile de teste
    }
}

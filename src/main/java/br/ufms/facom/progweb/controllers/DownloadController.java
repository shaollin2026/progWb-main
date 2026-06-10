package br.ufms.facom.progweb.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @GetMapping("/exemplo")
    public ResponseEntity<InputStreamResource> downloadExemplo() {
        // Conteúdo simples para teste
        String conteudo = "Este é um arquivo de exemplo gerado pelo DownloadController.";
        ByteArrayInputStream stream = new ByteArrayInputStream(conteudo.getBytes(StandardCharsets.UTF_8));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=exemplo.txt")
                .contentType(MediaType.TEXT_PLAIN)
                .body(new InputStreamResource(stream));
    }
}

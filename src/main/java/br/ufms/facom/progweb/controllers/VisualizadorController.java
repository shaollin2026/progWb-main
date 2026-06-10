package br.ufms.facom.progweb.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/visualizador")
public class VisualizadorController {

    @GetMapping("/pdf/{nomeArquivo}")
    public ResponseEntity<Resource> visualizarPdf(@PathVariable String nomeArquivo) throws IOException {
        Resource resource = new ClassPathResource("arquivos/" + nomeArquivo + ".pdf");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + resource.getFilename())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/epub/{nomeArquivo}")
    public ResponseEntity<Resource> visualizarEpub(@PathVariable String nomeArquivo) throws IOException {
        Resource resource = new ClassPathResource("arquivos/" + nomeArquivo + ".epub");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + resource.getFilename())
                .contentType(MediaType.parseMediaType("application/epub+zip"))
                .body(resource);
    }
}

package com.oo2.grupo13.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oo2.grupo13.dtos.EmailDTO;
import com.oo2.grupo13.dtos.EmailFileDTO;
import com.oo2.grupo13.services.IEmailService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDTO emailDTO){
        System.out.println("Mensaje Recibido " + emailDTO);
        emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("estado", "Enviado");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/sendMessageFile")
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileDTO emailFileDTO){
        try {
            String fileName = emailFileDTO.getFile().getOriginalFilename();
            Path path = Paths.get("src/mail/resources/files/" + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File file = path.toFile();
            emailService.sendEmailWithFile(emailFileDTO.getToUser(), emailFileDTO.getSubject(), emailFileDTO.getMessage(), file);
            Map<String, String> response = new HashMap<>();
            response.put("estado", "Enviado");
            response.put("archivo", fileName);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
        }
    }
}
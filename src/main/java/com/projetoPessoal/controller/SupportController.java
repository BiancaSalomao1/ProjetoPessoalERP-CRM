package com.projetoPessoal.controller;

import com.projetoPessoal.dto.SupportTicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/support")
public class SupportController {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username:suporte@erpsocial.com}")
    private String mailFrom;

    @PostMapping("/ticket")
    public ResponseEntity<String> receiveTicket(@RequestBody SupportTicketDTO ticket) {
        // Simulando envio de e-mail ou processamento de ticket de suporte
        System.out.println("Recebido ticket de suporte: " + ticket.subject());
        System.out.println("Para: " + ticket.targetEmail());
        System.out.println("Mensagem: " + ticket.message());
        
        if (mailSender == null) {
            System.out.println("JavaMailSender não está configurado. O e-mail não foi enviado.");
            return ResponseEntity.ok("Ticket processado (servidor de e-mail não configurado).");
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailFrom);
            message.setTo(ticket.targetEmail());
            message.setSubject(ticket.subject());
            message.setText(ticket.message());
            mailSender.send(message);
            return ResponseEntity.ok("Ticket enviado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            return ResponseEntity.status(500).body("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}

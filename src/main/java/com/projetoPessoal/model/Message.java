package com.projetoPessoal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String content;
    private LocalDateTime submissionDate; // Data e hora de envio da mensagem
    private String submissionType; // email, sms, whatsapp
    private String status; //enviada, lida, falha


    @ManyToOne
    private User destinatario;


    @ManyToOne
    private Group grupoDestinatario;

}


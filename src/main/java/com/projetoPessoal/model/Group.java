package com.projetoPessoal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data // Lombok gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Gera construtor vazio
@AllArgsConstructor // Gera construtor com todos os campos
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String type;


}

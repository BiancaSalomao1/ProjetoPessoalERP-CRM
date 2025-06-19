package com.projetoPessoal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // Lombok gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Gera construtor vazio
@AllArgsConstructor // Gera construtor com todos os campos
@Builder
@Table(name = "user_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String type;


}

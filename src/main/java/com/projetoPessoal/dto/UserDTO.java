package com.projetoPessoal.dto;

import java.util.Objects;
import java.util.Set;
import lombok.*;

@Data // Lombok gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Gera construtor vazio
@AllArgsConstructor // Gera construtor com todos os campos
@Builder // Permite usar padrão Builder
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private double income;
    private int numOfDependents;
    private String status;
    private String observations;
    private Set<String> habilities;


    }




//package com.projetoPessoal.model;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Data // Lombok gera getters, setters, toString, equals e hashCode
//@NoArgsConstructor // Gera construtor vazio
//@AllArgsConstructor // Gera construtor com todos os campos
//@Builder
//public class Benefit {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String description;
//
//    @ManyToMany
//    private Set<Benefit> benefitSet = new HashSet<>();
//
//
//}

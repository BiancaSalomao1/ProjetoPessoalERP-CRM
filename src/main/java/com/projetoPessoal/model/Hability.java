//package com.projetoPessoal.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//
//@Data // Lombok gera getters, setters, toString, equals e hashCode
//@NoArgsConstructor // Gera construtor vazio
//@AllArgsConstructor // Gera construtor com todos os campos
//@Builder
//@Entity
//public class Hability {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String description;
//
//    @ManyToMany(mappedBy = "habilitySet")
//    private Set<User> userSet = new HashSet<>();
//
//
//}

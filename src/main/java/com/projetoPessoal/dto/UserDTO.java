package com.projetoPessoal.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {

    private Long id;

    @EqualsAndHashCode.Include
    private String name;

    private String email;
    private String phone;
    private String address;
    private double income;
    private int numOfDependents;
    private String status;
    private String observations;
    private String photo;

    private Set<String> habilities;
}

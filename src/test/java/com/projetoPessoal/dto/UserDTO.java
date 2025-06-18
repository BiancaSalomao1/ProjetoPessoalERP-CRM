package com.projetoPessoal.dto;

import java.util.Objects;
import java.util.Set;

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

    // Construtors

    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String phone, String address,
                   double income, int numOfDependents, String status, String observations,
                   Set<String> habilities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.income = income;
        this.numOfDependents = numOfDependents;
        this.status = status;
        this.observations = observations;
        this.habilities = habilities;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getNumOfDependents() {
        return numOfDependents;
    }

    public void setNumOfDependents(int numOfDependents) {
        this.numOfDependents = numOfDependents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Set<String> getHabilities() {
        return habilities;
    }

    public void setHabilities(Set<String> habilities) {
        this.habilities = habilities;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDto = (UserDTO) o;
        return Double.compare(income, userDto.income) == 0 && numOfDependents == userDto.numOfDependents && Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email) && Objects.equals(phone, userDto.phone) && Objects.equals(address, userDto.address) && Objects.equals(status, userDto.status) && Objects.equals(observations, userDto.observations) && Objects.equals(habilities, userDto.habilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, address, income, numOfDependents, status, observations, habilities);
    }

    @Override
    public String toString() {
        return "userDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", income=" + income +
                ", numOfDependents=" + numOfDependents +
                ", status='" + status + '\'' +
                ", observations='" + observations + '\'' +
                ", habilities=" + habilities +
                '}';
    }
}

package com.projetoPessoal.model;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//entidades
@Entity
public class User {
    @id @GeneratedValue
    private long id;
    private String name;
    private String email;
    private String phone;
    private String adress;
    private double income;
    private int numOfDependents;
    private String status;
    private String Observations;

    @ManyToMany
    @JoinTable(
            name = "user_hability",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hability_id")
    )
    private Set<Hability> habilitySet = new HashSet<>();

    //constructors


    public User() {

    }

    public User(String name, String email, String phone, String adress,
                double income, int numOfDependents, String status, String observations) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.income = income;
        this.numOfDependents = numOfDependents;
        this.status = status;
        this.Observations = observations;
    }


    // Getters and Setters
    public Set<Hability> getHabilitySet() {
        return habilitySet;
    }

    public void setHabilitySet(Set<Hability> habilitySet) {
        this.habilitySet = habilitySet;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
        return Observations;
    }

    public void setObservations(String observations) {
        Observations = observations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Double.compare(income, user.income) == 0 && numOfDependents == user.numOfDependents && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(adress, user.adress) && Objects.equals(status, user.status) && Objects.equals(Observations, user.Observations) && Objects.equals(habilitySet, user.habilitySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, adress, income, numOfDependents, status, Observations, habilitySet);
    }


}

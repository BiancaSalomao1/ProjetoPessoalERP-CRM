package com.projetoPessoal.model;

import java.time.LocalDate;

public class RegisterInteraction {

    private Long id;
    private LocalDate date;
    private String status;
    private String observation;

    //Getters and Setters

    public Long getId() {
        return id;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}

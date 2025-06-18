package com.projetoPessoal.model;

import java.time.LocalDateTime;

public class Message {

    private Long id;
    private String subject;
    private LocalDateTime dateTime;
    private String shippingType;
    private String status;

    //Getters and Setters

    public Long getId() {
        return id;
    }



    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

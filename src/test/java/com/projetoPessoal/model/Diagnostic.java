package com.projetoPessoal.model;

import java.time.LocalDate;

public class Diagnostic {
    private Long id;
    private LocalDate date;
    private String status;
    private String resumo;

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

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
}

package com.portuga.gymnasium.model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CondicaoPagamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String descricao;
    @Column
    private int diasAte;
    @Column
    private int diasEntre;

    public CondicaoPagamento() {
    }

    public CondicaoPagamento(int id, String descricao, int diasAte, int diasEntre) {
        this.id = id;
        this.descricao = descricao;
        this.diasAte = diasAte;
        this.diasEntre = diasEntre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDiasAte() {
        return diasAte;
    }

    public void setDiasAte(int diasAte) {
        this.diasAte = diasAte;
    }

    public int getDiasEntre() {
        return diasEntre;
    }

    public void setDiasEntre(int diasEntre) {
        this.diasEntre = diasEntre;
    }
}

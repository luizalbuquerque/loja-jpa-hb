/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portuga.gymnasium.model.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jonas SSD
 */
@Entity
public class EstoqueProduto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "idProduto", referencedColumnName = "id")
    private Produto produto;
    @Column
    private int estoqueInicial;
    @Column
    private int estoqueAtual;
    
    
    public EstoqueProduto() {
    }

    public EstoqueProduto(int id, Produto produto, int estoqueInicial, int estoqueAtual) {
        this.id = id;
        this.produto = produto;
        this.estoqueInicial = estoqueInicial;
        this.estoqueAtual = estoqueAtual;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the estoqueInicial
     */
    public int getEstoqueInicial() {
        return estoqueInicial;
    }

    /**
     * @param estoqueInicial the estoqueInicial to set
     */
    public void setEstoqueInicial(int estoqueInicial) {
        this.estoqueInicial = estoqueInicial;
    }

    /**
     * @return the estoqueAtual
     */
    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    /**
     * @param estoqueAtual the estoqueAtual to set
     */
    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }
}

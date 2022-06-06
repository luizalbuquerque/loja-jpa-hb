/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portuga.gymnasium.model.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jonas SSD
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
public class Pessoa implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String compleEndereco;
    @ManyToOne
    @JoinColumn(name = "idEndereco", referencedColumnName = "idEndereco")
    private Endereco endereco;
    @Column
    private String cpf;
    @Column
    private String rg;
    @Column
    private String dataNasc;
    @Column
    private String fone1;
     @Column
    private String fone2;
    @Column(insertable=false, updatable=false)
    private String tipo;
     
    public Pessoa(){
    }
    
      public Pessoa(int idCliente, String nomeCliente, String emailCliente, String CompleEnderecoCliente, Endereco endereco, String cpfCliente, String rgCliente, String dataNasc, String foneCliente, String fone2Cliente) {
        this.id = idCliente;
        this.nome = nomeCliente;
        this.email = emailCliente;
        this.compleEndereco = CompleEnderecoCliente;
        this.endereco = endereco;
        this.cpf = cpfCliente;
        this.rg = rgCliente;
        this.dataNasc = dataNasc;
        this.fone1 = foneCliente;
        this.fone2 = fone2Cliente;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the compleEndereco
     */
    public String getCompleEndereco() {
        return compleEndereco;
    }

    /**
     * @param compleEndereco the compleEndereco to set
     */
    public void setCompleEndereco(String compleEndereco) {
        this.compleEndereco = compleEndereco;
    }

    /**
     * @return the idEndereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param idEndereco the idEndereco to set
     */
    public void setEndereco(Endereco idEndereco) {
        this.endereco = idEndereco;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the dataNasc
     */
    public String getDataNasc() {
        return dataNasc;
    }

    /**
     * @param dataNasc the dataNasc to set
     */
    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    /**
     * @return the fone1
     */
    public String getFone1() {
        return fone1;
    }

    /**
     * @param fone1 the fone1 to set
     */
    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    /**
     * @return the fone2
     */
    public String getFone2() {
        return fone2;
    }

    /**
     * @param fone2 the fone2 to set
     */
    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }
    
     /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return  this.getId()+ ","+
                super.toString() + ","+
                this.getNome()+ ","+
                this.getEmail()+ ","+
                this.getCompleEndereco()+ ","+
                this.getEndereco().getLogradouroCep()+ ","+
                this.getCpf()+ ","+
                this.getRg()+ ","+
                this.getDataNasc() + ","+
                this.getFone1() + ","+
                this.getFone2();
    }
}

package com.portuga.gymnasium.model.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Endereco implements Serializable {
    
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int idEndereco;
  
  @Column
  private String cepCep;
  
  @Column
  private String logradouroCep;
  
  @ManyToOne
  @JoinColumn(name = "idBairro", referencedColumnName = "idBairro")
  private Bairro bairro;
  
  @ManyToOne
  @JoinColumn(name = "idCidade", referencedColumnName = "idCidade")
  private Cidade cidade;

    public Endereco() {
    }

    public Endereco(int idCep, String cepCep, String logradouroCep, Bairro bairro, Cidade cidade) {
        this.idEndereco = idCep;
        this.cepCep = cepCep;
        this.logradouroCep = logradouroCep;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public int getIdCep() {
        return idEndereco;
    }

    public void setIdCep(int idCep) {
        this.idEndereco = idCep;
    }

    public String getCepCep() {
        return cepCep;
    }

    public void setCepCep(String cepCep) {
        this.cepCep = cepCep;
    }

    public String getLogradouroCep() {
        return logradouroCep;
    }

    public void setLogradouroCep(String logradouroCep) {
        this.logradouroCep = logradouroCep;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return  this.getIdCep() + ","+
                this.getLogradouroCep()+ ","+
                this.getCepCep()+ ","+
                this.getCidade().getDescricaoCidade()+ ","+
                this.getBairro().getDescricaoBairro() + ",";
    }
  
  
  
}

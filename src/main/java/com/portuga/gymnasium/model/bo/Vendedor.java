package com.portuga.gymnasium.model.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("V")
public class Vendedor extends Pessoa{
    
    @Column
    private int comVenda;
    @Column 
    private int comRecebimento;
    
    
    public Vendedor(){
        
    }
    
    public Vendedor(int idCliente, String nomeCliente, String emailCliente, String CompleEnderecoCliente, Endereco endereco, String cpfCliente, String rgCliente, String dataNasc, String foneCliente, String fone2Cliente,int comVenda, int comRecebimento){
        super(comVenda, nomeCliente, emailCliente, CompleEnderecoCliente, endereco, cpfCliente, rgCliente, dataNasc, foneCliente, fone2Cliente);
        this.comVenda = comVenda;
        this.comRecebimento = comRecebimento;
    }

    /**
     * @return the comVenda
     */
    public int getComVenda() {
        return comVenda;
    }

    /**
     * @param comVenda the comVenda to set
     */
    public void setComVenda(int comVenda) {
        this.comVenda = comVenda;
    }

    /**
     * @return the comRecebimento
     */
    public int getComRecebimento() {
        return comRecebimento;
    }

    /**
     * @param comRecebimento the comRecebimento to set
     */
    public void setComRecebimento(int comRecebimento) {
        this.comRecebimento = comRecebimento;
    }
    
    @Override
     public String toString() {
        return  super.toString() + ","+
                this.getComRecebimento()+ ","+
                this.getComVenda();
     }
}

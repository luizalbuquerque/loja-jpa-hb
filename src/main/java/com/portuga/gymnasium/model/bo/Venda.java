package com.portuga.gymnasium.model.bo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private float valTotal;
    
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "idcondicaoPagamento", referencedColumnName = "id")
    private CondicaoPagamento condicaoPagamento;
    
    @ManyToOne
    @JoinColumn(name = "idVendedor", referencedColumnName = "id")
    private Vendedor vendedor;
    
    @OneToMany(mappedBy="venda", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idItemVenda")
    private List<ItemVenda> itensVenda;

    public Venda() {
    }
    
    public Venda(int id, Cliente cliente, CondicaoPagamento condicaoPagamento, Vendedor vendedor, List<ItemVenda> itensVenda){
        this.id = id;
        this.cliente = cliente;
        this.condicaoPagamento = condicaoPagamento;
        this.vendedor = vendedor;
        this.itensVenda = itensVenda;
    }

    public int getIdvenda() {
        return id;
    }

    public void setIdvenda(int idvenda) {
        this.id = idvenda;
    }

    public float getValTotal() {
        for (ItemVenda itemVenda : getItensVenda()) {
            this.valTotal = valTotal + itemVenda.getValTotalItem();
        }
        return this.valTotal;
    }

    public void setValTotal(float valTotal) {
        this.valTotal = valTotal;
    }
    /**
     * @return the condicaoPagamento
     */
    public CondicaoPagamento getCondicaoPagamento() {
        return condicaoPagamento;
    }

    /**
     * @param condicaoPagamento the condicaoPagamento to set
     */
    public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    /**
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the itensVenda
     */
    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    /**
     * @param itensVenda the itensVenda to set
     */
    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
    
    
}

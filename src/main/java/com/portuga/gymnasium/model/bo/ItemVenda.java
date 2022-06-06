package com.portuga.gymnasium.model.bo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ItemVenda {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "idVenda", referencedColumnName = "id", nullable=false)
    private Venda venda;
    
    @ManyToOne
    @JoinColumn(name = "idProduto", referencedColumnName = "id")
    private Produto produto;
    
    @Column
    private float valTotalItem;
    
    @Column
    private float qtdProduto;

    public ItemVenda() {
    }

    public ItemVenda(int id, Venda venda, Produto produto, float qtdProduto, float valTotalItem) {
        this.id = id;
        this.venda = venda;
        this.produto = produto;
        this.valTotalItem = valTotalItem;
        this.qtdProduto = qtdProduto;
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
     * @return the venda
     */
    public Venda getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(Venda venda) {
        this.venda = venda;
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
     * @return the valUnitario
     */
    public float getValTotalItem() {
        return valTotalItem = this.qtdProduto * this.produto.getVal();
    }

    /**
     * @param valUnitario the valUnitario to set
     */
    public void setValTotalItem(float ValTotalItem) {
        this.valTotalItem = ValTotalItem;
    }

    /**
     * @return the qtdProduto
     */
    public float getQtdProduto() {
        return qtdProduto;
    }

    /**
     * @param qtdProduto the qtdProduto to set
     */
    public void setQtdProduto(float qtdProduto) {
        this.qtdProduto = qtdProduto;
    }
}

package com.portuga.gymnasium.model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Produto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String descricao;
    @Column
    private float val;
    @ManyToOne
    @JoinColumn(name = "idMarca", referencedColumnName = "id")
    private Marca marca;
    @ManyToOne
    @JoinColumn(name = "idTipoProduto", referencedColumnName = "id")
    private TipoProduto tipoProduto;
    @ManyToOne
    @JoinColumn(name = "idTamanho", referencedColumnName = "id")
    private Tamanho tamanho;
    @Column
    private int estoqueInicial;
    

    public Produto() {
    }

    public Produto(int id, String descricao, float val, Marca marca, TipoProduto tipoProduto, Tamanho tamanho, int estoqueInicial) {
        this.id = id;
        this.descricao = descricao;
        this.val = val;
        this.marca = marca;
        this.tipoProduto = tipoProduto;
        this.tamanho = tamanho;
        this.estoqueInicial = estoqueInicial;
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

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
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
    
    
}

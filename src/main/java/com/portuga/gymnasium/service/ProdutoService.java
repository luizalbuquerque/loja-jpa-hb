package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.ProdutoDAO;
import com.portuga.gymnasium.model.bo.Produto;

public class ProdutoService implements InterfaceService<Produto> {

    @Override
    public void salvar(Produto objeto) {
        ProdutoDAO.getInstance().create(objeto);
    }

    @Override
    public List<Produto> buscar() {
        return ProdutoDAO.getInstance().retrieve();
    }

    @Override
    public Produto buscar(int codigo) {
        return ProdutoDAO.getInstance().retrieve(codigo);
    }

    @Override
    public Produto buscar(String descricao) {
        return ProdutoDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(Produto objeto) {
        ProdutoDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(Produto objeto) {
        ProdutoDAO.getInstance().delete(objeto);
    }
    
    public int getEstoqueAtual(int codigo){
        return ProdutoDAO.getInstance().getEstoqueAtual(codigo);
    } 
    
    public void atualizar(Produto objeto, int estoqueAtual) {
          ProdutoDAO.getInstance().update(objeto, estoqueAtual);
    }
}

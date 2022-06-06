package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.TipoProdutoDAO;
import com.portuga.gymnasium.model.bo.TipoProduto;

public class TipoProdutoService implements InterfaceService<TipoProduto> {

    @Override
    public void salvar(TipoProduto objeto) {
        TipoProdutoDAO.getInstance().create(objeto);
    }

    @Override
    public List<TipoProduto> buscar() {
        return TipoProdutoDAO.getInstance().retrieve();
    }

    @Override
    public TipoProduto buscar(int codigo) {
        return TipoProdutoDAO.getInstance().retrieve(codigo);
    }

    @Override
    public TipoProduto buscar(String descricao) {
        return TipoProdutoDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(TipoProduto objeto) {
        TipoProdutoDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(TipoProduto objeto) {
        TipoProdutoDAO.getInstance().delete(objeto);
    }
    
}

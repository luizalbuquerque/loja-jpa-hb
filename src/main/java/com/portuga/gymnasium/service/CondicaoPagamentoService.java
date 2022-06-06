package com.portuga.gymnasium.service;


import com.portuga.gymnasium.model.DAO.CondicaoPagamentoDAO;
import com.portuga.gymnasium.model.bo.CondicaoPagamento;

import java.util.List;

public class CondicaoPagamentoService implements InterfaceService<CondicaoPagamento> {

    @Override
    public void salvar(CondicaoPagamento objeto) {
        CondicaoPagamentoDAO.getInstance().create(objeto);
    }

    @Override
    public List<CondicaoPagamento> buscar() {
        return CondicaoPagamentoDAO.getInstance().retrieve();
    }

    @Override
    public CondicaoPagamento buscar(int codigo) {
        return CondicaoPagamentoDAO.getInstance().retrieve(codigo);
    }

    @Override
    public CondicaoPagamento buscar(String descricao) {
        return CondicaoPagamentoDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(CondicaoPagamento objeto) {
        CondicaoPagamentoDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(CondicaoPagamento objeto) {
        CondicaoPagamentoDAO.getInstance().delete(objeto);
    }
    
}

package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.TamanhoDAO;
import com.portuga.gymnasium.model.bo.Tamanho;

public class TamanhoService implements InterfaceService<Tamanho> {

    @Override
    public void salvar(Tamanho objeto) {
        TamanhoDAO.getInstance().create(objeto);
    }

    @Override
    public List<Tamanho> buscar() {
        return TamanhoDAO.getInstance().retrieve();
    }

    @Override
    public Tamanho buscar(int codigo) {
        return TamanhoDAO.getInstance().retrieve(codigo);
    }

    @Override
    public Tamanho buscar(String descricao) {
        return TamanhoDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(Tamanho objeto) {
        TamanhoDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(Tamanho objeto) {
        TamanhoDAO.getInstance().delete(objeto);
    }
    
}

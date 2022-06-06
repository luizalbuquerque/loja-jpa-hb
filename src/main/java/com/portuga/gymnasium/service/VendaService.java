package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.VendaDAO;
import com.portuga.gymnasium.model.bo.Venda;

public class VendaService implements InterfaceService<Venda> {

    @Override
    public void salvar(Venda objeto) {
        VendaDAO.getInstance().create(objeto);
    }

    @Override
    public List<Venda> buscar() {
        return VendaDAO.getInstance().retrieve();
    }

    @Override
    public Venda buscar(int codigo) {
        return VendaDAO.getInstance().retrieve(codigo);
    }

    @Override
    public Venda buscar(String descricao) {
        return VendaDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(Venda objeto) {
        VendaDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(Venda objeto) {
        VendaDAO.getInstance().delete(objeto);
    }
    
    
}

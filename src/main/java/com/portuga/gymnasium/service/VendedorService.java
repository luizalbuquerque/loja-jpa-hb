package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.VendedorDAO;
import com.portuga.gymnasium.model.bo.Vendedor;


public class VendedorService implements InterfaceService<Vendedor>{

    @Override
    public void salvar(Vendedor objeto) {
        VendedorDAO.getInstance().create(objeto);
    }

    @Override
    public List<Vendedor> buscar() {
        return VendedorDAO.getInstance().retrieve();
    }

    @Override
    public Vendedor buscar(int codigo) {
        return VendedorDAO.getInstance().retrieve(codigo);
    }

    @Override
    public Vendedor buscar(String descricao) {
        return VendedorDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(Vendedor objeto) {
        VendedorDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(Vendedor objeto) {
        VendedorDAO.getInstance().delete(objeto);
    }
    
}

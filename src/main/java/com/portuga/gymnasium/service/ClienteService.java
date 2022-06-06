package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.ClienteDAO;
import com.portuga.gymnasium.model.bo.Cliente;

public class ClienteService implements InterfaceService<Cliente> {

    @Override
    public void salvar(Cliente objeto) {
        ClienteDAO.getInstance().create(objeto);
    }

    @Override
    public List<Cliente> buscar() {
        return ClienteDAO.getInstance().retrieve();
    }

    @Override
    public Cliente buscar(int codigo) {
        return ClienteDAO.getInstance().retrieve(codigo);
    }

    @Override
    public Cliente buscar(String descricao) {
        return ClienteDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(Cliente objeto) {
        ClienteDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(Cliente objeto) {
        ClienteDAO.getInstance().delete(objeto);
    }
}

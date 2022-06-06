package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.EnderecoDAO;
import com.portuga.gymnasium.model.bo.Endereco;

public class EnderecoService implements InterfaceService<Endereco> {

    @Override
    public void salvar(Endereco objeto) {
        EnderecoDAO.getInstance().create(objeto);
    }

    @Override
    public List<Endereco> buscar() {
        return EnderecoDAO.getInstance().retrieve();
    }

    @Override
    public Endereco buscar(int codigo) {
        return EnderecoDAO.getInstance().retrieve(codigo);
    }

    @Override
    public Endereco buscar(String descricao) {
        return EnderecoDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(Endereco objeto) {
        EnderecoDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(Endereco objeto) {
        EnderecoDAO.getInstance().delete(objeto);
    }
}

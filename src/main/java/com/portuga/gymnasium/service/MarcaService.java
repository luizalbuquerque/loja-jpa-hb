package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.MarcaDAO;
import com.portuga.gymnasium.model.bo.Marca;

public class MarcaService implements InterfaceService<Marca> {

    @Override
    public void salvar(Marca objeto) {
        MarcaDAO.getInstance().create(objeto);
    }

    @Override
    public List<Marca> buscar() {
        return MarcaDAO.getInstance().retrieve();
    }

    @Override
    public Marca buscar(int codigo) {
        return MarcaDAO.getInstance().retrieve(codigo);
    }

    @Override
    public Marca buscar(String descricao) {
        return MarcaDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(Marca objeto) {
        MarcaDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(Marca objeto) {
        MarcaDAO.getInstance().delete(objeto);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portuga.gymnasium.service;

import java.util.List;
import com.portuga.gymnasium.model.DAO.BairroDAO;
import com.portuga.gymnasium.model.bo.Bairro;


/**
 *
 * @author JACKSON
 */
public class BairroService implements InterfaceService<Bairro> {

    @Override
    public void salvar(Bairro objeto) {
        BairroDAO.getInstance().create(objeto);
    }

    @Override
    public List<Bairro> buscar() {
        return BairroDAO.getInstance().retrieve();
    }

    @Override
    public Bairro buscar(int codigo) {
        return BairroDAO.getInstance().retrieve(codigo);
    }

    @Override
    public Bairro buscar(String descricao) {
        return BairroDAO.getInstance().retrieve(descricao);
    }

    @Override
    public void atualizar(Bairro objeto) {
        BairroDAO.getInstance().update(objeto);
    }

    @Override
    public void apagar(Bairro objeto) {
        BairroDAO.getInstance().delete(objeto);
    }
    
}

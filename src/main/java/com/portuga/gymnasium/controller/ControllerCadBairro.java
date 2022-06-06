/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Bairro;
import com.portuga.gymnasium.service.BairroService;
import com.portuga.gymnasium.view.TelaBusBairro;
import com.portuga.gymnasium.view.TelaCadBairro;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;


/**
 *
 * @author JACKSON
 */
public class ControllerCadBairro implements ActionListener {

    TelaCadBairro telaCadBairoo;
    public static int codigo;

    public ControllerCadBairro(TelaCadBairro telaCadBairoo) {
        this.telaCadBairoo = telaCadBairoo;
        this.telaCadBairoo.getjButtonBuscar().addActionListener(this);
        this.telaCadBairoo.getjButtonNovo().addActionListener(this);
        this.telaCadBairoo.getjButtonCancelar().addActionListener(this);
        this.telaCadBairoo.getjButtonGravar().addActionListener(this);
        
        this.ativa(true);
        this.ligaDesliga(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.telaCadBairoo.getjButtonNovo()) {
            this.ativa(false);
            this.ligaDesliga(true);
            this.telaCadBairoo.getjTFId().setEnabled(false);
        } else if (e.getSource() == this.telaCadBairoo.getjButtonCancelar()) {
            ativa(true);
            ligaDesliga(false);
        } else if (e.getSource() == this.telaCadBairoo.getjButtonGravar()) {
            this.cadastrar();
        } else if (e.getSource() == this.telaCadBairoo.getjButtonBuscar()) {
            this.buscar();
        }
    }
    
    //Método para habilitar/desabilitar botões(controle de estados)
    public void ativa(boolean estado) {
        this.telaCadBairoo.getjButtonNovo().setEnabled(estado);
        this.telaCadBairoo.getjButtonCancelar().setEnabled(!estado);
        this.telaCadBairoo.getjButtonGravar().setEnabled(!estado);
        this.telaCadBairoo.getjButtonBuscar().setEnabled(estado);
        this.telaCadBairoo.getjButtonSair().setEnabled(estado);
    }

    //Método para Ativação/Desativação/Limpeza ds 
    //Componentes do jPanelDados
    public void ligaDesliga(boolean estado) {
        Component[] componentes = this.telaCadBairoo.getjPanelDados().getComponents();
        for (Component componenteAtual : componentes) {
            if (componenteAtual instanceof JTextField) {
                ((JTextField) componenteAtual).setText("");
                componenteAtual.setEnabled(estado);
            } else if (componenteAtual instanceof JFormattedTextField) {
                ((JFormattedTextField) componenteAtual).setText("");
                componenteAtual.setEnabled(estado);
            } else if (componenteAtual instanceof JComboBox) {
                ((JComboBox) componenteAtual).setSelectedIndex(0);
                componenteAtual.setEnabled(estado);
            }
        }
    }
    
    private void cadastrar() {
        Bairro bairro = new Bairro();
        bairro.setDescricaoBairro(this.telaCadBairoo.getjTFNome().getText());

        BairroService cidSevice = new BairroService();
        if (this.telaCadBairoo.getjTFId().getText().trim().equalsIgnoreCase("")) {
            cidSevice.salvar(bairro);
        } else {
            bairro.setIdBairro(Integer.parseInt(this.telaCadBairoo.getjTFId().getText()));
            cidSevice.atualizar(bairro);
        }
        //Setar o estado do formulário
        ativa(true);
        ligaDesliga(false);
    }
    
    private void buscar() {
        this.codigo = 0;
        //chamada da tela da busca
        TelaBusBairro tela = new TelaBusBairro(null, true);
        ControllerBusBairro controller = new ControllerBusBairro(tela);
        tela.setVisible(true);

        if (this.codigo != 0) {
            BairroService cidadeService = new BairroService();
            Bairro bairro = cidadeService.buscar(this.codigo);

            this.ativa(false);
            this.ligaDesliga(true);

            this.telaCadBairoo.getjTFId().setText(bairro.getIdBairro()+ "");
            this.telaCadBairoo.getjTFNome().setText(bairro.getDescricaoBairro());
            
            this.telaCadBairoo.getjTFId().setEnabled(false);
        }
    }
}

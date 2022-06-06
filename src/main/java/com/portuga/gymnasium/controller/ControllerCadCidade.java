package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Cidade;
import com.portuga.gymnasium.service.CidadeService;
import com.portuga.gymnasium.view.TelaBusCidade;
import com.portuga.gymnasium.view.TelaCadCidade;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ControllerCadCidade implements ActionListener {

    TelaCadCidade telaCadCidade;
    public static int codigo;

    public ControllerCadCidade(TelaCadCidade telaCadCidade) {
        this.telaCadCidade = telaCadCidade;

        telaCadCidade.getjButtonBuscar().addActionListener(this);
        telaCadCidade.getjButtonNovo().addActionListener(this);
        telaCadCidade.getjButtonCancelar().addActionListener(this);
        telaCadCidade.getjButtonGravar().addActionListener(this);
        telaCadCidade.getjButtonSair().addActionListener(this);

        ativa(true);
        ligaDesliga(false);

    }

    //Nesse método controlamos o que fazer quando uma ação acontece
    //Não foram desenvolvidas ainda as funcionalidades de persistência
    @Override
    public void actionPerformed(ActionEvent acao) {
        if (acao.getSource() == telaCadCidade.getjButtonNovo()) {
            ativa(false);
            ligaDesliga(true);
            this.telaCadCidade.getjTFIdCidade().setEnabled(false);
        } else if (acao.getSource() == telaCadCidade.getjButtonCancelar()) {
            ativa(true);
            ligaDesliga(false);
        } else if (acao.getSource() == telaCadCidade.getjButtonGravar()) {
            
            //montar um objeto do tipo cidade
            Cidade cidade = new Cidade();
            cidade.setDescricaoCidade(this.telaCadCidade.getjTFNomeCidade().getText());
            cidade.setUfCidade(this.telaCadCidade.getjTFUF().getText());
            //acionar o service da cidade passando o objeto como parametro
            CidadeService cidSevice = new CidadeService();

            if (this.telaCadCidade.getjTFIdCidade().getText().trim().equalsIgnoreCase("")) {
                cidSevice.salvar(cidade);
            } else {
                cidade.setIdCidade(Integer.parseInt(this.telaCadCidade.getjTFIdCidade().getText()));
                cidSevice.atualizar(cidade);
            }
            //Setar o estado do formulário
            ativa(true);
            ligaDesliga(false);
        } else if (acao.getSource() == telaCadCidade.getjButtonBuscar()) {
            codigo = 0;
            //chamada da tela da busca
            TelaBusCidade telaBusCidade = new TelaBusCidade(null, true);
            ControllerBusCidade controllerBusCidade = new ControllerBusCidade(telaBusCidade);
            telaBusCidade.setVisible(true);

            if (codigo != 0) {
                Cidade cidade;
                CidadeService cidadeService = new CidadeService();
                cidade = cidadeService.buscar(codigo);

                ativa(false);
                ligaDesliga(true);

                this.telaCadCidade.getjTFIdCidade().setText(cidade.getIdCidade() + "");
                this.telaCadCidade.getjTFNomeCidade().setText(cidade.getDescricaoCidade());
                this.telaCadCidade.getjTFUF().setText(cidade.getUfCidade());

                this.telaCadCidade.getjTFIdCidade().setEnabled(false);
            }
        } else if (acao.getSource() == telaCadCidade.getjButtonSair()) {
        }
    }

    //Método para habilitar/desabilitar botões(controle de estados)
    public void ativa(boolean estado) {
        telaCadCidade.getjButtonNovo().setEnabled(estado);
        telaCadCidade.getjButtonCancelar().setEnabled(!estado);
        telaCadCidade.getjButtonGravar().setEnabled(!estado);
        telaCadCidade.getjButtonBuscar().setEnabled(estado);
        telaCadCidade.getjButtonSair().setEnabled(estado);
    }

    //Método para Ativação/Desativação/Limpeza ds 
    //Componentes do jPanelDados
    public void ligaDesliga(boolean estado) {
        Component[] componentes = this.telaCadCidade.getjPanelDados().getComponents();
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
}

package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.TipoProduto;
import com.portuga.gymnasium.service.TipoProdutoService;
import com.portuga.gymnasium.view.TelaBusTipoProduto;
import com.portuga.gymnasium.view.TelaCadTipoProduto;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;


public class ControllerCadTipoProduto implements ActionListener {

    public static int codigo;
    private TelaCadTipoProduto tela;

    public ControllerCadTipoProduto(TelaCadTipoProduto tela) {
        this.tela = tela;
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        
        this.ativa(true);
        this.ligaDesliga(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tela.getjButtonNovo()) {
            this.ativa(false);
            this.ligaDesliga(true);
            this.tela.getjTFId().setEnabled(false);
        } else if (e.getSource() == this.tela.getjButtonCancelar()) {
            ativa(true);
            ligaDesliga(false);
        } else if (e.getSource() == this.tela.getjButtonGravar()) {
            this.cadastrar();
        } else if (e.getSource() == this.tela.getjButtonBuscar()) {
            this.buscar();
        }
    }
    
    private void cadastrar() {
        TipoProduto item = new TipoProduto();
        item.setDescricao(this.tela.getjTFDescricao().getText());

        TipoProdutoService sevice = new TipoProdutoService();
        if (this.tela.getjTFId().getText().trim().equalsIgnoreCase("")) {
            sevice.salvar(item);
        } else {
            item.setId(Integer.parseInt(this.tela.getjTFId().getText()));
            sevice.atualizar(item);
        }
        //Setar o estado do formulário
        ativa(true);
        ligaDesliga(false);
    }
    
    private void buscar() {
        this.codigo = 0;

        TelaBusTipoProduto telaBusca = new TelaBusTipoProduto(null, true);
        ControllerBusTipoProduto controller = new ControllerBusTipoProduto(telaBusca);
        telaBusca.setVisible(true);

        if (this.codigo != 0) {
            TipoProdutoService service = new TipoProdutoService();
            TipoProduto item = service.buscar(this.codigo);

            this.ativa(false);
            this.ligaDesliga(true);

            this.tela.getjTFId().setText(item.getId()+ "");
            this.tela.getjTFDescricao().setText(item.getDescricao());
            
            this.tela.getjTFId().setEnabled(false);
        }
    }
 
    //Método para habilitar/desabilitar botões(controle de estados)
    public void ativa(boolean estado) {
        this.tela.getjButtonNovo().setEnabled(estado);
        this.tela.getjButtonCancelar().setEnabled(!estado);
        this.tela.getjButtonGravar().setEnabled(!estado);
        this.tela.getjButtonBuscar().setEnabled(estado);
        this.tela.getjButtonSair().setEnabled(estado);
    }

    //Método para Ativação/Desativação/Limpeza ds 
    //Componentes do jPanelDados
    public void ligaDesliga(boolean estado) {
        Component[] componentes = this.tela.getjPanelDados().getComponents();
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

package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.CondicaoPagamento;
import com.portuga.gymnasium.service.CondicaoPagamentoService;
import com.portuga.gymnasium.view.TelaBusCondicaoPagamento;
import com.portuga.gymnasium.view.TelaCadCondicaoPagamento;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;



public class ControllerCadCondicaoPagamento implements ActionListener {
    
    public static int codigo;
    private TelaCadCondicaoPagamento tela;

    public ControllerCadCondicaoPagamento(TelaCadCondicaoPagamento tela) {
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
        CondicaoPagamento item = new CondicaoPagamento();
        item.setDescricao(this.tela.getjTFDescricao().getText());
        item.setDiasAte((Integer) tela.getjSDiasAte().getValue());
        item.setDiasEntre((Integer) this.tela.getjSDiasEntre().getValue());

        CondicaoPagamentoService sevice = new CondicaoPagamentoService();
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

        TelaBusCondicaoPagamento telaBusca = new TelaBusCondicaoPagamento(null, true);
        ControllerBusCondicaoPagamento controller = new ControllerBusCondicaoPagamento(telaBusca);
        telaBusca.setVisible(true);

        if (this.codigo != 0) {
            CondicaoPagamentoService service = new CondicaoPagamentoService();
            CondicaoPagamento item = service.buscar(this.codigo);

            this.ativa(false);
            this.ligaDesliga(true);

            this.tela.getjTFId().setText(item.getId()+ "");
            this.tela.getjTFDescricao().setText(item.getDescricao());
            this.tela.getjSDiasAte().setValue(item.getDiasAte());
            this.tela.getjSDiasEntre().setValue(item.getDiasEntre());
            
            this.tela.getjTFId().setEnabled(false);
        }
    }
    
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
            } else if (componenteAtual instanceof JSpinner) {
                componenteAtual.setEnabled(estado);
            }
        }
    }
}

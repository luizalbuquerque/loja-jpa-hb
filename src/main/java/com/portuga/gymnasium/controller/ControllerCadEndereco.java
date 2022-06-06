package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Bairro;
import com.portuga.gymnasium.model.bo.Cidade;
import com.portuga.gymnasium.model.bo.Endereco;
import com.portuga.gymnasium.service.BairroService;
import com.portuga.gymnasium.service.CidadeService;
import com.portuga.gymnasium.service.EnderecoService;
import com.portuga.gymnasium.view.TelaBusEndereco;
import com.portuga.gymnasium.view.TelaCadEndereco;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;


public class ControllerCadEndereco implements ActionListener {

    public static int codigo;
    private TelaCadEndereco tela;

    public ControllerCadEndereco(TelaCadEndereco tela) {
        this.tela = tela;
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        
        this.carregaComboCidade();
        this.carregaComboBairro();
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
        Endereco item = new Endereco();
        item.setCepCep(this.tela.getjTFCep().getText());
        item.setLogradouroCep(this.tela.getjTFLogradouro().getText());
        item.setBairro((Bairro) this.tela.getjCBBairro().getSelectedItem());
        item.setCidade((Cidade) this.tela.getjCBCidade().getSelectedItem());

        EnderecoService sevice = new EnderecoService();
        if (this.tela.getjTFId().getText().trim().equalsIgnoreCase("")) {
            sevice.salvar(item);
        } else {
            item.setIdCep(Integer.parseInt(this.tela.getjTFId().getText()));
            sevice.atualizar(item);
        }
        //Setar o estado do formulário
        ativa(true);
        ligaDesliga(false);
    }
    
    private void buscar() {
        this.codigo = 0;

        TelaBusEndereco telaBusca = new TelaBusEndereco(null, true);
        ControllerBusEndereco controller = new ControllerBusEndereco(telaBusca);
        telaBusca.setVisible(true);

        if (this.codigo != 0) {
            EnderecoService service = new EnderecoService();
            Endereco item = service.buscar(this.codigo);

            this.ativa(false);
            this.ligaDesliga(true);

            this.tela.getjTFId().setText(item.getIdCep()+ "");
            this.tela.getjTFCep().setText(item.getCepCep());
            this.tela.getjTFLogradouro().setText(item.getLogradouroCep());
            this.defineBairroSelecionado(item);
            this.defineCidadeSelecionada(item);
            
            this.tela.getjTFId().setEnabled(false);
        }
    }
    
    private void defineBairroSelecionado(Endereco item) {
        JComboBox cb = this.tela.getjCBBairro();
        int count = cb.getItemCount();
        Bairro bairro = null;
        
        for (int i = 0; i < count; i++) {
            bairro = (Bairro) cb.getItemAt(i);
            if (bairro.getIdBairro() == item.getBairro().getIdBairro()) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void defineCidadeSelecionada(Endereco item) {
        JComboBox cb = this.tela.getjCBCidade();
        int count = cb.getItemCount();
        Cidade cidade = null;
        
        for (int i = 0; i < count; i++) {
            cidade = (Cidade) cb.getItemAt(i);
            if (cidade.getIdCidade()== item.getCidade().getIdCidade()) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void carregaComboBairro() {
        DefaultComboBoxModel comboModel = (DefaultComboBoxModel) this.tela.getjCBBairro().getModel();
        comboModel.removeAllElements();
        
        BairroService bairroService = new BairroService();
        for (Bairro item: bairroService.buscar()) {
            comboModel.addElement(item);
        }
    }
    
    private void carregaComboCidade() {
        DefaultComboBoxModel comboModel = (DefaultComboBoxModel) this.tela.getjCBCidade().getModel();
        comboModel.removeAllElements();
        
        CidadeService service = new CidadeService();
        for (Cidade item: service.buscar()) {
            comboModel.addElement(item);
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

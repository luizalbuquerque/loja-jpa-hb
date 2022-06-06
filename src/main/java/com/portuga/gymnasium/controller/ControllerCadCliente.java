package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Cliente;
import com.portuga.gymnasium.model.bo.Endereco;
import com.portuga.gymnasium.service.ClienteService;
import com.portuga.gymnasium.service.EnderecoService;
import com.portuga.gymnasium.view.TelaBusCliente;
import com.portuga.gymnasium.view.TelaBusEndereco;
import com.portuga.gymnasium.view.TelaCadCliente;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;


public class ControllerCadCliente implements ActionListener {

    public static int codigo;
    public static int codigoCep;
    private TelaCadCliente tela;

    public ControllerCadCliente(TelaCadCliente tela) {
        this.tela = tela;
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjBBuscarCep().addActionListener(this);
        
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
        } else if (e.getSource() == this.tela.getjBBuscarCep()) {
            this.buscarCep();
        }
    }
    
    private void cadastrar() {
        Cliente item = new Cliente();
        item.setNome(this.tela.getjTFNome().getText());
        item.setDataNasc(this.tela.getjTFDtNas().getText());
        item.setCpf(this.tela.getjFTFCPF().getText());
        item.setRg(this.tela.getjFTFRG().getText());
        item.setFone1(this.tela.getjFTFFone1().getText());
        item.setFone2(this.tela.getjFTFFone2().getText());
        item.setEmail(this.tela.getjTFEmail().getText());
        item.setCompleEndereco(this.tela.getjTFComplemento().getText());
        
        Endereco endereco = new Endereco();
        endereco.setIdCep(Integer.parseInt(this.tela.getjTFIdCep().getText()));
        item.setEndereco(endereco);

        ClienteService service = new ClienteService();
        if (this.tela.getjTFId().getText().trim().equalsIgnoreCase("")) {
            service.salvar(item);
        } else {
            item.setId(Integer.parseInt(this.tela.getjTFId().getText()));
            service.atualizar(item);

        }
        //Setar o estado do formulário
        ativa(true);
        ligaDesliga(false);
    }
    
    private void buscarCep() {
        this.codigoCep = 0;

        TelaBusEndereco telaBusca = new TelaBusEndereco(null, true);
        ControllerBusEndereco controller = new ControllerBusEndereco(telaBusca);
        telaBusca.setVisible(true);

        if (this.codigoCep != 0) {
            EnderecoService service = new EnderecoService();
            Endereco item = service.buscar(this.codigoCep);

            this.tela.getjTFIdCep().setText(item.getIdCep()+ "");
            this.tela.getjTFLogradouro().setText(item.getLogradouroCep());
            this.tela.getjTFCidade().setText(item.getCidade().getDescricaoCidade());
            this.tela.getjTFBairro().setText(item.getBairro().getDescricaoBairro());
            
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

    private void buscar() {
        this.codigo = 0;

        TelaBusCliente telaBusca = new TelaBusCliente(null, true);
        ControllerBusCliente controller = new ControllerBusCliente(telaBusca);
        telaBusca.setVisible(true);

        if (this.codigo != 0) {
            ClienteService service = new ClienteService();
            Cliente item = service.buscar(this.codigo);
            EnderecoService serviceEndereco = new EnderecoService();
            item.setEndereco(serviceEndereco.buscar(item.getEndereco().getIdCep()));
           
            this.ativa(false);
            this.ligaDesliga(true);

            this.tela.getjTFId().setText(item.getId()+ "");
            this.tela.getjTFNome().setText(item.getNome());
            this.tela.getjTFDtNas().setText(item.getDataNasc());
            this.tela.getjFTFCPF().setText(item.getCpf());
            this.tela.getjFTFRG().setText(item.getRg());
            this.tela.getjFTFFone1().setText(item.getFone1());
            this.tela.getjFTFFone2().setText(item.getFone2());
            this.tela.getjTFBairro().setText(item.getEndereco().getBairro().getDescricaoBairro());
            this.tela.getjTFCidade().setText(item.getEndereco().getCidade().getDescricaoCidade());
            this.tela.getjTFComplemento().setText(item.getCompleEndereco());
            this.tela.getjTFEmail().setText(item.getEmail());
            this.tela.getjTFIdCep().setText(item.getEndereco().getIdCep()+"");
            this.tela.getjTFLogradouro().setText(item.getEndereco().getLogradouroCep());
            
            this.tela.getjTFId().setEnabled(false);
        }
    }
}

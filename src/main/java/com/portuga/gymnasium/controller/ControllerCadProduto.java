package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Marca;
import com.portuga.gymnasium.model.bo.Produto;
import com.portuga.gymnasium.model.bo.Tamanho;
import com.portuga.gymnasium.model.bo.TipoProduto;
import com.portuga.gymnasium.service.MarcaService;
import com.portuga.gymnasium.service.ProdutoService;
import com.portuga.gymnasium.service.TamanhoService;
import com.portuga.gymnasium.service.TipoProdutoService;
import com.portuga.gymnasium.view.TelaBusProduto;
import com.portuga.gymnasium.view.TelaCadProduto;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;


public class ControllerCadProduto implements ActionListener {
    
    public static int codigo;
    private TelaCadProduto tela;

    public ControllerCadProduto(TelaCadProduto tela) {
        this.tela = tela;
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        
        this.carregaMarcaComboBox();
        this.carregaTamanhoComboBox();
        this.carregaTipoComboBox();
        
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
    
    private void carregaMarcaComboBox() {
        DefaultComboBoxModel comboModel = (DefaultComboBoxModel) this.tela.getjCBMarca().getModel();
        comboModel.removeAllElements();
        
        MarcaService marcas = new MarcaService();
        for (Marca item: marcas.buscar()) {
            comboModel.addElement(item);
        }
    }
    
    private void carregaTipoComboBox() {
        DefaultComboBoxModel comboModel = (DefaultComboBoxModel) this.tela.getjCBTipo().getModel();
        comboModel.removeAllElements();
        
        TipoProdutoService service = new TipoProdutoService();
        for (TipoProduto item: service.buscar()) {
            comboModel.addElement(item);
        }
    }
    
    private void carregaTamanhoComboBox() {
        DefaultComboBoxModel comboModel = (DefaultComboBoxModel) this.tela.getjCBTamanho().getModel();
        comboModel.removeAllElements();
        
        TamanhoService service = new TamanhoService();
        for (Tamanho item: service.buscar()) {
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

    private void cadastrar() {
        Produto item = new Produto();
        item.setDescricao(this.tela.getjTFDescricao().getText());
        item.setVal(Float.parseFloat(this.tela.getjFTFValor().getText()));
        item.setMarca((Marca) this.tela.getjCBMarca().getSelectedItem());
        item.setTipoProduto((TipoProduto) this.tela.getjCBTipo().getSelectedItem());
        item.setTamanho((Tamanho) this.tela.getjCBTamanho().getSelectedItem());
        item.setEstoqueInicial(Integer.parseInt(this.tela.getEstoque().getText()));
        
        
        ProdutoService sevice = new ProdutoService();
        if (this.tela.getjTFId().getText().trim().equalsIgnoreCase("")) {
            sevice.salvar(item);
        } else {
            int estoqueAtual = Integer.parseInt(this.tela.getEstoqueAtual().getText());
            item.setId(Integer.parseInt(this.tela.getjTFId().getText()));
            sevice.atualizar(item,estoqueAtual);
        }
        //Setar o estado do formulário
        ativa(true);
        ligaDesliga(false);
    }
    
    private void buscar() {
        this.codigo = 0;

        TelaBusProduto telaBuscaProduto = new TelaBusProduto(tela, true);
        ControllerBusProduto controller = new ControllerBusProduto(telaBuscaProduto);
        telaBuscaProduto.setVisible(true);

        if (this.codigo != 0) {
            ProdutoService service = new ProdutoService();
            Produto item = service.buscar(this.codigo);
            
            int estoqueAtual = service.getEstoqueAtual(this.codigo);
            
            TipoProdutoService servideTipoProduto = new  TipoProdutoService();
            TipoProduto tipoProduto = servideTipoProduto.buscar(item.getTipoProduto().getId());
            item.setTipoProduto(tipoProduto);
            
            MarcaService marcaService = new  MarcaService();
            Marca marca = marcaService.buscar(item.getMarca().getId());
            item.setMarca(marca);
            
            TamanhoService tamanhoService = new  TamanhoService();
            Tamanho tamanho = tamanhoService.buscar(item.getTamanho().getId());
            item.setTamanho(tamanho);
            
            this.ativa(false);
            this.ligaDesliga(true);

            this.tela.getjTFId().setText(item.getId()+ "");
            this.tela.getjTFDescricao().setText(item.getDescricao());
            this.tela.getjFTFValor().setText(Float.toString(item.getVal()));
            this.tela.getEstoque().setText(Integer.toString(item.getEstoqueInicial()));
            this.tela.getEstoqueAtual().setText(String.valueOf(estoqueAtual));
            this.defineMarcaSelecionado(item);
            this.defineTipoSelecionado(item);
            this.defineTamanhoSelecionado(item);
            this.tela.getjTFId().setEnabled(false);
            this.tela.getEstoque().setEnabled(false);
        }
    }
    
    private void defineMarcaSelecionado(Produto item) {
        JComboBox cb = this.tela.getjCBMarca();
        int count = cb.getItemCount();
        Marca aux = null;
        
        for (int i = 0; i < count; i++) {
            aux = (Marca) cb.getItemAt(i);
            if (aux.getId() == item.getMarca().getId()) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void defineTipoSelecionado(Produto item) {
        JComboBox cb = this.tela.getjCBTipo();
        int count = cb.getItemCount();
        TipoProduto aux = null;
        
        for (int i = 0; i < count; i++) {
            aux = (TipoProduto) cb.getItemAt(i);
            if (aux.getId() == item.getTipoProduto().getId()) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void defineTamanhoSelecionado(Produto item) {
        JComboBox cb = this.tela.getjCBTamanho();
        int count = cb.getItemCount();
        Tamanho aux = null;
        
        for (int i = 0; i < count; i++) {
            aux = (Tamanho) cb.getItemAt(i);
            if (aux.getId() == item.getTamanho().getId()) {
                cb.setSelectedIndex(i);
                break;
            }
        }
    }
}

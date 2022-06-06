/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portuga.gymnasium.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.portuga.gymnasium.model.bo.*;
import com.portuga.gymnasium.service.*;
import com.portuga.gymnasium.view.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JACKSON
 */
public class ControllerVendas implements ActionListener, KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = this.tela.getjTextFieldBarraProduto().getText();
            if (codigo.matches("-?\\d+")) {
                this.adicionarProduto(Integer.parseInt(codigo));
            }
            this.tela.getjTextFieldBarraProduto().setText("");
        }
    }
    
    public static int codigoProduto;
    public static int codigoVendedor;
    public static int codigoCliente;
    public static int codigoCondicao;
    private TelaVendas tela;
    private int itemCount;
    private float itemTotal;
    private List<ItemVenda> itensVendas;

    public ControllerVendas(TelaVendas tela) {
        this.tela = tela;
        this.tela.getjButtonBuscaProduto().addActionListener(this);
        this.tela.getjButtonBuscaAluno().addActionListener(this);
        this.tela.getjButtonBuscaPersonal().addActionListener(this);
        this.tela.getjBCancelar().addActionListener(this);
        this.tela.getjBFecharVenda().addActionListener(this);
        this.tela.getjBSair().addActionListener(this);
        this.tela.getjBNovaVenda().addActionListener(this);
        this.tela.getjTextFieldBarraProduto().addKeyListener(this);
        itensVendas = new ArrayList<ItemVenda>();
        
        this.ativa(true);
        this.ligaDesliga(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.tela.getjBNovaVenda()) {
            this.ativa(false);
            this.ligaDesliga(true);
        } else if (e.getSource() == this.tela.getjBCancelar()) {
            ativa(true);
            ligaDesliga(false);
        } else if (e.getSource() == this.tela.getjBFecharVenda()) {
            this.fecharVenda();
        } else if (e.getSource() == this.tela.getjButtonBuscaPersonal()) {
            this.buscarVendedor();
        } else if (e.getSource() == this.tela.getjButtonBuscaAluno()) {
            this.buscarCliente();
        } else if (e.getSource() == this.tela.getjButtonBuscaProduto()) {
            this.buscarProduto();
        }
    }    
    
    //Método para habilitar/desabilitar botões(controle de estados)
    public void ativa(boolean estado) {
        this.tela.getjBNovaVenda().setEnabled(estado);
        this.tela.getjBCancelar().setEnabled(!estado);
        this.tela.getjBFecharVenda().setEnabled(!estado);
        this.tela.getjBSair().setEnabled(estado);
    }
    
    public void ligaDesliga(boolean estado) {
        Component[] componentes = this.tela.getjPanel7().getComponents();
        for (Component componenteAtual : componentes) {
            if (componenteAtual instanceof JTextField) {
                ((JTextField) componenteAtual).setText("");
            } else if (componenteAtual instanceof JFormattedTextField) {
                ((JFormattedTextField) componenteAtual).setText("");
            }
        }
        
        this.tela.getjTextFieldBarraProduto().setText("");
        this.tela.getjTextFieldBarraProduto().setEnabled(estado);
        this.tela.getjButtonBuscaAluno().setEnabled(estado);
        this.tela.getjButtonBuscaPersonal().setEnabled(estado);
        this.tela.getjButtonBuscaProduto().setEnabled(estado);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        tabela.setNumRows(0);

        this.itemCount = 0;
        this.setItemTotal(0, true);
        
        if (estado) {
            this.tela.getJTFStatus().setText("Aberto");
            Date data = new Date();
            this.tela.getjTextFieldData().setText(data.toString());
        } else {
            this.tela.getjTextFieldData().setText("");
            this.tela.getJTFStatus().setText("Fechado");
        }
    } 
    
    private void buscarVendedor() {
        this.codigoVendedor = 0;

        TelaBusVendedor telaBusca = new TelaBusVendedor(null, true);
        ControllerBusVendedor controller = new ControllerBusVendedor(telaBusca);
        telaBusca.setVisible(true);

        if (this.codigoVendedor != 0) {
            VendedorService service = new VendedorService();
            Vendedor item = service.buscar(this.codigoVendedor);
            this.tela.getjFTFidPersonal().setText(item.getId()+"");
            this.tela.getjFTFNomePersonal().setText(item.getNome());
        }
    }
    
    private void buscarCliente() {
        this.codigoCliente = 0;
        TelaBusCliente telaBusca = new TelaBusCliente(null, true);
        ControllerBusCliente controller = new ControllerBusCliente(telaBusca);
        telaBusca.setVisible(true);

        if (this.codigoCliente != 0) {
            ClienteService service = new ClienteService();
            Cliente item = service.buscar(this.codigoCliente);

            this.tela.getjFTFidAluno().setText(item.getId()+ "");
            this.tela.getjFTFNomeAluno().setText(item.getNome());
            this.tela.getjFTFCidadeAluno().setText(item.getEndereco().getCidade().getDescricaoCidade());
            this.tela.getjFTFBairroAluno().setText(item.getEndereco().getBairro().getDescricaoBairro());
            this.tela.getjFTFFoneAluno().setText(item.getFone1());
            this.tela.getjFTFEmailAluno().setText(item.getEmail());
        }
    }
    
    private void buscarProduto() {
        this.codigoProduto = 0;

        TelaBusProduto telaBusca = new TelaBusProduto(null, true);
        ControllerBusProduto controller = new ControllerBusProduto(telaBusca);
        telaBusca.setVisible(true);

        if (this.codigoProduto != 0) {
            this.adicionarProduto(codigoProduto);
        }
    }
    
    private void adicionarProduto(int codigo) {
        ProdutoService service = new ProdutoService();
        Produto item = service.buscar(codigo);
        
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setProduto(item);
        itemVenda.setQtdProduto(1);
        itemVenda.setValTotalItem(item.getVal() * 1);
       
        this.itensVendas.add(itemVenda);
        
        if (item.getId() < 1) {
            return;
        }
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.itemCount++;
        tabela.addRow(
            new Object[]{
                this.itemCount,
                item.getId(),
                item.getDescricao(),
                1,
                item.getVal(),
                item.getVal()
            }
        );
        
        this.setItemTotal(item.getVal());
    }
    
    private void setItemTotal(float total) {
        this.setItemTotal(total, false);
    }
    
    private void setItemTotal(float total, boolean overwrite) {
        if (overwrite) {
            this.itemTotal = total;
        } else {
            this.itemTotal += total;
        }
        this.tela.getjLabelTotal().setText("R$ " + this.itemTotal);
    }

    private void fecharVenda() {
        this.codigoCondicao = 0;

        TelaBusCondicaoPagamento telaBusca = new TelaBusCondicaoPagamento(null, true);
        ControllerBusCondicaoPagamento controller = new ControllerBusCondicaoPagamento(telaBusca);
        telaBusca.setVisible(true);
        
        CondicaoPagamentoService condicaoPagamentoService = new CondicaoPagamentoService();
        VendedorService vendedorService = new VendedorService();
        
        ClienteService clienteService = new ClienteService();
        
        if (this.codigoCondicao == 0) {
            return;
        }
        
        Venda item = new Venda();
        item.setVendedor(vendedorService.buscar(Integer.parseInt(this.tela.getjFTFidPersonal().getText())));
        item.setCliente(clienteService.buscar(Integer.parseInt(this.tela.getjFTFidAluno().getText())));
        item.setCondicaoPagamento(condicaoPagamentoService.buscar(this.codigoCondicao));
        item.setValTotal(this.itemTotal);
        item.setItensVenda(this.itensVendas);
        VendaService vendas = new VendaService();
        vendas.salvar(item);
        
        ativa(true);
        ligaDesliga(false);
    }
}

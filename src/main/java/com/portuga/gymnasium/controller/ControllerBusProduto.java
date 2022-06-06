package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Produto;
import com.portuga.gymnasium.service.ProdutoService;
import com.portuga.gymnasium.view.TelaBusProduto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;



public class ControllerBusProduto implements ActionListener {
    
    private TelaBusProduto tela;

    public ControllerBusProduto(TelaBusProduto tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        ProdutoService service = new ProdutoService();
        for (Produto item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getId(),
                    item.getDescricao(),
                    item.getVal()
                }
            );
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadProduto.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            ControllerVendas.codigoProduto = ControllerCadProduto.codigo;
            this.tela.dispose();
        }
    }
    
}

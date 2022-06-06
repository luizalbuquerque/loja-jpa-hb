package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.TipoProduto;
import com.portuga.gymnasium.service.TipoProdutoService;
import com.portuga.gymnasium.view.TelaBusTipoProduto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


public class ControllerBusTipoProduto implements ActionListener {
    
    private TelaBusTipoProduto tela;

    public ControllerBusTipoProduto(TelaBusTipoProduto tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        TipoProdutoService service = new TipoProdutoService();
        for (TipoProduto item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getId(),
                    item.getDescricao()
                }
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadTipoProduto.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            this.tela.dispose();
        }
    }
    
}

package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Tamanho;
import com.portuga.gymnasium.service.TamanhoService;
import com.portuga.gymnasium.view.TelaBusTamanho;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


public class ControllerBusTamanho implements ActionListener {
    
    private TelaBusTamanho tela;
    
    public ControllerBusTamanho(TelaBusTamanho tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        TamanhoService service = new TamanhoService();
        for (Tamanho item : service.buscar()) {
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
            ControllerCadTamanho.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            this.tela.dispose();
        }
    }
}

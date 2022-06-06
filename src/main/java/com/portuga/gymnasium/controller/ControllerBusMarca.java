package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Marca;
import com.portuga.gymnasium.service.MarcaService;
import com.portuga.gymnasium.view.TelaBusMarca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


public class ControllerBusMarca implements ActionListener {
    
    private TelaBusMarca tela;

    public ControllerBusMarca(TelaBusMarca tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        MarcaService service = new MarcaService();
        for (Marca item : service.buscar()) {
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
            ControllerCadMarca.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            this.tela.dispose();
        }
    }
    
}

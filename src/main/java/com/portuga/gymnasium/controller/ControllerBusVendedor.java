package com.portuga.gymnasium.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import com.portuga.gymnasium.model.bo.Vendedor;
import com.portuga.gymnasium.service.VendedorService;

import com.portuga.gymnasium.view.TelaBusVendedor;

public class ControllerBusVendedor implements ActionListener {

    private TelaBusVendedor tela;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadVendedor.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            ControllerVendas.codigoVendedor = ControllerCadVendedor.codigo;
            this.tela.dispose();
        }
    }

    public ControllerBusVendedor(TelaBusVendedor tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        VendedorService service = new VendedorService();
        for (Vendedor item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getId(),
                    item.getNome(),
                    item.getCpf(),
                    item.getFone1()
                }
            );
        }
    }
    
    
}

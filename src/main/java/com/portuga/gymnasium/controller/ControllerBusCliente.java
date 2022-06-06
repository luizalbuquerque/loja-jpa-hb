package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Cliente;
import com.portuga.gymnasium.service.ClienteService;
import com.portuga.gymnasium.view.TelaBusCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


public class ControllerBusCliente implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadCliente.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            ControllerVendas.codigoCliente = ControllerCadCliente.codigo;
            this.tela.dispose();
        }
    }

    private TelaBusCliente tela;

    public ControllerBusCliente(TelaBusCliente tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        ClienteService service = new ClienteService();
        for (Cliente item : service.buscar()) {
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

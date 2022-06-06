package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Endereco;
import com.portuga.gymnasium.service.EnderecoService;
import com.portuga.gymnasium.view.TelaBusEndereco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


public class ControllerBusEndereco implements ActionListener {
    
    private TelaBusEndereco tela;

    public ControllerBusEndereco(TelaBusEndereco tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        EnderecoService service = new EnderecoService();
        for (Endereco item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getIdCep(),
                    item.getCepCep(),
                    item.getLogradouroCep()
                }
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadEndereco.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            ControllerCadCliente.codigoCep = ControllerCadEndereco.codigo;
            ControllerCadVendedor.codigoCep = ControllerCadEndereco.codigo;
            this.tela.dispose();
        }
    }
    
}

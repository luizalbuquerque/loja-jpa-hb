package com.portuga.gymnasium.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import com.portuga.gymnasium.model.bo.Vendedor;
import com.portuga.gymnasium.service.VendedorService;

import com.portuga.gymnasium.view.TelaBusVendedor;
import javax.swing.JTable;

public class ControllerBusVendedor implements ActionListener {

    TelaBusVendedor tela;
    private DefaultTableModel tabela;

    public ControllerBusVendedor(TelaBusVendedor tela) {
this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);
        
        VendedorService service = new VendedorService();
        for (Vendedor item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getId(),
                    item.getNome(),
                    item.getCpf(),
                    item.getFone1() });
        }
        tela.getjButtonDeletar().addActionListener(a -> apaga());
    }
    
     private void apaga() {
        if (tela.getjTable1().getSelectedRow() < 0) {
            return;
        }

            JTable table = tela.getjTable1();
        int selectedRow = table.getSelectedRow();
        int idVendedor = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new VendedorService().apagar(new VendedorService().buscar(idVendedor));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadVendedor.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            this.tela.dispose();
        }
    }
    
}
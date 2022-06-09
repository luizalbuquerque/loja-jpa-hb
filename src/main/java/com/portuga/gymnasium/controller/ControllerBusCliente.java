package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Cliente;
import com.portuga.gymnasium.service.ClienteService;
import com.portuga.gymnasium.view.TelaBusCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Team Marcos / José / Luiz
 */
public class ControllerBusCliente implements ActionListener {

    private TelaBusCliente tela;
    private DefaultTableModel tabela;

    public ControllerBusCliente(TelaBusCliente tela) {
        this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);
        
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
                tela.getjButtonDeletar().addActionListener(a -> apaga());
    }
    
    private void apaga() {
        if (tela.getjTable1().getSelectedRow() < 0) {
            return;
        }

            JTable table = tela.getjTable1();
        int selectedRow = table.getSelectedRow();
        int idCliente = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new ClienteService().apagar(new ClienteService().buscar(idCliente));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }
    
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
    
}

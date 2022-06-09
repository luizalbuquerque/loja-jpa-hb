package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Endereco;
import com.portuga.gymnasium.service.EnderecoService;
import com.portuga.gymnasium.view.TelaBusEndereco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Team Marcos / José / Luiz
 */
public class ControllerBusEndereco implements ActionListener {
    
    TelaBusEndereco tela;
    private DefaultTableModel tabela;

    public ControllerBusEndereco(TelaBusEndereco tela) {
        this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);
        
        EnderecoService service = new EnderecoService();
        for (Endereco item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getIdCep(),
                    item.getCepCep(),
                    item.getLogradouroCep() });
        }

        tela.getjButtonDeletar().addActionListener(a -> apaga());
    }
    
      private void apaga() {
        if (tela.getjTable1().getSelectedRow() < 0) {
            return;
        }

        JTable table = tela.getjTable1();
        int selectedRow = table.getSelectedRow();
        int idEndereco = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new EnderecoService().apagar(new EnderecoService().buscar(idEndereco));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
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

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == this.tela.getjButtonCarregar()){
//            ControllerCadEndereco.codigo = (int) this.tela.getjTable1().getValueAt(
//                this.tela.getjTable1().getSelectedRow(),
//                0
//            );
//            ControllerCadCliente.codigoCep = ControllerCadEndereco.codigo;
//            ControllerCadVendedor.codigoCep = ControllerCadEndereco.codigo;
//            this.tela.dispose();
//        }
//    }
//    
//}

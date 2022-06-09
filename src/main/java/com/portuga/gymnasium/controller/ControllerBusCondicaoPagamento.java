package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.CondicaoPagamento;
import com.portuga.gymnasium.service.CondicaoPagamentoService;
import com.portuga.gymnasium.view.TelaBusCondicaoPagamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ControllerBusCondicaoPagamento implements ActionListener {

    TelaBusCondicaoPagamento tela;
    private DefaultTableModel tabela;

    public ControllerBusCondicaoPagamento(TelaBusCondicaoPagamento tela) {
        this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);
        
        CondicaoPagamentoService service = new CondicaoPagamentoService();
        for (CondicaoPagamento item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getId(),
                    item.getDescricao(),
                    item.getDiasAte(),
                    item.getDiasEntre()
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
        int idCondicaoPagamento = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new CondicaoPagamentoService().apagar(new CondicaoPagamentoService().buscar(idCondicaoPagamento));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }
    
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadCondicaoPagamento.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            this.tela.dispose();
        }
    }
    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == this.tela.getjButtonCarregar()){
//            ControllerCadCondicaoPagamento.codigo = (int) this.tela.getjTable1().getValueAt(
//                this.tela.getjTable1().getSelectedRow(),
//                0
//            );
//            ControllerVendas.codigoCondicao = ControllerCadCondicaoPagamento.codigo;
//            this.tela.dispose();
//        }
//    }    
}

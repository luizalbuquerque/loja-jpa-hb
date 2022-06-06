package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.CondicaoPagamento;
import com.portuga.gymnasium.service.CondicaoPagamentoService;
import com.portuga.gymnasium.view.TelaBusCondicaoPagamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


public class ControllerBusCondicaoPagamento implements ActionListener {

    private TelaBusCondicaoPagamento tela;

    public ControllerBusCondicaoPagamento(TelaBusCondicaoPagamento tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
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
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadCondicaoPagamento.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            ControllerVendas.codigoCondicao = ControllerCadCondicaoPagamento.codigo;
            this.tela.dispose();
        }
    }    
}

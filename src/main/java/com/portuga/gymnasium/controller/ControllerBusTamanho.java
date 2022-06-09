package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Tamanho;
import com.portuga.gymnasium.service.TamanhoService;
import com.portuga.gymnasium.view.TelaBusTamanho;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ControllerBusTamanho implements ActionListener {
    
    private TelaBusTamanho tela;
    private DefaultTableModel tabela;
    
    public ControllerBusTamanho(TelaBusTamanho tela) {
        this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);
        
        TamanhoService service = new TamanhoService();
        for (Tamanho item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getId(),
                    item.getDescricao()
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
        int idTamanho = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new TamanhoService().apagar(new TamanhoService().buscar(idTamanho));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
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

package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Bairro;
import com.portuga.gymnasium.service.BairroService;
import com.portuga.gymnasium.view.TelaBusBairro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Team Marcos / José / Luiz
 */
public class ControllerBusBairro implements ActionListener {
    
    TelaBusBairro tela;
    private DefaultTableModel tabela;

    public ControllerBusBairro(TelaBusBairro tela) {
        this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);
        //DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        BairroService bairroService = new BairroService();
        for (Bairro bairroAtualDaLista : bairroService.buscar()) {
            tabela.addRow(new Object[]{ bairroAtualDaLista.getIdBairro(),
                    bairroAtualDaLista.getDescricaoBairro(),
                    bairroAtualDaLista.getDescricaoBairro() });
        }

        tela.getjButtonDeletar().addActionListener(a -> apaga());
    }
    
        private void apaga() {
        if (tela.getjTable1().getSelectedRow() < 0) {
            return;
        }

            JTable table = tela.getjTable1();
        int selectedRow = table.getSelectedRow();
        int idBairro = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new BairroService().apagar(new BairroService().buscar(idBairro));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadBairro.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            this.tela.dispose();
        }
    }
    
}

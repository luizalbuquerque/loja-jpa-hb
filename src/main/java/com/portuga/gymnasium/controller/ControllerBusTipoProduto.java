package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.TipoProduto;
import com.portuga.gymnasium.service.TipoProdutoService;
import com.portuga.gymnasium.view.TelaBusTipoProduto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Team Marcos / José / Luiz
 */
public class ControllerBusTipoProduto implements ActionListener {
    
    TelaBusTipoProduto tela;
    private DefaultTableModel tabela;

    public ControllerBusTipoProduto(TelaBusTipoProduto tela) {
      this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);

        TipoProdutoService service = new TipoProdutoService();
        for (TipoProduto item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getId(),
                    item.getDescricao()});
        }
        tela.getjButtonDeletar().addActionListener(a -> apaga());
    }
    
    private void apaga() {
        if (tela.getjTable1().getSelectedRow() < 0) {
            return;
        }

            JTable table = tela.getjTable1();
        int selectedRow = table.getSelectedRow();
        int idTipoProduto = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new TipoProdutoService().apagar(new TipoProdutoService().buscar(idTipoProduto));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadTipoProduto.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            this.tela.dispose();
        }
    }
    
}

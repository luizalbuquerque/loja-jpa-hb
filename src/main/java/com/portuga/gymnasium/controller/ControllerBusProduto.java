package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Produto;
import com.portuga.gymnasium.service.ProdutoService;
import com.portuga.gymnasium.view.TelaBusProduto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class ControllerBusProduto implements ActionListener {
    
    private TelaBusProduto tela;
    private DefaultTableModel tabela;

    public ControllerBusProduto(TelaBusProduto tela) {
        this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);
        
        ProdutoService service = new ProdutoService();
        for (Produto item : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    item.getId(),
                    item.getDescricao(),
                    item.getVal()
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
        int idProduto = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new ProdutoService().apagar(new ProdutoService().buscar(idProduto));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == this.tela.getjButtonCarregar()){
//            ControllerCadProduto.codigo = (int) this.tela.getjTable1().getValueAt(
//                this.tela.getjTable1().getSelectedRow(),
//                0
//            );
//            ControllerVendas.codigoProduto = ControllerCadProduto.codigo;
//            this.tela.dispose();
//        }
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            ControllerCadProduto.codigo = (int) this.tela.getjTable1().getValueAt(
                this.tela.getjTable1().getSelectedRow(),
                0
            );
            ControllerVendas.codigoProduto = ControllerCadProduto.codigo;
            this.tela.dispose();
        }
    }
}

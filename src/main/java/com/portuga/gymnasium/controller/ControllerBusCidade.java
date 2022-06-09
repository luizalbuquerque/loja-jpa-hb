package com.portuga.gymnasium.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import com.portuga.gymnasium.model.bo.Cidade;
import com.portuga.gymnasium.service.CidadeService;
import com.portuga.gymnasium.view.TelaBusCidade;
import javax.swing.JTable;

public class ControllerBusCidade implements ActionListener{
    
    TelaBusCidade tela;
    private DefaultTableModel tabela;

    public ControllerBusCidade(TelaBusCidade tela) {
        this.tela = tela;
        tela.getjButtonCarregar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        this.tela.getjButtonCarregar().addActionListener(this);

        CidadeService cidadeService = new CidadeService();
        for (Cidade cidadeAtualDaLista : cidadeService.buscar()) {
            tabela.addRow(new Object[]{cidadeAtualDaLista.getIdCidade(),
                cidadeAtualDaLista.getDescricaoCidade(),
                cidadeAtualDaLista.getUfCidade()});
        }
        tela.getjButtonDeletar().addActionListener(a -> apaga());
    }
    
    private void apaga() {
        if (tela.getjTable1().getSelectedRow() < 0) {
            return;
        }
        JTable table = tela.getjTable1();
        int selectedRow = table.getSelectedRow();
        int idCidade = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
        new CidadeService().apagar(new CidadeService().buscar(idCidade));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.tela.getjButtonCarregar()){
            
         ControllerCadCidade.codigo = 
         (int)this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0);
         this.tela.dispose();
        }else if(e.getSource() == this.tela.getjButtonSair()){
            this.tela.dispose();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portuga.gymnasium.controller;

import com.portuga.gymnasium.model.bo.Bairro;
import com.portuga.gymnasium.service.BairroService;
import com.portuga.gymnasium.view.TelaBusBairro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author JACKSON
 */
public class ControllerBusBairro implements ActionListener {
    
    TelaBusBairro tela;

    public ControllerBusBairro(TelaBusBairro tela) {
        this.tela = tela;
        this.tela.getjButtonCarregar().addActionListener(this);
        
        DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
        
        BairroService service = new BairroService();
        for (Bairro bairro : service.buscar()) {
            tabela.addRow(
                new Object[]{
                    bairro.getIdBairro(),
                    bairro.getDescricaoBairro()
                }
            );
        }
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

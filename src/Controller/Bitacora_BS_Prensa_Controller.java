/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bitacora_BS_Prensa_Model;
import Model.DBConexion;
import View.Bitacora_BS_Prensa_View;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTHONY-MARTINEZ
 */
public class Bitacora_BS_Prensa_Controller {

    Bitacora_BS_Prensa_View prensa_View;
    Bitacora_BS_Prensa_Model prensa_Model = new Bitacora_BS_Prensa_Model();
    DBConexion conexion = new DBConexion();

    public Bitacora_BS_Prensa_Controller(Bitacora_BS_Prensa_View prensa_View) {
        this.prensa_View = prensa_View;
        iniciarEscuchadores();
    }

    private void iniciarEscuchadores() {
        prensa_View.jButtonAtras.addActionListener(e -> regresar());
        prensa_View.jButtonBuscar.addActionListener(e -> generarBitacora());
        prensa_View.jButtonExport.addActionListener(e -> exportarBitacora());
        prensa_View.jTextFieldBusquedaMOG.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                generarBitacoraMOG();
            }
        });
    }

    private void generarBitacora() {
        if (prensa_View.jCheckBoxOPnormal.isSelected()) {
            prensa_Model.consultaYLlenadoDeTablasNormal(prensa_View);
        } else {
            if (prensa_View.jCheckBox4lotes.isSelected()) {
                prensa_Model.consultaYLlenadoDeTablas4Lot(prensa_View);
            } else {
                JOptionPane.showMessageDialog(null, "Debes elegir un tipo de orden");
            }
        }
    }

    private void generarBitacoraMOG() {
        prensa_Model.busquedaMOG(prensa_View);
    }

    private void exportarBitacora() {
        prensa_Model.reportePrensa(prensa_View.jTableReporte);
    }

    private void regresar() {
        prensa_View.setVisible(false);
    }
}

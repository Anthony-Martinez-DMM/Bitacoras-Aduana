/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bitacora_HB_Prensa_Model;
import Model.DBConexion;
import View.Bitacora_HB_Prensa_View;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author CRISTOPHER-ALVARADO
 */
public class Bitacora_HB_Prensa_Controller {

    Bitacora_HB_Prensa_View prensa_view;
    Bitacora_HB_Prensa_Model prensa_model = new Bitacora_HB_Prensa_Model();
    DBConexion conexion = new DBConexion();

    public Bitacora_HB_Prensa_Controller(Bitacora_HB_Prensa_View prensa_view) {
        this.prensa_view = prensa_view;
        inicializar_escuchadores();
    }

    private void inicializar_escuchadores() {
        prensa_view.jButtonBuscar.addActionListener(e -> buscar());
        prensa_view.jButtonAtras.addActionListener(e -> atras());
        prensa_view.jButtonExport.addActionListener(e -> exportar());
        prensa_view.jTextFieldBusquedaMOG.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                generarBitacoraPorMOG(e);
            }
        });

    }

    private void buscar() {
        if (prensa_view.jCheckBoxOPnormal.isSelected()) {
            prensa_model.consultaYLlenadoDeTablasNormal(conexion, prensa_view);
        } else if (prensa_view.jCheckBox4lotes.isSelected()) {
            prensa_model.consultaYLlenadoDeTablas4Lot(conexion, prensa_view);
        }
    }

    private void atras() {
        prensa_view.setVisible(false);

    }

    private void exportar() {
      prensa_model.reportePrensa(prensa_view.jTableReporte);
    }

    private void generarBitacoraPorMOG(KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_ENTER) {
             return;
        }
        prensa_model.busquedaMOG(conexion, prensa_view);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bitacora_BS_Empaque_Model;
import Model.DBConexion;
import View.Bitacora_BS_Empaque_View;
import java.awt.event.KeyEvent;

/**
 *
 * @author ANTHONY-MARTINEZ
 */
public class Bitacora_BS_Empaque_Controller {

    Bitacora_BS_Empaque_View empaque_View;
    Bitacora_BS_Empaque_Model empaque_Model = new Bitacora_BS_Empaque_Model();
    DBConexion conexion = new DBConexion();

    public Bitacora_BS_Empaque_Controller(Bitacora_BS_Empaque_View empaque_View) {
        this.empaque_View = empaque_View;
        iniciarEscuchadores();
    }

    private void iniciarEscuchadores() {
        empaque_View.jButtonAtras.addActionListener(e -> regresar());
        empaque_View.jButtonBuscar.addActionListener(e -> generarBitacora());
        empaque_View.jButtonExport.addActionListener(e -> exportarBitacora());
        empaque_View.jTextFieldBusquedaMOG.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                generarBitacoraMOG();
            }
        });
    }

    private void generarBitacora() {
        empaque_Model.consultaYLlenadoDeTablas(empaque_View);
    }

    private void generarBitacoraMOG() {
        empaque_Model.consultaYLlenadoDeTablasByMOG(empaque_View);
    }

    private void exportarBitacora() {
        conexion.reporte(empaque_View.jTableReporte);
    }

    private void regresar() {
        empaque_View.setVisible(false);
    }
}

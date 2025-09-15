/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bitacora_BS_Procesos_Model;
import Model.DBConexion;
import View.Bitacora_BS_Procesos_View;
import java.awt.event.KeyEvent;

/**
 *
 * @author anthony
 */
public class Bitacora_BS_Procesos_Controller {
    Bitacora_BS_Procesos_View procesos_View;
    Bitacora_BS_Procesos_Model procesos_Model = new Bitacora_BS_Procesos_Model();
    DBConexion conexion = new DBConexion();

    public Bitacora_BS_Procesos_Controller(Bitacora_BS_Procesos_View procesos_View) {
        this.procesos_View = procesos_View;
        iniciarEscuchadores();
    }

    private void iniciarEscuchadores() {
        procesos_View.jButtonAtras.addActionListener(e -> regresar());
        procesos_View.jButtonBuscar.addActionListener(e -> generarBitacora());
        procesos_View.jButtonExport.addActionListener(e -> exportarBitacora());
        procesos_View.jTextFieldBusquedaMOG.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                generarBitacoraMOG();
            }
        });
    }

    private void generarBitacora() {
        procesos_Model.consultaYLlenadoDeTablasNuevo(procesos_View);
    }

    private void generarBitacoraMOG() {
        procesos_Model.consultaYLlenadoDeTablasByMOGnuevo(procesos_View);
    }

    private void exportarBitacora() {
        conexion.reporte(procesos_View.jTableReporte);
    }

    private void regresar() {
        procesos_View.setVisible(false);
    }
}

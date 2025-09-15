/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bitacora_BS_Maquinado_Model;
import Model.DBConexion;
import View.Bitacora_BS_Maquinado_View;
import java.awt.event.KeyEvent;

/**
 *
 * @author anthony
 */
public class Bitacora_BS_Maquinado_Controller {
    Bitacora_BS_Maquinado_View maquinado_View;
    Bitacora_BS_Maquinado_Model maquinado_Model = new Bitacora_BS_Maquinado_Model();
    DBConexion conexion = new DBConexion();

    public Bitacora_BS_Maquinado_Controller(Bitacora_BS_Maquinado_View maquinado_View) {
        this.maquinado_View = maquinado_View;
        iniciarEscuchadores();
    }

    private void iniciarEscuchadores() {
        maquinado_View.jButtonAtras.addActionListener(e -> regresar());
        maquinado_View.jButtonBuscar.addActionListener(e -> generarBitacora());
        maquinado_View.jButtonExport.addActionListener(e -> exportarBitacora());
        maquinado_View.jTextFieldBusquedaMOG.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                generarBitacoraMOG();
            }
        });
    }

    private void generarBitacora() {
        maquinado_Model.consultaYLlenadoDeTablas(maquinado_View);
    }

    private void generarBitacoraMOG() {
        maquinado_Model.consultaYLlenadoDeTablasByMOG(maquinado_View);
    }

    private void exportarBitacora() {
        conexion.reporte(maquinado_View.jTableReporte);
    }

    private void regresar() {
        maquinado_View.setVisible(false);
    }
}

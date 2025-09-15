/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bitacora_HB_Maquinado_Model;
import Model.DBConexion;
import View.Bitacora_HB_Maquinado_View;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anthony
 */
public class Bitacora_HB_Maquinado_Controller {

    private final Bitacora_HB_Maquinado_View vista;
    private final Bitacora_HB_Maquinado_Model modelo;

    public Bitacora_HB_Maquinado_Controller(Bitacora_HB_Maquinado_View vista) {
        this.vista = vista;
        this.modelo = new Bitacora_HB_Maquinado_Model();
        iniciarEscuchadores();
    }

    private void iniciarEscuchadores() {
        vista.jButtonBuscar.addActionListener(e -> generarBitacoraPorFechas());
        vista.jButtonExport.addActionListener(e -> exportarBitacora());
        vista.jTextFieldBusquedaMOG.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                generarBitacoraPorMOG();
            }
        });
    }

    private void generarBitacoraPorFechas() {
        Date fi = vista.jDateChooserInicio.getDate();
        Date ff = vista.jDateChooserFin.getDate();
        DefaultTableModel modeloTabla = modelo.consultaPorFechas(fi, ff);
        configurarTabla(modeloTabla);
    }

    private void generarBitacoraPorMOG() {
        String mog = vista.jTextFieldBusquedaMOG.getText();
        DefaultTableModel modeloTabla = modelo.consultaPorMOG(mog);
        configurarTabla(modeloTabla);
    }

    private void exportarBitacora() {
        DBConexion conexion = new DBConexion();
        conexion.reporte(vista.jTableReporte);
    }

    private void configurarTabla(DefaultTableModel modeloTabla) {
        vista.jTableReporte.setModel(modeloTabla);
        vista.jTableReporte.setRowHeight(20);
        vista.jTableReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < vista.jTableReporte.getColumnCount(); i++) {
            vista.jTableReporte.getColumnModel().getColumn(i).setPreferredWidth(180);
        }
    }
}

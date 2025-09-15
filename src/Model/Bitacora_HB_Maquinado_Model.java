/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import View.Bitacora_BS_Maquinado_View;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANTHONY-MARTINEZ
 */
public class Bitacora_HB_Maquinado_Model {

    private final DBConexion conexion = new DBConexion();

    public DefaultTableModel consultaPorFechas(Date fechaInicio, Date fechaFin) {
        DefaultTableModel dtm = crearModeloTabla();
        if (fechaInicio == null || fechaFin == null) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar fechas válidas");
            return dtm;
        }

        try (Connection connectionDB = conexion.conexionHBMySQL()) {
            DateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");

            String fecha_inicio = hourFormat.format(fechaInicio);
            String fecha_fin = hourFormat.format(fechaFin);

            CallableStatement cst = connectionDB.prepareCall("call reporteBitacoraMaquinado(?,?)");
            cst.setString(1, fecha_inicio);
            cst.setString(2, fecha_fin);
            ResultSet rst = cst.executeQuery();

            while (rst.next()) {
                dtm.addRow(mapearRegistro(rst, dayFormat));
            }
        } catch (SQLException e) {
            System.err.println("Error en consultaPorFechas: " + e.getMessage());
        }
        return dtm;
    }

    public DefaultTableModel consultaPorMOG(String mog) {
        DefaultTableModel dtm = crearModeloTabla();
        try (Connection connectionDB = conexion.conexionHBMySQL()) {
            DateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");

            CallableStatement cst = connectionDB.prepareCall("call reporteBitacoraMaquinado2(?)");
            cst.setString(1, "%" + mog + "%");
            ResultSet rst = cst.executeQuery();

            while (rst.next()) {
                dtm.addRow(mapearRegistro(rst, dayFormat));
            }
        } catch (SQLException e) {
            System.err.println("Error en consultaPorMOG: " + e.getMessage());
        }
        return dtm;
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel dtm = new DefaultTableModel();
        String[] columnas = {
            "MOG", "#Parte", "Lote", "OP#", "MOG RBP", "OP# RBP", "WC",
            "F.Date", "Sobrante Inicial", "Sobrante Final", "Received Qty.", "Finished Qty.",
            "WHHH:", "WH:mm(60)", "Sob Inicial +1", "STD +1", "Sob Final +1", "Sob Inicial -1", "STD -1", "Sob Final -1",
            "Proccessed RodGuide", "QTY. SCRAP", "SCRAPS RBP HOJA AZUL", "VERIF. DE CALCULOS", "VERIF. DE PROCESOS"
        };
        for (String col : columnas) {
            dtm.addColumn(col);
        }
        return dtm;
    }

    private Object[] mapearRegistro(ResultSet rst, DateFormat dayFormat) throws SQLException {
        Object[] data = new Object[25];
        data[0] = rst.getString("mog");
        String noParte = rst.getString("no_parte").replace("-", "'");
        data[1] = noParte;

        data[2] = rst.getString("loteTM");
        data[3] = rst.getString("orden_manufactura");
        data[4] = rst.getString("mog");
        data[5] = rst.getString("orden_manufactura");
        data[6] = rst.getString("linea");

        Date fecha = rst.getDate("fecha");
        data[7] = dayFormat.format(fecha);

        int piezasProcesadas = rst.getInt("piezas_procesadas");
        int piezasAprobadas = rst.getInt("piezas_aprobadas");
        int piezasRecibidas = rst.getInt("piezas_recibidas");
        int totalScrap = rst.getInt("total_scrap");
        int cambioMOG = rst.getInt("cambio_mog");
        
        data[10] = String.valueOf(piezasProcesadas);
        data[11] = String.valueOf(piezasAprobadas);
        data[12] = rst.getInt("horas_trabajadas");
        data[13] = rst.getInt("minutos_trabajados");
        data[21] = String.valueOf(totalScrap);

        int verifCalculos = piezasProcesadas - piezasAprobadas - totalScrap - cambioMOG;
        int verifProcesos = piezasRecibidas - piezasProcesadas;
        
        data[23] = String.valueOf(verifCalculos);
        data[24] = String.valueOf(verifProcesos);

        return data;
    }
}

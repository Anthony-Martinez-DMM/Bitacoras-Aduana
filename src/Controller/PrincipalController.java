/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Bitacora_BS_Empaque_View;
import View.Bitacora_BS_Procesos_View;
import View.Menu_BS_View;
import View.Menu_Principal_View;
import View.Menu_HB_View;
import View.Bitacora_HB_Prensa_View;
import View.Bitacora_BS_Maquinado_View;
import View.Bitacora_BS_Prensa_View;
import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author DMM-ADMIN
 */
public class PrincipalController implements KeyListener, ActionListener {
    Menu_BS_View vistaElegirReporte;
    DBConexion m;
    Bitacora_BS_Empaque_Model mdl_pck;
    Bitacora_BS_Empaque_View vw_pck;
    Bitacora_BS_Prensa_Model mdl_prs;
    Bitacora_BS_Prensa_View vw_prs;
    Bitacora_BS_Maquinado_Model mdl_maq;
    Bitacora_BS_Maquinado_View vw_maq;
    Bitacora_BS_Procesos_View vw_bush;
    Menu_Principal_View vistaElegirReporteLogin;
    Bitacora_HB_Prensa_View vw_pressHB;
    Bitacora_HB_Prensa_Model mdl_prs_hb;
    Menu_HB_View elegirReporteHBView;
    
    int banderaPro;
    int conet;
    
    public PrincipalController(Menu_BS_View vistaElegirReporte, DBConexion m, Bitacora_BS_Empaque_Model mdl_pck, Bitacora_BS_Empaque_View vw_pck, 
            Bitacora_BS_Prensa_Model mdl_prs, Bitacora_BS_Prensa_View vw_prs, Bitacora_BS_Maquinado_Model mdl_maq, Bitacora_BS_Maquinado_View vw_maq, Bitacora_BS_Procesos_View vw_bush, 
            Menu_Principal_View vistaElegirReporteLogin, Bitacora_HB_Prensa_View vw_pressHB, Bitacora_HB_Prensa_Model mdl_prs_hb, Menu_HB_View elegirReporteHB) {
        this.vistaElegirReporte = vistaElegirReporte;
        this.m = m;
        this.mdl_pck = mdl_pck;
        this.vw_pck = vw_pck;
        this.mdl_prs = mdl_prs;
        this.vw_prs = vw_prs;
        this.mdl_maq = mdl_maq;
        this.vw_maq = vw_maq;
        this.vw_bush=vw_bush;
        this.vistaElegirReporteLogin=vistaElegirReporteLogin;
        this.vw_pressHB=vw_pressHB;
        this.mdl_prs_hb=mdl_prs_hb;
        this.elegirReporteHBView = elegirReporteHB;
        vistaElegirReporteLogin.setVisible(true);
        escuchadores();
    }

    public void escuchadores(){
        ///////////////PCK////////////////////////////////////
        vistaElegirReporte.btnPCK.addActionListener(this);
        vistaElegirReporteLogin.btnBushing.addActionListener(this);
        vistaElegirReporteLogin.btnHalfbearing.addActionListener(this);
        vistaElegirReporte.btnRegresar.addActionListener(this);
        vw_pck.jButtonAtras.addActionListener(this);
        vw_pck.jButtonBuscar.addActionListener(this);
        vw_pck.jButtonExport.addActionListener(this);
        vw_pck.jTextFieldBusquedaMOG.addKeyListener(this);
        //////////PRENSA////////////
        vistaElegirReporte.btnPRS.addActionListener(this);
        vw_prs.jButtonAtras.addActionListener(this);
        vw_prs.jButtonBuscar.addActionListener(this);
        vw_prs.jButtonExport.addActionListener(this);
        vw_prs.jTextFieldBusquedaMOG.addKeyListener(this);
        //////////////MAQUINADO/////////////////////
        vistaElegirReporte.btnHBL.addActionListener(this);
        vw_maq.jButtonAtras.addActionListener(this);
        vw_maq.jButtonBuscar.addActionListener(this);
        vw_maq.jButtonExport.addActionListener(this);
        vw_maq.jTextFieldBusquedaMOG.addKeyListener(this);
        //////////////////BUSH//////////////////////////
        vistaElegirReporte.btnBUSH.addActionListener(this);
        vw_bush.jButtonAtras.addActionListener(this);
        vw_bush.jButtonExport.addActionListener(this);
        vw_bush.jTextFieldBusquedaMOG.addKeyListener(this);
        vw_bush.jButtonBuscar.addActionListener(this);
        /////////////////PRENSA HB/////////////////////
        vw_pressHB.jButtonAtras.addActionListener(this);
        vw_pressHB.jButtonBuscar.addActionListener(this);
        vw_pressHB.jButtonExport.addActionListener(this);
        vw_pressHB.jTextFieldBusquedaMOG.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            
            if(conet==1){
                if(banderaPro==1){
                    mdl_prs_hb.busquedaMOG(m, vw_pressHB);
                }
                if(banderaPro==8){
                    mdl_maq.consultaYLlenadoDeTablasByMOGHB(m, vw_maq);
                }
            }
            
            if(conet==2){
                if(banderaPro==2){
                    mdl_maq.consultaYLlenadoDeTablasByMOG(m, vw_maq);
                }
                
                if(banderaPro==4){
                    mdl_pck.consultaYLlenadoDeTablasByMOG(m, vw_pck);
                }
                if(banderaPro==3){
                    //mdl_maq.consultaBushByMOG(m, vw_bush);
                    mdl_maq.consultaYLlenadoDeTablasByMOGnuevo(m, vw_bush);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == vistaElegirReporte.btnRegresar){
            vistaElegirReporte.setVisible(false);
            vistaElegirReporteLogin.setVisible(true);
        }
        
        ///////////////////PRS HB////////////////////////////
        
        if(e.getSource() == vw_pressHB.jButtonAtras){
            vw_prs.setVisible(false);
            mdl_prs_hb.limpiarTabla(vw_pressHB);
            vistaElegirReporte.setVisible(true);
        }
        
        if(e.getSource() == vw_pressHB.jButtonBuscar){
            if(vw_pressHB.jCheckBoxOPnormal.isSelected()){
                mdl_prs_hb.consultaYLlenadoDeTablasNormal(m, vw_pressHB);
            }else{
                if(vw_pressHB.jCheckBox4lotes.isSelected()){
                mdl_prs_hb.consultaYLlenadoDeTablas4Lot(m, vw_pressHB);
                }else{
                    JOptionPane.showMessageDialog(null,"Debes elegir un tipo de orden");
                }
            }
        }
        
        if (e.getSource() == vw_pressHB.jButtonExport) {
            int h = vw_pressHB.jTableReporte.getRowCount();
            if (h == 0) {
                JOptionPane.showMessageDialog(null, "No hay datos en la tabla");
            } else {
                mdl_prs.reportePrensa(vw_pressHB.jTableReporte);
            }
        }
        
        /////////////////////PCK//////////////////
        if(e.getSource() == vistaElegirReporteLogin.btnBushing){
            conet=2;
            banderaPro=2;
            vistaElegirReporte.setVisible(true);
            vistaElegirReporte.btnBUSH.setVisible(true);
            vistaElegirReporteLogin.setVisible(false);
            
        }
        
        if(e.getSource() == vistaElegirReporteLogin.btnHalfbearing){
            conet=1;
            banderaPro=1;
            vistaElegirReporte.setVisible(true);
            vistaElegirReporte.btnBUSH.setVisible(false);
            vistaElegirReporteLogin.setVisible(false);
        }
        
        if(e.getSource() == vistaElegirReporte.btnPCK){
            banderaPro=4;
            vw_pck.setVisible(true);
            vistaElegirReporte.setVisible(false);
        }
        
        if(e.getSource() == vw_pck.jButtonAtras){
            vw_pck.setVisible(false);
            mdl_pck.limpiarTabla(vw_pck);
            vistaElegirReporte.setVisible(true);
        }
        
        if(e.getSource() == vw_pck.jButtonBuscar){
            mdl_pck.consultaYLlenadoDeTablas(m, vw_pck);
        }
        
        if (e.getSource() == vw_pck.jButtonExport) {
            int h = vw_pck.jTableReporte.getRowCount();
            if (h == 0) {
                JOptionPane.showMessageDialog(null, "No hay datos en la tabla");
            } else {
                m.reporte(vw_pck.jTableReporte);
            }
        }
        
        ///////////////////PRS////////////////////////////
        
        if(e.getSource() == vistaElegirReporte.btnPRS){
            if(banderaPro==1){
                vw_pressHB.setVisible(true);
            }else{
                banderaPro=5;
                vw_prs.setVisible(true);
            }
           
            vistaElegirReporte.setVisible(false);
        }
        
        if(e.getSource() == vw_prs.jButtonAtras){
            vw_prs.setVisible(false);
            mdl_prs.limpiarTabla(vw_prs);
            vistaElegirReporte.setVisible(true);
        }
        
        if(e.getSource() == vw_prs.jButtonBuscar){
            if(vw_prs.jCheckBoxOPnormal.isSelected()){
                mdl_prs.consultaYLlenadoDeTablasNormal(m, vw_prs);
            }else{
                if(vw_prs.jCheckBox4lotes.isSelected()){
                mdl_prs.consultaYLlenadoDeTablas4Lot(m, vw_prs);
                }else{
                    JOptionPane.showMessageDialog(null,"Debes elegir un tipo de orden");
                }
            }
        }
        
        if (e.getSource() == vw_prs.jButtonExport) {
            int h = vw_prs.jTableReporte.getRowCount();
            if (h == 0) {
                JOptionPane.showMessageDialog(null, "No hay datos en la tabla");
            } else {
                mdl_prs.reportePrensa(vw_prs.jTableReporte);
            }
        }
        
        
        
       //////////////////maquinado/////////////////////////
       if(e.getSource() == vistaElegirReporte.btnHBL){
           if(conet==1){
               banderaPro=8;
               vw_maq.setVisible(true);
           }else{
               banderaPro=2;
               vw_maq.setVisible(true);
           }
           
            
            vistaElegirReporte.setVisible(false);
        }
       
       
        
       
        if(e.getSource() == vw_maq.jButtonAtras){
            vw_maq.setVisible(false);
            mdl_maq.limpiarTablaMachining(vw_maq);
            vistaElegirReporte.setVisible(true);
        }
        
        if(e.getSource() == vw_maq.jButtonBuscar){
            if(conet==2){
                mdl_maq.consultaYLlenadoDeTablas(m, vw_maq);
            }else{
                mdl_maq.consultaYLlenadoDeTablasHBMaquinado(m, vw_maq);
            }
            
            //mdl_maq.consultaYLlenadoDeTablasNuevo(m, vw_maq);
        }
        
        if (e.getSource() == vw_maq.jButtonExport) {
            int h = vw_maq.jTableReporte.getRowCount();
            if (h == 0) {
                JOptionPane.showMessageDialog(null, "No hay datos en la tabla");
            } else {
                m.reporte(vw_maq.jTableReporte);
            }
        }
        
        //////////////////bush/////////////////////////
        if(e.getSource() == vistaElegirReporte.btnBUSH){
           banderaPro=3;
            vw_bush.setVisible(true);
            vistaElegirReporte.setVisible(false);
        }
        
        if(e.getSource() == vw_bush.jButtonAtras){
            vw_bush.setVisible(false);
            mdl_maq.limpiarTablaBushing(vw_bush);
            vistaElegirReporte.setVisible(true);
        }
        
        if(e.getSource() == vw_bush.jButtonBuscar){
            //mdl_maq.consultaBushByFechas(m, vw_bush);
            mdl_maq.consultaYLlenadoDeTablasNuevo(m, vw_bush);
        }
        
        if (e.getSource() == vw_bush.jButtonExport) {
            int h = vw_bush.jTableReporte.getRowCount();
            if (h == 0) {
                JOptionPane.showMessageDialog(null, "No hay datos en la tabla");
            } else {
                m.reporte(vw_bush.jTableReporte);
            }
        }
    }
}

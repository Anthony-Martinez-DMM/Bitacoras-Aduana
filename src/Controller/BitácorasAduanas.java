/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DBConexion;
import Model.Bitacora_BS_Empaque_Model;
import Model.ModelBitacoraHBPrensa;
import Model.ModelBitacoraMaquinado;
import Model.Bitacora_BS_Prensa_Model;
import View.Menu_BS_View;
import View.Menu_HB_View;
import View.Menu_Principal_View;
import View.ReporteBitacoraBush;
import View.Bitacora_BS_Empaque_View;
import View.ReporteBitacoraMaquinado;
import View.Bitacora_BS_Prensa_View;
import View.ReporteBitacoraPrensaHB;

/**
 *
 * @author DMM-ADMIN
 */
public class BitácorasAduanas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu_Principal_View menu_Principal_View = new Menu_Principal_View();
        Menu_Principal_Controller menu_Principal_Controller = new Menu_Principal_Controller(menu_Principal_View);
        menu_Principal_View.setVisible(true);
    }
}

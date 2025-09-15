/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Bitacora_BS_Empaque_View;
import View.Bitacora_BS_Maquinado_View;
import View.Bitacora_BS_Prensa_View;
import View.Menu_BS_View;
import View.Menu_HB_View;
import javax.swing.JOptionPane;

/**
 *
 * @author anthony
 */
public class Menu_BS_Controller {

    private final Menu_BS_View menu_BS_View;

    public Menu_BS_Controller(Menu_BS_View menu_BS_View) {
        this.menu_BS_View = menu_BS_View;
        inicializarEscuchadores();
    }

    private void inicializarEscuchadores() {
        menu_BS_View.btnPCK.addActionListener(e -> abrirBitacora("PCK"));
        menu_BS_View.btnPRS.addActionListener(e -> abrirBitacora("PRS"));
        menu_BS_View.btnHBL.addActionListener(e -> abrirBitacora("HBL"));
        menu_BS_View.btnRegresar.addActionListener(e -> regresar());
    }

    private void abrirBitacora(String proceso) {
        switch (proceso) {
            case "PCK":
                Bitacora_BS_Empaque_View empaque_View = new Bitacora_BS_Empaque_View();
                Bitacora_BS_Empaque_Controller empaque_Controller = new Bitacora_BS_Empaque_Controller(empaque_View);
                empaque_View.setVisible(true);
                break;
            case "PRS":
                Bitacora_BS_Prensa_View prensa_View = new Bitacora_BS_Prensa_View();
                Bitacora_BS_Prensa_Controller prensa_Controller = new Bitacora_BS_Prensa_Controller(prensa_View);
                prensa_View.setVisible(true);
                break;
            case "HBL":
                Bitacora_BS_Maquinado_View maquinado_View = new Bitacora_BS_Maquinado_View();
                Bitacora_BS_Maquinado_Controller maquinado_Controller = new Bitacora_BS_Maquinado_Controller(maquinado_View);
                maquinado_View.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Proceso desconocida");
        }
    }

    private void regresar() {
        menu_BS_View.setVisible(false);
    }

}

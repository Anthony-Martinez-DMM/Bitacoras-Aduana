/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Menu_HB_View;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTHONY-MARTINEZ
 */
public class Menu_HB_Controller {
    
    private final Menu_HB_View menu_HB_View;

    public Menu_HB_Controller(Menu_HB_View menu_HB_View) {
        this.menu_HB_View = menu_HB_View;
        inicializarEscuchadores();
    }
    
    private void inicializarEscuchadores(){
        menu_HB_View.btnPRS.addActionListener(e -> abrirBitacora("PRS"));
        menu_HB_View.btnHBL.addActionListener(e -> abrirBitacora("HBL"));
        menu_HB_View.btnRegresar.addActionListener(e -> regresar());
    }
    
    private void abrirBitacora(String proceso) {
        switch (proceso) {
            case "PRS":
                // TODO
                break;
            case "HBL":
                // TODO
                break;
            default:
                JOptionPane.showMessageDialog(null, "Área desconocida");
        }
    }
    
    private void regresar(){
        menu_HB_View.setVisible(false);
    }
    
}

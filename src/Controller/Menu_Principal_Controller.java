/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Menu_BS_View;
import View.Menu_HB_View;
import View.Menu_Principal_View;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTHONY-MARTINEZ
 */
public class Menu_Principal_Controller {
    
    private final Menu_Principal_View menu_Principal_View;
    
    public Menu_Principal_Controller(Menu_Principal_View menu_Principal_View) {
        this.menu_Principal_View = menu_Principal_View;
        inicializarEscuchadores();
    }
    
    private void inicializarEscuchadores() {
        menu_Principal_View.btnBushing.addActionListener(e -> abrirMenu("BS"));
        menu_Principal_View.btnHalfbearing.addActionListener(e -> abrirMenu("HB"));
    }
    
    private void abrirMenu(String area) {
        switch (area) {
            case "BS":
                Menu_BS_View menu_BS_View = new Menu_BS_View();
                Menu_BS_Controller menu_BS_Controller = new Menu_BS_Controller(menu_BS_View);
                menu_BS_View.setVisible(true);
                break;
            case "HB":
                Menu_HB_View menu_HB_View = new Menu_HB_View();
                Menu_HB_Controller menu_HB_Controller = new Menu_HB_Controller(menu_HB_View);
                menu_HB_View.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Área desconocida");
        }
    }
}

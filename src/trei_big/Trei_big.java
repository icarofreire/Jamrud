/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.banco;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import menu_modulos.*;

/**
 *
 * @author icaro
 */
public class Trei_big {
    
    private static String titulo = "Sistema de Cadastro";
    
    public static void menu(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               menu m = new menu(titulo);
               m.setVisible(true);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          banco.conectar();
          menu();
//        for (int count = 0; count < 20; count++) {banco.iniciar();}
//carregando.main(args);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
    
//    public static void lista(){
//        String[] colunas = new String[] {
//            "Id", "Name", "Hourly Rate", "Part Time", "Endereço", "CPF", "Sobrenome", "Caso Pendente",
//            "campo-1", "campo-2", "campo-3", "campo-4"
//        };
//        
//        ArrayList<Object[]> dados_da_tabela = new ArrayList<Object[]>();
//        
//        for (int count = 0; count < 200; count++) {
//            Object[] data = new Object[]{ (count+1), "John", 40.0, false, "testee", "testee", "testee", "testee",
//            "--teste-1", "--teste-2", "--teste-3", "--teste-4"}; 
//            dados_da_tabela.add(data);
//        }        
//        
//        exibir_listagem l = new exibir_listagem("Lista de Clientes", colunas, dados_da_tabela);
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//          menu();
//        new sobre();
          new painel_elementos().gui_elementos();
        

        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

/**
 *
 * @author icaro
 */
public class executar_atras implements Runnable {

    private final int um_segundo = 1000;
    public JList lista;
    private JPanel painel;
    public static boolean se_comecou = false;

    public executar_atras(JList lista, JPanel painel) {
        this.lista = lista;
        this.painel = painel;
    }

    public JList getLista() {
        return lista;
    }
    
    
    
    @Override
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(um_segundo * 2);
                
                se_comecou = true;
                this.lista = new menu_modulos.MenuLateral().lista();
                
                operacoes_painel.atualizar_painel(painel);

                System.out.println("executando...");
            } catch (InterruptedException ex) {
               break;
            }
        }
    }
    
}

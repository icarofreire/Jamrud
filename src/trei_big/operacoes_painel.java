/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author icaro
 */
public class operacoes_painel {
    
    public static void atualizar_painel(JPanel painel) {
        painel.revalidate();
        painel.repaint();
    }
    
    public static JScrollPane painel_com_scroll_sem_borda(JPanel painel) {
        JScrollPane scroll = new JScrollPane(painel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
        return scroll;
    }
    
    /* \/ Verifica se existe um componente X no painel; 
       ATENÇÃO: A verificação é realizada atraves do nome do componente, que é definido pelo metodo 'setName'.
    Exemplo:
        JPanel painel = new JPanel();
        painel.setName("painel_interno"); <= Este metodo define o nome do componente(Similar a um 'ID').
    através deste nome, será realizada a busca no painel para ver se ele existe;
    \/ \/ \/ */
    public static boolean se_componente_em_painel(JPanel painel, String name_componente_no_painel) {
            boolean f = false;
            Component[] components = painel.getComponents();
            for (int i=0; i < components.length; i++) {
                   String name_componente = components[i].getName();
                   if( (name_componente != null) && name_componente.equalsIgnoreCase(name_componente_no_painel) ){
                       f = true; break;
                   }
            }
            return f;
    }
    
    public static int obj_to_int(Object inteiro) {
        int valor = Integer.parseInt(inteiro.toString());
        return valor;
    }
    
}

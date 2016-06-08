/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import ferramenta_gui.GBHelper;
import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    
    public static JPanel remover_componentes_painel(JPanel painel){
        if(painel.getComponents().length >= 1){
            painel.removeAll();
            operacoes_painel.atualizar_painel(painel);
        }
        return painel;
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
    
    public static Component pegar_componente_em_painel(JPanel painel, String name_componente_no_painel) {
        Component x = null;
        Component[] components = painel.getComponents();
            for (int i=0; i < components.length; i++) {
                String name_componente = components[i].getName();
                if( (name_componente != null) && (name_componente.indexOf(name_componente_no_painel) != -1)  ){
                    x = components[i];
                }
            }//fim for;
        return x;
    }
    
    public static int obj_to_int(Object inteiro) {
        int valor = Integer.parseInt(inteiro.toString());
        return valor;
    }
    
    public static JPanel add_painel_filho_ao_PAI(JPanel painel_principal, JPanel painel_filho, String ID_painel_filho, GBHelper pos){
        operacoes_painel.remover_componentes_painel(painel_principal);
        JScrollPane scroll_painel_backup = operacoes_painel.painel_com_scroll_sem_borda(painel_filho);
        scroll_painel_backup.setName(ID_painel_filho);
                        
        if( !operacoes_painel.se_componente_em_painel(painel_principal, ID_painel_filho) ){
            painel_principal.add(scroll_painel_backup, pos.expandir());
        }
        return painel_principal;
    }
    
    
    public static String serializar_obj(Object myObject) {
        String serializedObject = "";
         // serialize the object
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(myObject);
            so.flush();
            serializedObject = new String(Base64.encode(bo.toByteArray()));//bo.toString();
        } catch (Exception e) {
            System.out.println("->" + e);
        }
        return serializedObject;
    }
    
    public static Object deserializar_obj(String serializedObject) {
        Object myObject = null;
        // deserialize the object
         try {
             byte b[] = Base64.decode(serializedObject);
             ByteArrayInputStream bi = new ByteArrayInputStream(b);
             ObjectInputStream si = new ObjectInputStream(bi);
             myObject = si.readObject();
         } catch (Exception e) {
             System.out.println("x->" + e);
         }
         return myObject;
    }
    
}

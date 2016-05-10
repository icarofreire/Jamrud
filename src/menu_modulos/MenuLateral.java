/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_modulos;

import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author icaro
 */
public class MenuLateral {
    
    private Map<String, ImageIcon> map = new HashMap<>();
    private Map<String, Integer> modulo = new HashMap<>();
    
    private Vector<String> nomes = new Vector<String>();
    private Vector<String> icones = new Vector<String>();
    
    public JList lista() {
        
        /* \/ A ordem em que forem adicionados será a ordem em que aparecerá no menu. \/ */
        nomes.add("Cadastrar"); /* => */ icones.add("icones/cadastrar.png");
        nomes.add("Pesquisar/Editar"); /* => */ icones.add("icones/pesquisar_e_editar.png");
//        nomes.add("Lixeira"); /* => */ icones.add("icones/lixo2.png");
        nomes.add("Backup"); /* => */ icones.add("icones/1460597575_document-save.png");
        nomes.add("Temas"); /* => */ icones.add("icones/aparencia1.png");

        
        //...
        try {
            for(int i = 0; i < nomes.size(); i++) {
                map.put( nomes.elementAt(i), new ImageIcon(icones.elementAt(i)));
                modulo.put( nomes.elementAt(i), i );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
                
        JList list = new JList(nomes);
        list.setCellRenderer(new MenuLateralRenderer());
        return list;
    }

    private class MenuLateralRenderer extends DefaultListCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            label.setIcon(map.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }
    
    public boolean se_chave(String chave, int indice) {
        if( modulo.containsKey(chave) && modulo.get(chave) == indice ) {
            return true;
        }else{
            return false;
        }
    }

}

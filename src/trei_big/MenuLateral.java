/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
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

    public JList lista() {
        
        String[] nomes_do_menu = {
            "Cadastrar",
            "Pesquisar/Editar",
            "Lixeira",
            "Backup",
            "Temas"
        };
        
        try {
            map.put(nomes_do_menu[0], new ImageIcon("icones/cadastrar.png"));
            map.put(nomes_do_menu[1], new ImageIcon("icones/pesquisar_e_editar.png"));
            map.put(nomes_do_menu[2], new ImageIcon("icones/lixo2.png"));
            map.put(nomes_do_menu[3], new ImageIcon("icones/1460597575_document-save.png"));
            map.put(nomes_do_menu[4], new ImageIcon("icones/aparencia1.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
                
        JList list = new JList(nomes_do_menu);
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

}

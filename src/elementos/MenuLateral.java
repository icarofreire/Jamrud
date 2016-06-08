/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

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
 * ELEMENTOS
 * @author icaro
 */
public class MenuLateral {
    
    /* \/ Titulo para os componentes; \/ */
    public static String input_text = "Campo de texto";
    public static String input_text_password = "Campo de senha";
    public static String label = "Nome simples";
    public static String opcoes = "Selecionar uma opção";
    public static String checkbox = "Selecionar opções";
    
    private Map<String, ImageIcon> map = new HashMap<>();
    private Map<String, Integer> modulo = new HashMap<>();
    private Vector<String> nomes = new Vector<String>();
    private Vector<String> icones = new Vector<String>();
    
    public JList lista() {
        
        /* \/ A ordem em que forem adicionados será a ordem em que aparecerá no menu. \/ */
        nomes.add(label); /* => */ icones.add("icones/elementos/Label/Label_32x32.png");
        nomes.add(input_text); /* => */ icones.add("icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png");
        nomes.add(input_text_password); /* => */ icones.add("icones/elementos/Form Input (Password)/Form Input (Password)_32x32.png");
        nomes.add(opcoes); /* => */ icones.add("icones/elementos/Form Input (Radio)/Form Input (Radio)_32x32.png");
        nomes.add("Selecionar opção"); /* => */ icones.add("icones/elementos/Form Input (Select Single)/Form Input (Select Single)_32x32.png");
        nomes.add(checkbox); /* => */ icones.add("icones/elementos/Form Input (Checkbox)/Form Input (Checkbox)_32x32.png");
        nomes.add("Área para texto"); /* => */ icones.add("icones/elementos/Form Input (Textarea)/Form Input (Textarea)_32x32.png");
        nomes.add("Um titulo"); /* => */ icones.add("icones/elementos/H1/H1_32x32.png");
        nomes.add("Texto"); /* => */ icones.add("icones/elementos/Quote/Quote_32x32.png");
        nomes.add("Link"); /* => */ icones.add("icones/elementos/Link (Make)/Link (Make)_32x32.png");
        nomes.add("Botão para OK"); /* => */ icones.add("icones/elementos/Form Input (Button OK)/Form Input (Button OK)_32x32.png");
        nomes.add("Botão para Cancelar"); /* => */ icones.add("icones/elementos/Form Input (Button Cancel)/Form Input (Button Cancel)_32x32.png");
        //        nomes.add("Componente5"); /* => */ icones.add("icones/elementos/Form Input (Select Multiple)/Form Input (Select Multiple)_32x32.png");
        //        nomes.add("Componente12"); /* => */ icones.add("icones/elementos/List (Ordered Decimal)/List (Ordered Decimal)_32x32.png");
        //        nomes.add("Componente13"); /* => */ icones.add("icones/elementos/List (Unordered)/List (Unordered)_32x32.png");

        
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

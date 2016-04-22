/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author icaro
 */
public class colorir_linha_tabela extends DefaultTableCellRenderer {
    
        Color backgroundColor = getBackground();
        estado_editar estado;
        
        public colorir_linha_tabela(estado_editar estado) {
            this.estado = estado;
        }
        

        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, final int row, final int column) {
            
            final Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            
                boolean linha_modificada_ = estado.se_linha_esta_na_lista(row);
                if ( linha_modificada_ ) 
                {
                    c.setBackground(Color.GREEN);

                } else {
                    if(!isSelected){
                        c.setBackground(backgroundColor);
                    }
                }
          
            return c;
        }
}

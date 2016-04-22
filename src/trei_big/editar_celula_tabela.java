/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author icaro
 */
public class editar_celula_tabela  extends DefaultCellEditor {
        
        editar_celula_tabela()
        {
            super( new JTextField() );
        }

        public boolean stopCellEditing()
        {
            try
            {
                String editingValue = (String)getCellEditorValue();

                if(editingValue.length() == 0)
                {
                    JTextField textField = (JTextField)getComponent();
                    textField.setBorder(new LineBorder(Color.red));
                    textField.selectAll();
                    textField.requestFocusInWindow();

                    JOptionPane.showMessageDialog(
                        null,
                        "Insira alguma informação.",
                        "Erro!",JOptionPane.ERROR_MESSAGE);
                    
                    return false;
                }
            }
            catch(ClassCastException exception)
            {
                return false;
            }

            return super.stopCellEditing();
        }

        public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
        {
            Component c = super.getTableCellEditorComponent(
                table, value, isSelected, row, column);
            ((JComponent)c).setBorder(new LineBorder(Color.black));
            

            return c;
        }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela_listagem;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author icaro
 */
public class exibir_listagem {
    
    public JPanel p1;
    
    exibir_listagem(final String titulo_listagem, final String[] nomes_colunas, final ArrayList<Object[]> dados_da_tabela)
    {
        //... Set Look and Feel.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                listagem window = new listagem(titulo_listagem, nomes_colunas, dados_da_tabela);
                window.setVisible(true);
            }
        });
    }
    
    public exibir_listagem(){};
    
    public JPanel obj(final String titulo_listagem, final String[] nomes_colunas, final ArrayList<Object[]> dados_da_tabela){
        listagem window = new listagem(titulo_listagem, nomes_colunas, dados_da_tabela);
        p1 = window.replaceDialog;
        return p1;
    }
}

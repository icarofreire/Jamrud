/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import ferramenta_gui.GBHelper;
import java.awt.GridBagLayout;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author icaro
 */
public class painel_historico {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    
    public JPanel painel_p_historico()
    {
        Vector<String> nomes = new Vector<String>();
        
        for(int i=0; i<500; i++){
            nomes.add("Hora: 00:00 | Data: 99/99/9999 | titulo da ação no software. " + (i+1) );
        }        
        
        JList list = new JList(nomes);
        JScrollPane scroll = new JScrollPane(list);
        painel.add(scroll, pos.expandir());
        return painel;
    }
    
}

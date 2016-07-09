/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import elementos.popup;
import ferramenta_gui.GBHelper;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JList list;
    
    public JPanel painel_p_historico()
    {
        Vector<String> nomes = new Vector<String>();
        
        for(int i=0; i<500; i++){
            nomes.add("Hora: 00:00 | Data: 99/99/9999 | titulo da ação no software. " + (i+1) );
        }        
        
        list = new JList(nomes);
        JScrollPane scroll = new JScrollPane(list);
        painel.add(scroll, pos.expandir());
        
        list.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) // <= evento de 2 clicks no mouse;
                {
                    int index = list.locationToIndex(evt.getPoint());
                    Object conteudo_obj = list.getSelectedValue();
                    if( conteudo_obj != null ){
                        String conteudo = conteudo_obj.toString();
                        new popup().exibir_texto("Registro de número: " + (index+1), conteudo);
                    }

                }
            }
        });
        
        list.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if( ke.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    Object conteudo_obj = list.getSelectedValue();
                    if( conteudo_obj != null ){
                        String conteudo = conteudo_obj.toString();
                        new popup().exibir_texto("Registro de número: " + (list.getSelectedIndex()+1), conteudo);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        
        return painel;
    }
    
}

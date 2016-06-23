/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import elementos.prefixos;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trei_big.operacoes;

/**
 *
 * @author icaro
 */
public class criar_layout_grid {
    
//    private JPanel painel_externo = new JPanel();
//    private JPanel painel = new JPanel();
    
    public static JPanel painel_grid = new JPanel(new GridBagLayout());
    public static GBHelper pos = new GBHelper();
    
    private JPanel painel;
    private GBHelper pos_painel;

    public criar_layout_grid() {
        painel = new JPanel(new GridBagLayout());
        pos_painel = new GBHelper();
    }
    
    public void add(JComponent comp1, JComponent comp2)
    {
        painel.add(comp1, pos_painel.nextRow().expandW());
        painel.add(comp2, pos_painel.nextRow().expandW());
        painel.add(new Gap(5), pos_painel.nextRow());
    }

    public JPanel getPainel() {
        return painel;
    }

    
}

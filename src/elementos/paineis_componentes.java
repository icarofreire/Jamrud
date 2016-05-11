/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.*;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author icaro
 */
public class paineis_componentes {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar";
    
    public JPanel input_text(){
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        
        JLabel lcmp1 = new JLabel("Nome:");
        JTextField cmp1 = new JTextField(20);
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        return painel;
    }
    
    public JPanel input_text_password(){
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        
        JLabel lcmp1 = new JLabel("Nome:");
        JTextField cmp1 = new JTextField(20);
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        return painel;
    }
    
}

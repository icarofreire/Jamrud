/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.*;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import trei_big.operacoes_painel;

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
        painel.setName("painel_input_text");
        
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
        painel.setName("painel_input_text_password");
        
        JLabel lcmp1 = new JLabel("Nome:");
        JTextField cmp1 = new JTextField(20);
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        return painel;
    }
    
    public JPanel label(){
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        painel.setName("painel_label");
        
        JLabel lcmp1 = new JLabel("Nome:");
        JTextField cmp1 = new JTextField(20);
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        return painel;
    }
    
    public JPanel grupo_opcoes_radio(JPanel painel_baixo){

        JPanel painel = new criar_grupos_opcoes().grupo_botoes(painel_baixo);
        return painel;
    }
    
    public JPanel grupo_opcoes_checkbox(JPanel painel_baixo){
        
        JPanel painel = new criar_opcoes_checkbox().grupo_botoes(painel_baixo);
        return painel;
    }
    
    
}

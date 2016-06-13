/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class paineis_componentes {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    
    public JPanel input_text(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new criar_campo_input().input_text(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel input_data(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new criar_campo_data().input_data(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel input_hora(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new criar_campo_hora().input_hora(painel_baixo, pos_painel_baixo);
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
    
    public JPanel grupo_opcoes_radio(JPanel painel_baixo, GBHelper pos_painel_baixo){

        JPanel painel = new criar_grupos_opcoes(1).grupo_botoes(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel grupo_opcoes_checkbox(JPanel painel_baixo, GBHelper pos_painel_baixo){
        JPanel painel = new criar_grupos_opcoes(2).grupo_botoes(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel lista_select(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        JPanel painel = new criar_grupos_opcoes(3).grupo_botoes(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel texto(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new criar_texto().texto(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel area_para_texto(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new criar_area_para_texto().area_para_texto(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel um_titulo(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new criar_titulo().um_titulo(painel_baixo, pos_painel_baixo);
        return painel;
    }    
    
}

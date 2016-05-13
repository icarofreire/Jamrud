/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.*;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
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
    
    public JPanel label(){
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
    
    public JPanel grupo_botoes(){
        final JPanel painel = new JPanel(new GridBagLayout());
        final GBHelper pos = new GBHelper();
        
        JTextField campo_titulo = new JTextField(20);
        JLabel lcmp1 = new JLabel("Nome:");
        final JTextField cmp1 = new JTextField(10);
        JButton btn_mais = new JButton("Mais um");
        cmp1.setText("Nome");
        
        cmp1.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                cmp1.setText("");
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
                
        
        final JRadioButton birdButton = new JRadioButton("opcap 1");
//        JRadioButton catButton = new JRadioButton("opcap 2");

        
           //Group the radio buttons.
        final ButtonGroup group = new ButtonGroup();
        group.add(birdButton);
//        group.add(catButton);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(campo_titulo, pos.nextCol().expandW());
        painel.add(btn_mais, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        painel.add(birdButton, pos.nextRow().expandW());
        painel.add(cmp1, pos.nextCol().expandW());
//        painel.add(catButton, pos.nextRow().expandW());
        
        btn_mais.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                final JRadioButton catButton = new JRadioButton("Nome");
                group.add(catButton);
                painel.add(catButton, pos.nextRow().expandW());
                final JTextField campos = new JTextField(10);
                campos.setText("Nome");
                painel.add(campos, pos.nextCol().expandW());
                operacoes_painel.atualizar_painel(painel);
                
                campos.addFocusListener(new FocusListener(){
                    @Override
                    public void focusGained(FocusEvent fe) {
                        campos.setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent fe) {
                    }
                });
                
                
                campos.addKeyListener(new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent ke) {
                            catButton.setText(catButton.getText() + ke.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent ke) {
                    }

                    @Override
                    public void keyReleased(KeyEvent ke) {
                        if( ke.getKeyCode() == KeyEvent.VK_BACK_SPACE )
                        {
                            if( catButton.getText().length() > 1 )
                            {
                                String nova = catButton.getText().substring(0, catButton.getText().length()-2);
                                catButton.setText(nova.trim());
                            }
                        }
                    }
                });

                
            }
        });
        
        return painel;
    }
    
}

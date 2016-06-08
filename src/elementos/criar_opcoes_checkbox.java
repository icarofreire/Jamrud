/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
//import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
import javax.swing.JTextField;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_opcoes_checkbox {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar";
    private int FLG_TIPO_OPCAO;
    
    
    private JPanel criar_radios_por_nome(Vector<String> nomes_radios) {
        
//        ButtonGroup group = new ButtonGroup();
        JPanel p_group = new JPanel(new GridBagLayout());
        GBHelper pos_p_group = new GBHelper();
        p_group.setName("painel_checkbox");
        
        for(int i=0; i<nomes_radios.size(); i++){
                JCheckBox catButton = new JCheckBox(nomes_radios.get(i));
//                group.add(catButton);
                p_group.add(catButton, pos_p_group.nextRow().expandW());
        }
        nomes_radios.clear();
        return p_group;
    }
    
    public JPanel grupo_botoes(final JPanel painel_baixo) {
        
        final JPanel painel = new JPanel(new GridBagLayout());
        final GBHelper pos = new GBHelper();
        
        JTextField campo_titulo = new JTextField(20);
        JLabel lcmp1 = new JLabel("Titulo:");
        final JTextField cmp1 = new JTextField(10);
        JButton btn_mais = new JButton("Mais uma opcao");
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        cmp1.setText("Nome");
        
        final JCheckBox birdButton = new JCheckBox("Nome");
        birdButton.setName("radio_0");
        
        cmp1.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                cmp1.setText("");
                birdButton.setText("");
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
                
        evento_escrever_nome_p_opcao(birdButton, cmp1);
        
           //Group the radio buttons.
        final JCheckBox group = new JCheckBox();
        
        group.add(birdButton);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(campo_titulo, pos.nextCol().expandW());
        painel.add(btn_aplicar, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        painel.add(birdButton, pos.nextRow().expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(btn_mais, pos.nextCol().expandW());
        
        btn_mais.addActionListener(new ActionListener(){

            int id_radio = 1;
            @Override
            public void actionPerformed(ActionEvent ae) {
                final JCheckBox catButton = new JCheckBox("Nome");
                catButton.setName("radio_"+id_radio);
                id_radio++;
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
                        catButton.setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent fe) {
                    }
                });
                
                evento_escrever_nome_p_opcao(catButton, campos);
            }
        });
        
        
        final GBHelper pos_painel_baixo = new GBHelper();
        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                Component[] components = painel.getComponents();
                Vector<String> nomes_radios = new Vector<String>();
                for (int i=0; i < components.length; i++) {
                    String name_componente = components[i].getName();
                   if( (name_componente != null) && (name_componente.indexOf("radio_") != -1) ){
                       JCheckBox radio = (JCheckBox) components[i];
                       nomes_radios.add( radio.getText() );
                   }
                }//fim for;
                
                painel_baixo.add( criar_radios_por_nome(nomes_radios), pos_painel_baixo.nextRow().expandW() );
                operacoes_painel.atualizar_painel(painel_baixo);
                
            }
        });
        
        return painel;
    }
    
    
    private void evento_escrever_nome_p_opcao(final JCheckBox birdButton, final JTextField cmp1) 
    {
        cmp1.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
                    birdButton.setText(birdButton.getText() + ke.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if( ke.getKeyCode() == KeyEvent.VK_BACK_SPACE )
                {
                    if( birdButton.getText().length() > 1 )
                    {
                        String nova = birdButton.getText().substring(0, birdButton.getText().length()-2);
                        birdButton.setText(nova.trim());
                    }
                }
            }
        });
    }
    
}

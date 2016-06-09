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
import javax.swing.ImageIcon;
//import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
//import javax.swing.JRadioButton;
import javax.swing.JTextField;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_opcoes_checkbox {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    private int FLG_TIPO_OPCAO;
    
    
    private JPanel criar_checkbox_por_nome(Vector<String> nomes_radios) {
        
        JPanel p_group = new JPanel(new GridBagLayout());
        GBHelper pos_p_group = new GBHelper();
        p_group.setName("painel_checkbox");
        
        int numero_colunas = 6;
        int con = 0;
        int primeiro = 0;
        for(int i=0; i<nomes_radios.size(); i++){
                JCheckBox catButton = new JCheckBox(nomes_radios.get(i));
                if( con < (numero_colunas-1) ){
                    if(primeiro==0){
                        p_group.add(catButton, pos_p_group.expandW()); primeiro = 1;
                    }else{
                        p_group.add(catButton, pos_p_group.nextCol().expandW());
                        con++;
                    }
                }else{
                    p_group.add(catButton, pos_p_group.nextRow().expandW());
                    con=0;
                }
        }
        nomes_radios.clear();
        return p_group;
    }
    
    public JPanel grupo_botoes(final JPanel painel_baixo, final GBHelper pos_painel_baixo) {
        
        final JPanel painel = new JPanel(new GridBagLayout());
        final GBHelper pos = new GBHelper();
        final String token = "-";
        
        final JTextField cmp1 = new JTextField(10);
        JButton btn_mais = new JButton("Mais uma opcao", new ImageIcon("icones/add-24.png"));
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
        
        painel.add(btn_aplicar, pos.expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        painel.add(birdButton, pos.nextRow().expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(btn_mais, pos.nextCol().expandW());
        
        btn_mais.addActionListener(new ActionListener(){

            int id_radio = 1;
            @Override
            public void actionPerformed(ActionEvent ae) {
                final JCheckBox catButton = new JCheckBox("Nome");
                final JButton btn_excluir = new JButton("", new ImageIcon("icones/erro-24.png"));
                final JTextField campos = new JTextField(10);
                campos.setName("campo"+token+id_radio);
                catButton.setName("radio_"+token+id_radio);
                btn_excluir.setName("btn_excluir"+token+id_radio);
                id_radio++;
                group.add(catButton);
                
                painel.add(catButton, pos.nextRow().expandW());
                
                painel.add(campos, pos.nextCol().expandW());
                painel.add(btn_excluir, pos.nextCol().expandW());
                operacoes_painel.atualizar_painel(painel);
                
                campos.addFocusListener(new FocusListener(){
                    @Override
                    public void focusGained(FocusEvent fe) {
//                        campos.setText("");
                        catButton.setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent fe) {
                    }
                });
                
                btn_excluir.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        String name = btn_excluir.getName();
                        String numero_name = name.substring(name.indexOf(token)+1, name.length()).trim();
                                  
                        painel.remove( operacoes_painel.pegar_componente_em_painel(painel, "campo"+token+numero_name) );
                        painel.remove( operacoes_painel.pegar_componente_em_painel(painel, "radio_"+token+numero_name) );
                        painel.remove( operacoes_painel.pegar_componente_em_painel(painel, name) );

                        operacoes_painel.atualizar_painel(painel);
                    }
                });
                
                evento_escrever_nome_p_opcao(catButton, campos);
            }
        });
        
        
        
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
                
                painel_baixo.add( criar_checkbox_por_nome(nomes_radios), pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
                operacoes_painel.atualizar_painel(painel_baixo);
                nomes_radios.clear();
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

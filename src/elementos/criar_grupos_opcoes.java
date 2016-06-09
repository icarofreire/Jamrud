/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JTextField;
import trei_big.operacoes;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_grupos_opcoes {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    private String token = "-";
    private String prefix_id = "radio_";
    private String prefixo_campo_add = "comp_campo_add";
    private String prefixo_btn_excluir_add = "comp_btn_excluir_add_";
    private String prefixo_painel_radios = "painel_radios_";
    private String prefixo_btn_excluir_painel = "btn_excluir_painel_";
    
    
    private JPanel criar_radios_por_nome(Vector<String> nomes_radios) {
        
        ButtonGroup group = new ButtonGroup();
        JPanel p_group = new JPanel(new GridBagLayout());
        GBHelper pos_p_group = new GBHelper();
        p_group.setName("painel_radios");
        
        int numero_colunas = 6;
        int con = 0;
        int primeiro = 0;
        for(int i=0; i<nomes_radios.size(); i++){
                JRadioButton catButton = new JRadioButton(nomes_radios.get(i));
                group.add(catButton);
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
        
        
        final JTextField cmp1 = new JTextField(10);
        JButton btn_mais = new JButton("Mais uma opcao", new ImageIcon("icones/add-24.png"));
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        cmp1.setText("Nome");
        
        final JRadioButton birdButton = new JRadioButton("Nome");
        birdButton.setName( operacoes.gerar_name_para_componente(prefix_id) );
        
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
        final ButtonGroup group = new ButtonGroup();
        
        group.add(birdButton);
        
        painel.add(btn_aplicar, pos.expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        painel.add(birdButton, pos.nextRow().expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(btn_mais, pos.nextCol().expandW());
        
        btn_mais.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                final JRadioButton catButton = new JRadioButton("Nome");
                final JButton btn_excluir = new JButton("", new ImageIcon("icones/erro-24.png"));
                final JTextField campos = new JTextField(10);
                
                String chave = operacoes.gerar_chave();
                campos.setName( prefixo_campo_add + chave );
                catButton.setName( prefix_id + chave );
                btn_excluir.setName( prefixo_btn_excluir_add + chave );
                
                group.add(catButton);
                
                painel.add(catButton, pos.nextRow().expandW());
                painel.add(campos, pos.nextCol().expandW());
                painel.add(btn_excluir, pos.nextCol().expandW());
                operacoes_painel.atualizar_painel(painel);
                
                campos.addFocusListener(new FocusListener(){
                    @Override
                    public void focusGained(FocusEvent fe) {
                        catButton.setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent fe) {
                    }
                });
                
                add_botao_excluir(btn_excluir, "campo");
                
                evento_escrever_nome_p_opcao(catButton, campos);
            }
        });
        
        evento_botao_aplicar(btn_aplicar, painel_baixo, pos_painel_baixo);
        
        return painel;
    }
    
    
    private void evento_escrever_nome_p_opcao(final JRadioButton birdButton, final JTextField cmp1) 
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
    
    private void add_botao_excluir(final JButton btn_excluir, final String name_componente_excluir)
    {
        btn_excluir.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
                        
                String name = btn_excluir.getName();
                String chave = operacoes.pegar_chave_em_string(name);
                                  
                painel.remove( operacoes_painel.pegar_componente_em_painel(painel, operacoes.pegar_name(prefix_id, chave)) );
                painel.remove( operacoes_painel.pegar_componente_em_painel(painel, operacoes.pegar_name(prefixo_campo_add, chave)) );
                painel.remove( operacoes_painel.pegar_componente_em_painel(painel, operacoes.pegar_name(prefixo_btn_excluir_add, chave)) );

                operacoes_painel.atualizar_painel(painel);
                pos.gridy--;
            }
        });
    }
    
    private void evento_botao_aplicar(final JButton btn_aplicar, final JPanel painel_baixo, final GBHelper pos_painel_baixo)
    {
            btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                Component[] components = painel.getComponents();
                Vector<String> nomes_radios = new Vector<String>();
                for (int i=0; i < components.length; i++) {
                    String name_componente = components[i].getName();
                   if( (name_componente != null) && (name_componente.indexOf(prefix_id) != -1) ){

                            JRadioButton radio = (JRadioButton) components[i];
                            nomes_radios.add( radio.getText() );                       
                   }
                }//fim for;
                
                JPanel painel_a_inserir = criar_radios_por_nome(nomes_radios);
                painel_a_inserir.setBorder(BorderFactory.createLineBorder(Color.RED));
                JButton btn_excluir_painel = new JButton("", new ImageIcon("icones/erro-24.png"));
                
                operacoes_painel.add_botao_excluir_painel(painel_baixo, pos_painel_baixo, painel_a_inserir, btn_excluir_painel, prefixo_painel_radios, prefixo_btn_excluir_painel);
                
                painel_baixo.add( painel_a_inserir, pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add( btn_excluir_painel, pos_painel_baixo.nextCol().expandW() );
                
                painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
                operacoes_painel.atualizar_painel(painel_baixo);
                nomes_radios.clear();
            }
        });
    }
    
}

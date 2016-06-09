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
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_opcoes_select {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private Vector<String> nomes_radios = new Vector<String>();
    private String token = "-";
    private String prefix_id = "campo";
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    
    
    public JPanel lista_select(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        final JTextField cmp1 = new JTextField(10);
        cmp1.setName(prefix_id+token+"0");
        JButton btn_mais = new JButton("Mais uma opcao", new ImageIcon("icones/add-24.png"));
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        final JLabel titulo = new JLabel("Nome:");
        
        cmp1.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                cmp1.setText("");
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });

        painel.add(btn_aplicar, pos.expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        painel.add(titulo, pos.nextRow().expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(btn_mais, pos.nextCol().expandW());
        
        btn_mais.addActionListener(new ActionListener(){
            int id_campo = 1;
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                final JTextField campos = new JTextField(10);
                final JLabel titulo2 = new JLabel("Nome:");
                final JButton btn_excluir = new JButton("", new ImageIcon("icones/erro-24.png"));
                titulo2.setName("titulo"+token+id_campo);
                campos.setName(prefix_id+token+id_campo);
                btn_excluir.setName("btn_excluir"+token+id_campo);
                id_campo++;
                
                painel.add(titulo2, pos.nextRow().expandW());
                painel.add(campos, pos.nextCol().expandW());
                painel.add(btn_excluir, pos.nextCol().expandW());
                operacoes_painel.atualizar_painel(painel);
                
                campos.addFocusListener(new FocusListener(){
                    @Override
                    public void focusGained(FocusEvent fe) {
//                        campos.setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent fe) {
                    }
                });
                
                add_botao_excluir(btn_excluir, "titulo");
                
            }
        });

        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                Component[] components = painel.getComponents();
                for (int i=0; i < components.length; i++) {
                    String name_componente = components[i].getName();
                    if( (name_componente != null) && (name_componente.indexOf(prefix_id+token) != -1) ){
                        JTextField campo = (JTextField) components[i];
                        nomes_radios.add( campo.getText() );
                    }
                }//fim for;
                
                JComboBox comboBox1 = new JComboBox(nomes_radios.toArray(new String[]{}));
                painel_baixo.add( comboBox1, pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
                operacoes_painel.atualizar_painel(painel_baixo);
                nomes_radios.clear();
            }
        });
        
        
        return painel;
    }
    
    
    private void add_botao_excluir(final JButton btn_excluir, final String name_componente_excluir)
    {
        btn_excluir.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
                        
                String name = btn_excluir.getName();
                String numero_name = name.substring(name.indexOf(token)+1, name.length()).trim();
                                  
                painel.remove( operacoes_painel.pegar_componente_em_painel(painel, name_componente_excluir+token+numero_name) );
                painel.remove( operacoes_painel.pegar_componente_em_painel(painel, prefix_id+token+numero_name) );
                painel.remove( operacoes_painel.pegar_componente_em_painel(painel, name) );

                operacoes_painel.atualizar_painel(painel);
                pos.gridy--;
            }
        });
    }
    
}

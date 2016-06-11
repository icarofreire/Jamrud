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
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import trei_big.aviso;
import trei_big.operacoes;
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
    private String prefixo_campo_add = "comp_campo_add";
    private String prefixo_btn_excluir_add = "comp_btn_excluir_add_";
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    
    
    private JPanel criar_select_por_nome(Vector<String> nomes_radios) {
        
        JPanel p_group = new JPanel(new GridBagLayout());
        GBHelper pos_p_group = new GBHelper();
        p_group.setName("painel_select");
        
        JComboBox comboBox1 = new JComboBox(nomes_radios.toArray(new String[]{}));
        p_group.add(comboBox1, pos_p_group.expandW());
        
        nomes_radios.clear();
        return p_group;
    }
    
    
    public JPanel lista_select(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        final JTextField cmp1 = new JTextField(10);
        cmp1.setName( operacoes.gerar_name_para_componente(prefixo_campo_add) );
        JButton btn_mais = new JButton("Mais uma opção", new ImageIcon("icones/add-24.png"));
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        final JLabel titulo = new JLabel("Titulo:");

        painel.add(btn_mais, pos.expandW());
        painel.add(btn_aplicar, pos.expandW().nextCol());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        painel.add(titulo, pos.nextRow().expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        
        btn_mais.addActionListener(new ActionListener(){
            int id_campo = 1;
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                final JTextField campos = new JTextField(10);
                final JLabel titulo2 = new JLabel("Titulo:");
                final JButton btn_excluir = new JButton("Excluir", new ImageIcon("icones/erro-24.png"));
                
                String chave = operacoes.gerar_chave();
                titulo2.setName( prefix_id + chave );
                campos.setName( prefixo_campo_add + chave );
                btn_excluir.setName( prefixo_btn_excluir_add + chave );
                
                
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
        
        evento_botao_aplicar(btn_aplicar, painel_baixo, pos_painel_baixo);
                
        return painel;
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
                   if( (name_componente != null) && (name_componente.indexOf(prefixo_campo_add) != -1) ){

                            JTextField radio = (JTextField) components[i];
                            String nomes = radio.getText().trim();
                            nomes_radios.add( nomes );
                   }
                }//fim for;
                
                if( nomes_radios.size() > 1 )
                {
                    if( nomes_radios.contains("") ){
                        aviso.mensagem_atencao("Insira o titulo para as opções que irá adicionar em sua lista.", "Titulo vazio");
                    }else{
                        JPanel painel_a_inserir = criar_select_por_nome(nomes_radios);
                        operacoes_painel.add_componente_painel_baixo_e_add_botao_exluir(painel_a_inserir, painel_baixo, pos_painel_baixo, prefixos.prefixo_painel_select);
                        nomes_radios.clear();
                    }
                }else{
                    aviso.mensagem_atencao("Insira no mínimo 2 elementos em seu formulário.", "Número insuficiente");
                }
            }
        });
    }
    
}

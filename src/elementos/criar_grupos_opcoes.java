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
import java.awt.Dimension;
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import trei_big.aviso;
import trei_big.operacoes;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_grupos_opcoes {
    
    private JPanel painel_componentes_add = new JPanel(new GridBagLayout());
    private GBHelper pos_painel_componentes_add = new GBHelper();
    private JScrollPane scroll_painel_componentes_add = operacoes_painel.painel_com_scroll_sem_borda(painel_componentes_add);
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    private String prefix_id = "radio_";
    private String prefixo_campo_add = "comp_campo_add";
    private String prefixo_btn_excluir_add = "comp_btn_excluir_add_";
    
    private JTextField campo_para_titulo_componente = new JTextField(20);
    
    private int tipo_componente;

    /*
        tipo_componente = 1 => JRadioButton;
        tipo_componente = 2 => JCheckBox;
        tipo_componente = 3 => JTextField;
    */
    public criar_grupos_opcoes(int tipo_componente) {
        this.tipo_componente = tipo_componente;
    }
    
    
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
    
    private JPanel criar_select_por_nome(Vector<String> nomes_radios) {
        
        JPanel p_group = new JPanel(new GridBagLayout());
        GBHelper pos_p_group = new GBHelper();
        p_group.setName("painel_select");
        
        JComboBox comboBox1 = new JComboBox(nomes_radios.toArray(new String[]{}));
        p_group.add(comboBox1, pos_p_group.expandW());
        
        nomes_radios.clear();
        return p_group;
    }
    
    public JPanel grupo_botoes(final JPanel painel_baixo, final GBHelper pos_painel_baixo) {
        
        JLabel label_titulo = new JLabel("Titulo:");
        
        JButton btn_mais = new JButton("Adicionar opções", new ImageIcon("icones/add-24.png"));
        JButton btn_aplicar = new JButton(nome_botao_aplicar, new ImageIcon("icones/add_formulario-24.png"));
        
        final ButtonGroup group = new ButtonGroup();
        if( tipo_componente == 1 )
        {
            JRadioButton birdButton = new JRadioButton();
            birdButton.setName( operacoes.gerar_name_para_componente(prefix_id) );
            group.add(birdButton);
        }
        else if( tipo_componente == 2 )
        {
            JCheckBox birdButton = new JCheckBox();
            birdButton.setName( operacoes.gerar_name_para_componente(prefix_id) );
        }
        
        JPanel painel_titulo = new JPanel();
        painel_titulo.add(label_titulo);
        painel_titulo.add(campo_para_titulo_componente);
        
        painel.add(painel_titulo, pos.expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        JPanel painel_botao = new JPanel();
        painel_botao.add(btn_mais);
        painel_botao.add(btn_aplicar);
        
        painel.add(painel_botao, pos.expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        // \/ constroi o painel onde será visualizado os componentes que estão sendo adicionados no momento;
        painel_componentes_add.setBorder(BorderFactory.createTitledBorder("Componentes"));
        painel.add(scroll_painel_componentes_add, pos.nextRow().width(2).expandW());
        scroll_painel_componentes_add.setPreferredSize(new Dimension(200, 200));
        scroll_painel_componentes_add.setVisible(false);
        // ---
        
        btn_mais.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                JComponent comp = null;
                
                if( tipo_componente == 1 )
                {
                    comp = new JRadioButton();
                    group.add((JRadioButton)comp);
                }
                else if( tipo_componente == 2 )
                {
                    comp = new JCheckBox();
                }
                else if( tipo_componente == 3 )
                {
                    comp = new JTextField(10);
                }

                final JButton btn_excluir = new JButton("Excluir", new ImageIcon("icones/erro-24.png"));
                final JTextField campos = new JTextField(10);
                
                String chave = operacoes.gerar_chave();
                campos.setName( prefixo_campo_add + chave );
                comp.setName( prefix_id + chave );
                btn_excluir.setName( prefixo_btn_excluir_add + chave );
                
                // \/ visualizar o painel onde os componentes serão adicionados em fileira, após clicar o botão;
                scroll_painel_componentes_add.setVisible(true);
                operacoes_painel.atualizar_painel(painel);
                // ---
                
                if( tipo_componente == 1 || tipo_componente == 2 )
                {
                    painel_componentes_add.add(comp, pos_painel_componentes_add.nextRow().expandW());
                    painel_componentes_add.add(campos, pos_painel_componentes_add.nextCol().expandW());
                }
                else if( tipo_componente == 3 )
                {
                    JLabel titulo2 = new JLabel("Titulo:");
                    titulo2.setName( prefixo_campo_add + chave );
                    comp.setName( prefix_id + chave );
                    painel_componentes_add.add(titulo2, pos_painel_componentes_add.nextRow().expandW());
                    painel_componentes_add.add(comp, pos_painel_componentes_add.nextCol().expandW());
                }
                
                painel_componentes_add.add(btn_excluir, pos_painel_componentes_add.nextCol().expandW());
                operacoes_painel.atualizar_painel(painel_componentes_add);
                
                if( tipo_componente == 1 || tipo_componente == 2 ){
                    add_botao_excluir(btn_excluir, "campo");
                }
                else if( tipo_componente == 3 ){
                    add_botao_excluir(btn_excluir, "titulo");
                }
                
                if( tipo_componente == 1 ){
                    evento_escrever_nome_p_opcao((JRadioButton)comp, campos);
                }
                else if( tipo_componente == 2 ){
                    evento_escrever_nome_p_opcao((JCheckBox)comp, campos);
                }
                
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
    
    private void add_botao_excluir(final JButton btn_excluir, final String name_componente_excluir)
    {
        btn_excluir.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
                        
                String name = btn_excluir.getName();
                String chave = operacoes.pegar_chave_em_string(name);
                                  
                painel_componentes_add.remove( operacoes_painel.pegar_componente_em_painel(painel_componentes_add, operacoes.pegar_name(prefix_id, chave)) );
                painel_componentes_add.remove( operacoes_painel.pegar_componente_em_painel(painel_componentes_add, operacoes.pegar_name(prefixo_campo_add, chave)) );
                painel_componentes_add.remove( operacoes_painel.pegar_componente_em_painel(painel_componentes_add, operacoes.pegar_name(prefixo_btn_excluir_add, chave)) );

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
                
                Component[] components = painel_componentes_add.getComponents();
                Vector<String> nomes_radios = new Vector<String>();
                for (int i=0; i < components.length; i++) {
                    String name_componente = components[i].getName();
                   if( (name_componente != null) && (name_componente.indexOf(prefix_id) != -1) ){

                       if( tipo_componente == 1 ){
                            JRadioButton radio = (JRadioButton) components[i];
                            String nomes = radio.getText().trim();
                            nomes_radios.add( nomes );
                            
                       }else if( tipo_componente == 2 ){
                            JCheckBox radio = (JCheckBox) components[i];
                            String nomes = radio.getText().trim();
                            nomes_radios.add( nomes );
                            
                       }else if( tipo_componente == 3 ){
                            JTextField radio = (JTextField) components[i];
                            String nomes = radio.getText().trim();
                            nomes_radios.add( nomes );
                       }
                       
                   }
                }//fim for;
                
                String nome_titulo_componente = campo_para_titulo_componente.getText().trim();
                if( !nome_titulo_componente.isEmpty() )
                {                
                    if( nomes_radios.size() > 1 )
                    {
                        if( nomes_radios.contains("") ){
                            aviso.mensagem_atencao("Insira o titulo para as opções que irá adicionar em seu formulário.", "Titulo vazio");
                        }else{
                            JPanel painel_a_inserir = null;
                            if( tipo_componente == 1 ){
                                painel_a_inserir = criar_radios_por_nome(nomes_radios);
                            }
                            else if( tipo_componente == 2 ){
                                painel_a_inserir = criar_checkbox_por_nome(nomes_radios);
                            }
                            else if( tipo_componente == 3 ){
                                painel_a_inserir = criar_select_por_nome(nomes_radios);
                            }
                            
                            operacoes_painel.add_componente_painel_baixo_e_add_botao_exluir(painel_a_inserir, painel_baixo, pos_painel_baixo, prefixos.prefixo_painel_opcoes, campo_para_titulo_componente.getText().trim());
                            nomes_radios.clear();
                            campo_para_titulo_componente.setText(null);
                        }
                    }else{
                        aviso.mensagem_atencao("Insira no mínimo 2 elementos em seu formulário.", "Número insuficiente");
                    }
                }else{
                    aviso.mensagem_atencao("Informe um titulo para seu componente.", "Titulo vazio");
                }
                
            }
        });
    }
    
}

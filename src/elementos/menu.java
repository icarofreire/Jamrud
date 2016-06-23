/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import banco.*;
import ferramenta_gui.*;
import paineis.*;
import trei_big.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import tabela_listagem.exibir_listagem;


/**
 *
 * @author icaro
 */
public class menu extends JFrame {
    
    private MenuLateral menu_lateral;
    private GBHelper pos = new GBHelper();
    private GBHelper pos_paineis_internos = new GBHelper();
    private static final int BORDER = 12;  // Window border in pixels.
    private static final int GAP = 5;   // Default gap btwn components.
    
    private JTextField nome_formulario = new JTextField(20);
    private JTextField pesquisa = new JTextField(20);
    private JList lista = new JList();
    private JButton botao_exibir_listagem = new JButton();
    private JButton botao_exibir_listagem2 = new JButton();
    private JButton botao_criar_formulario = new JButton("Gerar Formulário", new ImageIcon("icones/construir-24.png"));
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    
    private JMenu menu = new JMenu("Arquivo");
    private JMenuItem item_menu_sobre = new JMenuItem("Sobre");
    private JMenuItem item_menu_ajuda = new JMenuItem("Ajuda");
    private JMenuBar barra_de_menu = new JMenuBar();
    
    private JLabel x = new JLabel("Formulário de cadastrar");
    private JLabel x2 = new JLabel("Fazer backup do banco");
    private JLabel l_lixeira = new JLabel("Lixeira");
    private JPanel painel_esquerdo = new JPanel(new GridBagLayout());
    private JPanel painel_direito = new JPanel(new GridBagLayout());
    
    private JPanel painel_cima = new JPanel(new GridBagLayout());
    private JPanel painel_baixo = new JPanel(new GridBagLayout());
    
    private int largura = 1200;
    private int altura = 700;
    
    private paineis_componentes pc;
    
    
    public menu(String titulo) {

        //... Create content pane with one button and set window attributes.
        JPanel windowContent = new JPanel();
        windowContent.setLayout(new BorderLayout());
        windowContent.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        windowContent.add( gui() , BorderLayout.CENTER);

        barra_de_menu.add(menu);
        menu.add(item_menu_ajuda);
        menu.add(item_menu_sobre);
    
        item_menu_sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new sobre();
            }
        });
        
        item_menu_ajuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new ajuda();
            }
        });

        super.setJMenuBar(barra_de_menu);
        
        //... Set the window characteristics.
        super.setContentPane(windowContent);
        super.pack();                               // Layout components.
        super.setTitle(titulo);
//        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);          // Center window.
        super.setSize(new Dimension(largura, altura));
//        super.setExtendedState(JFrame.MAXIMIZED_BOTH);// <= maximixar a janela ao iniciar;
        super.setVisible(true);
    }
    
     private JPanel gui() {
      
        this.menu_lateral = new MenuLateral();
        lista = this.menu_lateral.lista();
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(300, 0));
        scroll.setMinimumSize(new Dimension(300, 0));
        
        JLabel imageLbl = new JLabel();
        imageLbl.setBounds(0, 0, 286, 40);
        imageLbl.setIcon(new ImageIcon("icones/pesquisar.png"));
        
        pesquisa.setBounds(50, 0, 286, 40);
        pesquisa.add(imageLbl);
        
        //... Create GridBagLayout content pane; set border.
//        JPanel content = new JPanel(new GridBagLayout());
        painel_esquerdo.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        
        //... First row
        painel_esquerdo.add(scroll, pos.nextCol());
        painel_esquerdo.add(new Gap(GAP), pos.nextCol());
        
        painel_cima.setBorder(BorderFactory.createTitledBorder("Componente"));
        painel_baixo.setBorder(BorderFactory.createTitledBorder("Formulário gerado"));
        
        painel_esquerdo.add(painel_direito, pos.nextCol().expandir());
        
        JPanel p_nome_form = operacoes_painel.add_componente_em_painel(new JLabel("Nome do formulário:"), nome_formulario, 2);
        painel_direito.add(p_nome_form, pos_paineis_internos.expandW());
        
        painel_direito.add( operacoes_painel.painel_com_scroll_sem_borda(painel_cima) , pos_paineis_internos.nextRow().expandir());
        painel_direito.add( operacoes_painel.painel_com_scroll_sem_borda(painel_baixo) , pos_paineis_internos.nextRow().expandir());
        pos.nextRow();
        JPanel p_botoes = operacoes_painel.add_componente_em_painel(botao_criar_formulario, botao_fechar, 2);
        painel_direito.add(p_botoes, pos_paineis_internos.nextRow().expandW());
        
        lista.addMouseListener(new MouseListener(){
             @Override
             public void mouseClicked(MouseEvent me) {
                 
                 JList theList = (JList) me.getSource();
                 int index = theList.locationToIndex(me.getPoint());
                 Object o = theList.getModel().getElementAt(index);
                 String titulo = o.toString();
                 painel_cima.setBorder(BorderFactory.createTitledBorder("Componente: " + titulo));
                 
//                 paineis_componentes 
                         pc = new paineis_componentes();
                 
                 if( menu_lateral.se_chave(MenuLateral.input_text, index) )
                 {
                         JPanel painel_cadastrar = pc.input_text(painel_baixo, pos);
                         painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, painel_cadastrar, "scroll_painel_cadastrar", pos);
                         operacoes_painel.atualizar_painel(painel_cima);
                         
                 }else if( menu_lateral.se_chave(MenuLateral.input_data, index) )
                 {
                         JPanel painel_cadastrar = pc.input_data(painel_baixo, pos);
                         painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, painel_cadastrar, "scroll_painel_cadastrar", pos);
                         operacoes_painel.atualizar_painel(painel_cima);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.input_hora, index) )
                 {
                         JPanel painel_cadastrar = pc.input_hora(painel_baixo, pos);
                         painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, painel_cadastrar, "scroll_painel_cadastrar", pos);
                         operacoes_painel.atualizar_painel(painel_cima);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.opcoes, index) )
                 {                        
                        JPanel pl = pc.grupo_opcoes_radio(painel_baixo, pos);
                        painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, pl, "scroll_pl", pos);
                        operacoes_painel.atualizar_painel(painel_cima);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.checkbox, index) )
                 {                        
                        JPanel pl = pc.grupo_opcoes_checkbox(painel_baixo, pos);
                        painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, pl, "scroll_pl", pos);
                        operacoes_painel.atualizar_painel(painel_cima);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.textarea, index) )
                 {
                        JPanel pl = pc.area_para_texto(painel_baixo, pos);
                        painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, pl, "scroll_pl", pos);
                        operacoes_painel.atualizar_painel(painel_cima);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.texto, index) )
                 {
                        JPanel pl = pc.texto(painel_baixo, pos);
                        painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, pl, "scroll_pl", pos);
                        operacoes_painel.atualizar_painel(painel_cima);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.titulo, index) )
                 {
                        JPanel pl = pc.um_titulo(painel_baixo, pos);
                        painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, pl, "scroll_pl", pos);
                        operacoes_painel.atualizar_painel(painel_cima);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.select, index) )
                 {
                        JPanel pl = pc.lista_select(painel_baixo, pos);
                        painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, pl, "scroll_pl", pos);
                        operacoes_painel.atualizar_painel(painel_cima);
                 }
                 
                 
             }// fim mouseClicked(MouseEvent me);

             @Override
             public void mousePressed(MouseEvent me) {
             }

             @Override
             public void mouseReleased(MouseEvent me) {
             }

             @Override
             public void mouseEntered(MouseEvent me) {
             }

             @Override
             public void mouseExited(MouseEvent me) {
             }
        });
        
        botao_criar_formulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String nome = nome_formulario.getText().trim();
                if( nome.indexOf(" ") != -1 ){
                    nome = nome.replaceAll(" ", "_");
                }
                if( !nome.isEmpty() )
                {
                    if( operacoes.se_titulo_correto(nome.replaceAll("_", " ")) )
                    {
                        if( numero_componentes_add_em_painel() > 0 )
                        {

                            //|--->
                            int titulos_aceitos = 0;
                            String[] titulos = obter_todos_os_titulos();
                            if( !operacoes.se_dados_duplicados(titulos) )
                            {
                                for (int i = 0; i < titulos.length; i++) {
                                    if( operacoes.se_titulo_correto(titulos[i]) )
                                    {
                                        titulos_aceitos++;
                                    }else{
                                        aviso.mensagem_titulo_incorreto(titulos[i]);
                                    }                                
                                }
                                if( (titulos_aceitos == titulos.length) )
                                {
                                    if( titulos_aceitos > 0 ){
                                        /* \/\/ COMENTADO APENAS PARA FINS DE TESTE;
                                        PARA NÃO CRIAR TABELAS GERADAS PELOS TESTES. \/\/ */
                                        
//                                        banco.executar_query( SQL.montar_sql_criar_tabela(titulos, nome) );
                                        
                                        /* /\/\ COMENTADO APENAS PARA FINS DE TESTE;
                                        PARA NÃO CRIAR TABELAS GERADAS PELOS TESTES. /\/\ */
                                    }
                                            JPanel painel_a_serializar = remover_bordas_vermelhas_e_botes_excluir();
                                            String painel_baixo_serializado = operacoes_painel.serializar_obj( painel_a_serializar );

                                            banco.inserir_hash_formulario_serializado(nome, painel_baixo_serializado);

                                            painel_baixo.removeAll();
                                            painel_baixo.setBorder(BorderFactory.createTitledBorder("Formulário gerado"));
                                            operacoes_painel.atualizar_painel(painel_baixo);
                                            aviso.mensagem_sucesso("Formulário construído com sucesso!");
                                            dispose();
                                            operacoes_painel.atualizar_painel(painel_esquerdo);
                                    
                                }
                            }else{
                                aviso.mensagem_atencao("Existe titulos iguais em seu formulário,\n"
                                        + "informe titulos diferentes para o sistema registrar os dados corretamente.",
                                        "Titulos duplicados");
                            }
                            //|--->

                        }else{
                            aviso.mensagem_atencao("Adicione componentes em seu formulário.", "Formulário vazio");
                        }
                    }else{
                        aviso.mensagem_titulo_incorreto(nome);
                    }
                }else{
                    aviso.mensagem_atencao("Insira o nome de seu formulário.", "Nome vazio");
                }
                
            }
        });
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });
        
        return painel_esquerdo;
    }

    
    public JPanel remover_bordas_vermelhas_e_botes_excluir()
    {
        JPanel copia_painel_baixo = painel_baixo;
        Component[] components = copia_painel_baixo.getComponents();
        for (int i=0; i < components.length; i++)
        {
            String name_componente = components[i].getName();
            for (int j=0; j < prefixos.prefixos_paineis.length; j++){
                // \/ remove as bordas vermelhas;
                if( (name_componente != null) && (name_componente.indexOf(prefixos.prefixos_paineis[j]) != -1)  ){
                    JPanel p = (JPanel) components[i];
                    p.setBorder(null);
                }
            }
            // \/ remove os botões de excluir;
            if( (name_componente != null) && (name_componente.indexOf(prefixos.prefixo_painel_interno_para_botao) != -1)  ){
                copia_painel_baixo.remove(components[i]);
            }
                    
        }//fim for;
        copia_painel_baixo.setBorder(null);
        
        JButton botao_enviar = new JButton("Enviar", new ImageIcon("icones/go-32.png"));
        botao_enviar.setName( operacoes.gerar_name_para_componente(prefixos.prefixo_botao_enviar) );
                
        copia_painel_baixo.add(new Gap(GAP), pos.nextRow());
        copia_painel_baixo.add(new Gap(GAP), pos.nextRow());
        copia_painel_baixo.add( operacoes_painel.add_botao_em_painel(botao_enviar), pos.nextRow().expandW() );
        
        return copia_painel_baixo;
    }
    
    public int numero_componentes_add_em_painel()
    {
        int con = 0;
        Component[] components = painel_baixo.getComponents();
        for (int i=0; i < components.length; i++){
            if( components[i] != null )
            {
                String name_componente = components[i].getName();
                if(name_componente != null){
                    con++;
                }
            }
        }
        return con;
    }
    
    public String[] obter_todos_os_titulos()
    {
          Vector<String> titulos = new Vector<String>();
          JPanel copia_painel_baixo = painel_baixo;
          Component[] components = copia_painel_baixo.getComponents();
          for (int i=0; i < components.length; i++)
          {
              String name_componente = components[i].getName();
              for (int j=0; j < prefixos.prefixos_paineis.length; j++)
              {
                  if( (name_componente != null) && (name_componente.indexOf(prefixos.prefixos_paineis[j]) != -1) )
                  {
                      JPanel p = (JPanel) components[i];
                      Vector<Component> cmps_p_internos = operacoes.pegar_componentes_por_prefixo(p, prefixos.prefixo_painel_interno);

                      for (int a=0; a < cmps_p_internos.size(); a++)
                      {
                          Vector<Component> cmps_p_internos_titulos = operacoes.pegar_componentes_por_prefixo( (JPanel) cmps_p_internos.get(a), prefixos.prefixo_titulos_dos_componentes);
                          for (int b=0; b < cmps_p_internos_titulos.size(); b++)
                          {
                              String name_componente_p = ((JLabel) cmps_p_internos_titulos.get(b)).getText();
                              titulos.add( name_componente_p );
                          }
                      }

                      Vector<Component> cmps_p_opcoes = operacoes.pegar_componentes_por_prefixo(p, prefixos.prefixo_titulos_dos_componentes);
                      if( cmps_p_opcoes.size() > 0 ) {
                            String titulos_opcoes = ((JLabel) cmps_p_opcoes.lastElement()).getText();
                            titulos.add( titulos_opcoes );
                      }
                  }
              }

          }//fim for;
          return titulos.toArray(new String[]{});
    }
    
}


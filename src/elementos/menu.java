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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
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
    
    private JTextField pesquisa = new JTextField(20);
    private DefaultListModel dlm = new DefaultListModel();
    private JList lista = new JList(dlm);
    private JButton botao_exibir_listagem = new JButton();
    private JButton botao_exibir_listagem2 = new JButton();
    private JButton botao_criar_formulario = new JButton("Gerar Formulário");
    
    private JMenu menu = new JMenu("Arquivo");
    private JMenuItem item_menu_sobre = new JMenuItem("Sobre");
    private JMenuItem item_menu_ajuda = new JMenuItem("Ajuda");
    private JMenuBar barra_de_menu = new JMenuBar();
    
    private JLabel x = new JLabel("Formulário de cadastrar");
    private JLabel x2 = new JLabel("Fazer backup do banco");
    private JLabel l_lixeira = new JLabel("Lixeira");
    private JPanel painel_direito = new JPanel(new GridBagLayout());
    
    private JPanel painel_cima = new JPanel(new GridBagLayout());
    private JPanel painel_baixo = new JPanel(new GridBagLayout());
    
    
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
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);          // Center window.
        super.setSize(1100, 700);
//        super.setExtendedState(JFrame.MAXIMIZED_BOTH);// <= maximixar a janela ao iniciar;
        super.setVisible(true);
    }
    
    /* \/ inseriri dados na tabela de listagem e exibir a mesma; */
    private JPanel inserir_dados_na_tabela(){
        
        ArrayList<Object[]> dados_da_tabela = new ArrayList<Object[]>();
//        banco.conectar();
        Vector<Vector<String>> linhas = banco.selectRestaurants();
        
        String[] colunas = banco.nome_colunas_consulta.toArray(new String[]{});
        banco.nome_colunas_consulta.clear();
        
        for (Vector<String> linha : linhas) {
            dados_da_tabela.add( linha.toArray(new Object[]{}) );
        }       
                
       return new exibir_listagem().obj("Lista de Clientes", colunas, dados_da_tabela);
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
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        
        //... First row
        content.add(scroll, pos.nextCol());
        content.add(new Gap(GAP), pos.nextCol());
        
        painel_cima.setBorder(BorderFactory.createTitledBorder("Componente"));
        painel_baixo.setBorder(BorderFactory.createTitledBorder("Formulário gerado"));
        
        content.add(painel_direito, pos.nextCol().expandir());
        
        painel_direito.add( operacoes_painel.painel_com_scroll_sem_borda(painel_cima) , pos_paineis_internos.expandir());
        painel_direito.add( operacoes_painel.painel_com_scroll_sem_borda(painel_baixo) , pos_paineis_internos.nextRow().expandir());
        pos.nextRow();
        painel_direito.add(botao_criar_formulario, pos.nextRow().expandW());
        
        
        lista.addMouseListener(new MouseListener(){
             @Override
             public void mouseClicked(MouseEvent me) {
                 
                 JList theList = (JList) me.getSource();
                 int index = theList.locationToIndex(me.getPoint());
                 Object o = theList.getModel().getElementAt(index);
                 String titulo = o.toString();
                 painel_cima.setBorder(BorderFactory.createTitledBorder("Componente: " + titulo));
                 
                 paineis_componentes pc = new paineis_componentes();
                 
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
//                 else if( menu_lateral.se_chave(MenuLateral.input_text_password, index) )
//                 {                        
//                        JPanel pl = pc.input_text_password();
//                        painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, pl, "scroll_pl", pos);
//                        operacoes_painel.atualizar_painel(painel_cima);
//                 }
//                 else if( menu_lateral.se_chave(MenuLateral.label, index) )
//                 {                        
//                        JPanel pl = pc.label();
//                        painel_cima = operacoes_painel.add_painel_filho_ao_PAI(painel_cima, pl, "scroll_pl", pos);
//                        operacoes_painel.atualizar_painel(painel_cima);
//                 }
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
                
                String painel_baixo_serializado = operacoes_painel.serializar_obj( remover_bordas_vermelhas_e_botes_excluir() );
                System.out.println( "Tamanho serializado: " + painel_baixo_serializado.length() );
                new popup(painel_baixo_serializado);
                
                painel_baixo.removeAll();
                operacoes_painel.atualizar_painel(painel_baixo);
            }
        });
        
        return content;
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
            if( (name_componente != null) && (name_componente.indexOf(prefixos.prefixo_btn_excluir_painel) != -1)  ){
                copia_painel_baixo.remove(components[i]);
            }
                    
        }//fim for;
        return copia_painel_baixo;
    }
     
}


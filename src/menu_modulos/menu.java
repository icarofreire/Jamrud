/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_modulos;

import banco.*;
import elementos.popup;
import elementos.prefixos;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import tabela_listagem.exibir_listagem;


/**
 *
 * @author icaro
 */
public class menu extends JFrame {
    
    private MenuLateral menu_lateral;
    private GBHelper pos = new GBHelper();
    private static final int BORDER = 12;  // Window border in pixels.
    private static final int GAP = 5;   // Default gap btwn components.
    private final int um_segundo = 1000;
    private JTextField pesquisa = new JTextField(20);
    public JList lista = new JList();
    private JButton botao_exibir_listagem = new JButton();
    private JButton botao_exibir_listagem2 = new JButton();
    private JMenu menu = new JMenu("Arquivo");
    private JMenuItem item_menu_sobre = new JMenuItem("Sobre");
    private JMenuItem item_menu_ajuda = new JMenuItem("Ajuda");
//    private JMenuItem item_menu_local = new JMenuItem("Definir local");
    private JMenuBar barra_de_menu = new JMenuBar();
    
    private JLabel x = new JLabel("Formulário de cadastrar");
    private JLabel x2 = new JLabel("Fazer backup do banco");
    private JLabel l_lixeira = new JLabel("Lixeira");
    private JPanel painel_esquerdo = new JPanel(new GridBagLayout());
    private JPanel painel_direito = new JPanel(new GridBagLayout());
    
    
    public menu(String titulo) {

        //... Create content pane with one button and set window attributes.
        JPanel windowContent = new JPanel();
        windowContent.setLayout(new BorderLayout());
        windowContent.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        windowContent.add( gui() , BorderLayout.CENTER);

        barra_de_menu.add(menu);
//        menu.add(item_menu_local);
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
        
//        item_menu_local.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent event) {
////                new painel_definir_local_salvar();
//            }
//        });

        super.setJMenuBar(barra_de_menu);
        
        //... Set the window characteristics.
        super.setContentPane(windowContent);
        super.pack();                               // Layout components.
        super.setTitle(titulo);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);          // Center window.
        super.setSize(1100, 700);
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);// <= maximixar a janela ao iniciar;
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
        painel_esquerdo.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        
        
        //... First row
        painel_esquerdo.add(scroll, pos.nextCol());
        painel_esquerdo.add(new Gap(GAP), pos.nextCol());
        
//        final JPanel pane = new JPanel();
        painel_direito.setBorder(BorderFactory.createTitledBorder(""));
//        pane.add(pesquisa);
        
        painel_esquerdo.add(painel_direito, pos.nextCol().expandir());
        
        lista.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                 JList source = (JList)lse.getSource();
                 int index = source.getSelectedIndex();
                 Object valor_selecionado = source.getSelectedValue();
                 
                 if( (!source.isSelectionEmpty()) )
                 {
                       String titulo = valor_selecionado.toString();
                       painel_direito.setBorder(BorderFactory.createTitledBorder(titulo));

                       if( menu_lateral.se_chave(MenuLateral.form_cadastrar, index) )
                       {
                              JPanel painel_temas = new painel_selecionar_formulario().painel_selecionar_forms();
                              JPanel p_intermediario = new JPanel();
                              p_intermediario.add(painel_temas);
                              painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, p_intermediario, "scroll_painel_temas", pos);
                       }
                       else if( menu_lateral.se_chave(MenuLateral.pesquisar_editar, index) )
                       {
                              JPanel pl = new painel_selecionar_tabela().painel_selecionar_tabela_forms();
                              JPanel p_intermediario = new JPanel();
                              p_intermediario.add(pl);
                              painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, p_intermediario, "scroll_pl", pos);
                       }
//                       else if( menu_lateral.se_chave(MenuLateral.backup_banco, index) )
//                       {
//                              JPanel painel_backup = new painel_backup().painel_p_backup();
//                              painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, painel_backup, "scroll_painel_backup", pos);
//                       }
                       else if( menu_lateral.se_chave(MenuLateral.temas, index) )
                       {
                              JPanel painel_temas = new painel_temas().painel_p_temas();
                              painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, painel_temas, "scroll_painel_temas", pos);
                       }
                       else if( menu_lateral.se_chave(MenuLateral.criar_formulario, index) )
                       {
                              JPanel painel_criar_formulario = new JPanel();
                              JButton botao_criar_formulario = new JButton("Criar formulário para cadastro", new ImageIcon("icones/construir-32.png") );
                              painel_criar_formulario.add( botao_criar_formulario );
                              painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, painel_criar_formulario, "scroll_painel_temas", pos);

                              botao_criar_formulario.addActionListener(new ActionListener(){
                                      @Override
                                      public void actionPerformed(ActionEvent ae) {
                                          elementos.menu m = new elementos.menu("Criar um formulário");                        
                                      }
                              });

                       }
                       else if( menu_lateral.se_chave(MenuLateral.gerar_planilha, index) )
                       {
                              JPanel painel_formulario = new painel_criar_planilha_excel().painel_criar_planilha();
                              JPanel p_intermediario = new JPanel();
                              p_intermediario.add(painel_formulario);
                              painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, p_intermediario, "scroll_painel_cadastrar", pos);
                       }
                       else
                       {
                              JPanel painel_formulario = new JPanel();
                              painel_formulario.add( new JLabel("Sem formulário cadastrado.") );
                              painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, painel_formulario, "scroll_painel_cadastrar", pos);
                       }
                 }
                
            }// fim public void valueChanged(ListSelectionEvent lse);
        });
        
        return painel_esquerdo;
    }
    
}


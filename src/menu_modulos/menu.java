/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_modulos;

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
    private static final int BORDER = 12;  // Window border in pixels.
    private static final int GAP = 5;   // Default gap btwn components.
    
    private JTextField pesquisa = new JTextField(20);
    private DefaultListModel dlm = new DefaultListModel();
    private JList lista = new JList(dlm);
    private JButton botao_exibir_listagem = new JButton();
    private JButton botao_exibir_listagem2 = new JButton();
    private JMenu menu = new JMenu("Arquivo");
    private JMenuItem item_menu_sobre = new JMenuItem("Sobre");
    private JMenuItem item_menu_ajuda = new JMenuItem("Ajuda");
    private JMenuBar barra_de_menu = new JMenuBar();
    
    private JLabel x = new JLabel("Formulário de cadastrar");
    private JLabel x2 = new JLabel("Fazer backup do banco");
    private JLabel l_lixeira = new JLabel("Lixeira");
    private JPanel painel_direito = new JPanel(new GridBagLayout());
    
    
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
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);// <= maximixar a janela ao iniciar;
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
        
//        final JPanel pane = new JPanel();
        painel_direito.setBorder(BorderFactory.createTitledBorder(""));
//        pane.add(pesquisa);
        
        content.add(painel_direito, pos.nextCol().expandir());
        
        
        lista.addMouseListener(new MouseListener(){

             @Override
             public void mouseClicked(MouseEvent me) {
                 
                 JList theList = (JList) me.getSource();
                 int index = theList.locationToIndex(me.getPoint());
                 Object o = theList.getModel().getElementAt(index);
                 String titulo = o.toString();
                 painel_direito.setBorder(BorderFactory.createTitledBorder(titulo));
                 
                 
                 if( menu_lateral.se_chave(MenuLateral.form_cadastrar, index) )
                 {
                         JPanel painel_cadastrar = new painel_cadastro().painel_p_cadastrar();
                         painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, painel_cadastrar, "scroll_painel_cadastrar", pos);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.pesquisar_editar, index) )
                 {                        
                        JPanel pl = inserir_dados_na_tabela();
                        painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, pl, "scroll_pl", pos);
                 }
                 else if( menu_lateral.se_chave("Lixeira", index) )
                 {
                        operacoes_painel.remover_componentes_painel(painel_direito);
                        painel_direito.add(l_lixeira);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.backup_banco, index) )
                 {
                        JPanel painel_backup = new painel_backup().painel_p_backup();
                        painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, painel_backup, "scroll_painel_backup", pos);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.temas, index) )
                 {
                        JPanel painel_temas = new painel_temas().painel_p_temas();
                        painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, painel_temas, "scroll_painel_temas", pos);
                 }
                 else if( menu_lateral.se_chave(MenuLateral.criar_formulario, index) )
                 {
                        JPanel painel_criar_formulario = new JPanel();
                        painel_criar_formulario.add( new JLabel("Criar formulário") );
                        painel_direito = operacoes_painel.add_painel_filho_ao_PAI(painel_direito, painel_criar_formulario, "scroll_painel_temas", pos);
                        Trei_big.menu_elementos();
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
        
        return content;
    }

    
}


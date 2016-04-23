/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

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
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author icaro
 */
public class menu extends JFrame {
    
    private static final int BORDER = 12;  // Window border in pixels.
    private static final int GAP = 5;   // Default gap btwn components.
    
    
    JTextField pesquisa = new JTextField(20);
    DefaultListModel dlm = new DefaultListModel();
    JList lista = new JList(dlm);
    JButton botao_exibir_listagem = new JButton();
    JButton botao_exibir_listagem2 = new JButton();
    
    JLabel x = new JLabel("Formulário de cadastrar");
    JLabel x2 = new JLabel("Fazer backup do banco");
    JLabel l_lixeira = new JLabel("Lixeira");
    JPanel painel_direito = new JPanel(new GridBagLayout());
    
    
    public menu() {
     
        //... Create content pane with one button and set window attributes.
        JPanel windowContent = new JPanel();
        windowContent.setLayout(new BorderLayout());
        windowContent.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        windowContent.add( gui() , BorderLayout.CENTER);
        
        
        //... Set the window characteristics.
        super.setContentPane(windowContent);
        super.pack();                               // Layout components.
        super.setTitle("menu");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);          // Center window.
        super.setSize(1100, 700);
        super.setVisible(true);
    }
    
    private JPanel remover_componentes_painel(JPanel painel){
        if(painel.getComponents().length >= 1){
            painel.removeAll();
            operacoes_painel.atualizar_painel(painel);
        }
        return painel;
    }
      
    
    /* \/ adiciona os componentes no painel, onde este conjunto 
    de comnponente representa um cadastro (Uma tabela de registros para exibir); \/ */
//    private JPanel cadastro_exibir(JPanel painel, GBHelper pos, JButton botao, String titulo, String subtitulo_){
//        
//        JLabel l1 = new JLabel(titulo);
//        JLabel subtitulo = new JLabel(subtitulo_);
////        JButton botao = new JButton(nome_botao);
//        botao.setText("Abrir");
//        
//        Font f = l1.getFont();
//        l1.setFont(f.deriveFont(f.getStyle() | Font.BOLD));// negrito
//        
//        painel.add(l1, pos.nextRow().expandW());
//        painel.add(subtitulo, pos.nextRow().expandW());
//        painel.add(botao, pos.nextCol().expandW());
//        painel.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
//        painel.add(new JSeparator(SwingConstants.HORIZONTAL), pos.nextRow().width(2).expandW());
//        
//        painel.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
//        
//        return painel;
//    }
    
    /* \/ monta um painel para exibir uma lista dos cadastros existentes; 
    seguindo de um botão para exibir para cadastro; \/*/
//    private JPanel painel_listas_cadastros(){
//        JPanel painel = new JPanel(new GridBagLayout());
//        GBHelper pos = new GBHelper();
//        
//        
//        painel = cadastro_exibir(painel, pos, 
//                this.botao_exibir_listagem, 
//                "Cadastro de Clientes", 
//                "Subtitulo para Cadastro de Clientes");
//        
////        painel = cadastro_exibir(painel, pos, 
////                this.botao_exibir_listagem2, 
////                "Cadastro de Modelos", 
////                "Subtitulo para Cadastro de Modelos de todos os Carros");
//        
//        
//        
//        return painel;
//    }
    
    /* \/ inseriri dados na tabela de listagem e exibir a mesma; */
    private JPanel inserir_dados_na_tabela(){
        String[] colunas = new String[] {
            "Id", "Name", "Hourly Rate", "Part Time", "Endereço", "CPF", "Sobrenome", "Caso Pendente",
            "campo-1", "campo-2", "campo-3", "campo-4"
        };
        
        ArrayList<Object[]> dados_da_tabela = new ArrayList<Object[]>();
        
        for (int count = 0; count < 200; count++) {
            Object[] data = new Object[]{ (count+1), "John", 40.0, false, "testee", "testee", "testee", "testee",
            "--teste-1", "--teste-2", "--teste-3", "--teste-4"}; 
            dados_da_tabela.add(data);
        }        
        
//        exibir_listagem l = new exibir_listagem("Lista de Clientes", colunas, dados_da_tabela);
        
       return new exibir_listagem().obj("Lista de Clientes", colunas, dados_da_tabela);
    }
    
     private JPanel gui() {
//        selectionCB.setEnabled(false);
        
      
        MenuLateral m = new MenuLateral();
        lista = m.lista();
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

//\\//\\//\\//\\//\\ GridBagLayout code begins here
        final GBHelper pos = new GBHelper();  // Create GridBag helper object.
        
        
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
                 
                 switch(index)
                 {
                    case 0:
                         remover_componentes_painel(painel_direito);
                         
                         JPanel painel_cadastrar = new painel_cadastro().painel_p_cadastrar();
                         JScrollPane scroll_painel_cadastrar = operacoes_painel.painel_com_scroll_sem_borda(painel_cadastrar);
                         scroll_painel_cadastrar.setName("scroll_painel_cadastrar");
                         
                         if( !operacoes_painel.se_componente_em_painel(painel_direito, "scroll_painel_cadastrar") ){
                             painel_direito.add(scroll_painel_cadastrar, pos.expandir());
                         }
                         
                         break;
//                    case 1:
//                        remover_componentes_painel(painel_direito);
//                        JLabel pl = new JLabel("Exibir painel de listagem: ");
//                        painel_direito.add(pl);
//                        painel_direito.add(botao_exibir_listagem);
//                        break;
                    case 2:
                        remover_componentes_painel(painel_direito);
                        painel_direito.add(l_lixeira);
                        break;
                    case 1:
                        remover_componentes_painel(painel_direito);
                        
//                        JPanel painel_lis_cad = painel_listas_cadastros();
//                        JScrollPane scroll_painel_lis_cad = new JScrollPane(painel_lis_cad);
//                        scroll_painel_lis_cad.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//                        
//                        painel_direito.add(scroll_painel_lis_cad ,pos.expandir());
                        
                        JPanel pl = inserir_dados_na_tabela();
                        remover_componentes_painel(painel_direito);
                        JScrollPane scroll_pl = operacoes_painel.painel_com_scroll_sem_borda(pl);
                        scroll_pl.setName("scroll_pl");
                        
                        if( !operacoes_painel.se_componente_em_painel(painel_direito, "scroll_pl") ){
                             painel_direito.add(scroll_pl, pos.expandir());
                        }
                        
//                        painel_direito.add(pl, pos.expandir());
                        break;
                    case 3:
                        remover_componentes_painel(painel_direito);
                        
//                        String resultados_encontrados[] = { "A", "B", "C", "D","E", "F", "G", "H","I", "J" };
//                        JPanel pres = new painel_resultados_busca().painel_exibir_resultados(/*resultados_encontrados*/);
//                        pres.setName("pres");
                        
//                        JScrollPane scroll_pres = new JScrollPane(pres);
//                        scroll_pres.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        
//                        if( !operacoes_painel.se_componente_em_painel(painel_direito, "pres") ){
//                             painel_direito.add(pres, pos.expandir());
//                        }
//                      
                        painel_direito.add(x2);
                        break;
                 }
                 
                 
                 
             }

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
        
//        botao_exibir_listagem.addActionListener(new ActionListener(){
//             @Override
//             public void actionPerformed(ActionEvent ae) {
//                 JPanel pl = lista();
//                 remover_componentes_painel(painel_direito);
//                 painel_direito.add(pl, pos.expandir());
//             }
//        });
        
 //\\//\\//\\//\\//\\ GridBagLayout code ends here
        return content;
    }

    
}

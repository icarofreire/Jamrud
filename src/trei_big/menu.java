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
import javax.swing.JTextField;

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
    JButton botao_exibir_listagem = new JButton("Abrir Registros");
    JLabel x = new JLabel("Formulário de cadastrar");
    JLabel x2 = new JLabel("Fazer backup do banco");
    JPanel painel_direito = new JPanel();
    
    
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
            painel.revalidate();
            painel.repaint();
        }
        return painel;
    }
    
    
    private void lista(){
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
        
        exibir_listagem l = new exibir_listagem("Lista de Clientes", colunas, dados_da_tabela);
        
//       return new exibir_listagem().obj("Lista de Clientes", colunas, dados_da_tabela);
    }
    
     private JPanel gui() {
//        selectionCB.setEnabled(false);
        
      
         MarioList m = new MarioList();
        lista = m.lista();
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(300, 0));
         
        
        JLabel imageLbl = new JLabel();
        imageLbl.setBounds(0, 0, 286, 40);
        imageLbl.setIcon(new ImageIcon("icones/pesquisar.png"));
        
        pesquisa.setBounds(50, 0, 286, 40);
        
        pesquisa.add(imageLbl);

        
        //... Create GridBagLayout content pane; set border.
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

//\\//\\//\\//\\//\\ GridBagLayout code begins here
        GBHelper pos = new GBHelper();  // Create GridBag helper object.
        
        
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
                         painel_direito.add(x);
                         break;
                    case 1:
                        remover_componentes_painel(painel_direito);
                        JLabel pl = new JLabel("Exibir painel de listagem: ");
                        painel_direito.add(pl);
                        painel_direito.add(botao_exibir_listagem);
                        break;
                    case 2:
                        remover_componentes_painel(painel_direito);
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
        
        botao_exibir_listagem.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent ae) {
                 lista();
//                 remover_componentes_painel(painel_direito);
//                 painel_direito.add(lista());
             }
        });
        
 //\\//\\//\\//\\//\\ GridBagLayout code ends here
        return content;
    }

    
}

class MarioList {

    private Map<String, ImageIcon> map = new HashMap<>();

    public JList lista() {
        
        String[] nameList = {"Cadastrar", "Pesquisar/Editar", "Backup"};
        
        try {
            map.put("Cadastrar", new ImageIcon("icones/cadastrar.png"));
            map.put("Pesquisar/Editar", new ImageIcon("icones/pesquisar_e_editar.png"));
            map.put("Backup", new ImageIcon("icones/1460597575_document-save.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
                
        JList list = new JList(nameList);
        list.setCellRenderer(new MarioListRenderer());
        return list;
    }

    private class MarioListRenderer extends DefaultListCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            label.setIcon(map.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }

}
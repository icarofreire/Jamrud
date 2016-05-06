/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author icaro
 */
public class painel_elementos extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private JPanel painel_grupo = new JPanel(new GridLayout(3,3));
    JPanel painel_config = new JPanel(new GridBagLayout());
    
    private GBHelper pos = new GBHelper();
    private DefaultListModel modelo_lista = new DefaultListModel();
    private JList lista = new JList(modelo_lista);
    
    private String titulo_janela = "Elementos do formulário";
    private int largura = 700;
    private int altura = 500;
    
    private JLabel txt = new JLabel("Nome:");
    private JTextField campo_nome = new JTextField(20);
    private JButton botao_adicionar = new JButton("Adicionar");
    private JButton botao_fechar = new JButton("Fechar");
    
    JCheckBox jCheckBox1 = new JCheckBox("Elemento 1");
    JCheckBox jCheckBox2 = new JCheckBox("Elemento 2");
    JCheckBox jCheckBox3 = new JCheckBox("Elemento 3");
    
    JCheckBox jCheckBox4 = new JCheckBox("Elemento 4");
    JCheckBox jCheckBox5 = new JCheckBox("Elemento 5");
    JCheckBox jCheckBox6 = new JCheckBox("Elemento 6");
    
    JCheckBox jCheckBox7 = new JCheckBox("Elemento 7");
//    JComboBox quantidade7 = new JComboBox();
    
    JCheckBox jCheckBox8 = new JCheckBox("Elemento 8");
    JCheckBox jCheckBox9 = new JCheckBox("Elemento 9");
    
    
    private JPanel painel_celula_elemento(JCheckBox elemento, String caminho_icone){
        JPanel painel = new JPanel(new GridBagLayout());
        JLabel picLabel = new JLabel(new ImageIcon(caminho_icone));
//        JLabel q = new JLabel("quantidade: ");
        
//        JComboBox quantidade7 = new JComboBox();
//        quantidade7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
//        quantidade7.setEnabled(false);
        
        painel.add(picLabel);
        painel.add(elemento, pos.nextRow());
//        painel.add(q, pos.nextRow());
//        painel.add(quantidade7, pos.nextRow());
        return painel;
    }
    
    public void gui_elementos() {
          aparencia_swing.mudar_aparencia(painel_elementos.class);
//        campo_valor.setText(texto);
//        campo_valor.setEditable(false);
//        
//        campo_valor.setColumns(20);
//        campo_valor.setRows(5);
//        JScrollPane scroll_observacoes = new JScrollPane();
//        scroll_observacoes.setViewportView(campo_valor);
//        scroll_observacoes.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
        
//        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
//        painel.add(scroll_observacoes, pos.expandir());
//        painel.add(botao_fechar, pos.nextRow().expandW());
        
//        painel.add(txt, pos.expandW());
//        painel.add(campo_nome, pos.nextCol().expandW());
        
        
        painel_config.setBorder(BorderFactory.createTitledBorder("Config elementos:"));
        lista.setBorder(BorderFactory.createTitledBorder("Lista de elementos:"));
        
        painel_grupo.add(painel_celula_elemento(jCheckBox1, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        painel_grupo.add(painel_celula_elemento(jCheckBox2, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        painel_grupo.add(painel_celula_elemento(jCheckBox3, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        
        painel_grupo.add(painel_celula_elemento(jCheckBox4, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        painel_grupo.add(painel_celula_elemento(jCheckBox5, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        painel_grupo.add(painel_celula_elemento(jCheckBox6, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        
        painel_grupo.add(painel_celula_elemento(jCheckBox7, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        painel_grupo.add(painel_celula_elemento(jCheckBox8, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        painel_grupo.add(painel_celula_elemento(jCheckBox9, "icones/elementos/Form Input (Text)/Form Input (Text)_32x32.png"));
        
        
        painel.add(painel_grupo, pos.expandir());
        painel.add(painel_config, pos.nextCol().expandir());
        
//        painel.add(lista, pos.nextCol().expandir());
        final GBHelper pos_config = new GBHelper();
        JScrollPane scroll_lista = new JScrollPane(lista);
        
        JPanel painel_x = new JPanel(new GridBagLayout());
        painel_x.setBorder(BorderFactory.createTitledBorder("Paixel X"));
        
        painel_config.add(painel_x, pos_config.expandir());
//        painel_config.add(scroll_lista, pos_config.nextRow().expandir());
        
        
        painel.add(botao_adicionar, pos.nextRow().expandW());
        painel.add(botao_fechar, pos.nextCol().expandW());
        
        
        jCheckBox7.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if(ie.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    modelo_lista.addElement("Elemento 7;");
                    
//                    painel_config.add(lista, pos_config.nextRow().expandir());
//                    operacoes_painel.atualizar_painel(painel);
                    
                } else {//checkbox has been deselected
                    modelo_lista.remove(modelo_lista.size()-1);
//                    painel_config.remove(lista);
                };
            }
        });
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
         botao_adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                modelo_lista.addElement("ADD Elemento;");
            }
        });
        
        super.setContentPane(painel);
        super.pack();
        super.setTitle(titulo_janela);
        super.setSize(new Dimension(largura, altura));
        super.setVisible(true);
        super.setLocationRelativeTo(null);
        
    }
    
}

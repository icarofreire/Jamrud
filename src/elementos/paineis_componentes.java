/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class paineis_componentes {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    
    public JPanel input_text(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        painel.setName("painel_input_text");
        
        JLabel lcmp1 = new JLabel("Nome:");
        final JTextField cmp1 = new JTextField(20);
        final JLabel titulo = new JLabel();
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                titulo.setText(cmp1.getText());
                painel_baixo.add( titulo, pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add( new JTextField(20), pos_painel_baixo.nextCol().expandW() );
                painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
                operacoes_painel.atualizar_painel(painel_baixo);
                
            }
        });
        
        return painel;
    }
    
    public JPanel input_text_password(){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        painel.setName("painel_input_text_password");
        
        JLabel lcmp1 = new JLabel("Nome:");
        JTextField cmp1 = new JTextField(20);
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        return painel;
    }
    
    public JPanel label(){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        painel.setName("painel_label");
        
        JLabel lcmp1 = new JLabel("Nome:");
        JTextField cmp1 = new JTextField(20);
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        return painel;
    }
    
    public JPanel grupo_opcoes_radio(JPanel painel_baixo, GBHelper pos_painel_baixo){

        JPanel painel = new criar_grupos_opcoes().grupo_botoes(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel grupo_opcoes_checkbox(JPanel painel_baixo, GBHelper pos_painel_baixo){
        
        JPanel painel = new criar_opcoes_checkbox().grupo_botoes(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel lista_select(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new criar_opcoes_select().lista_select(painel_baixo, pos_painel_baixo);
        return painel;
    }
    
    public JPanel texto(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        final JTextArea campo_valor = new JTextArea();
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        campo_valor.setColumns(20);
        campo_valor.setRows(5);
        JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_valor);
        
        final JLabel texto = new JLabel();
        
        painel.add(scroll_observacoes, pos.expandW());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                String html1 = "<html><body style='width: ";
                String html2 = "px'>";
                
                texto.setText( (html1+"500"+html2) + campo_valor.getText() );
                painel_baixo.add( texto, pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
                operacoes_painel.atualizar_painel(painel_baixo);
                
            }
        });
        
        
        return painel;
    }
    
    public JPanel area_para_texto(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        painel.setName("painel_input_text");
        
        JLabel lcmp1 = new JLabel("Titulo:");
        final JTextField cmp1 = new JTextField(20);
        final JLabel titulo = new JLabel();
        final JTextArea campo_valor = new JTextArea();
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        campo_valor.setColumns(20);
        campo_valor.setRows(5);
        final JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_valor);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                titulo.setText(cmp1.getText());
                painel_baixo.add( titulo, pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add( scroll_observacoes, pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
                operacoes_painel.atualizar_painel(painel_baixo);
                
            }
        });
        
        return painel;
    }
    
    public JPanel um_titulo(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        painel.setName("painel_input_text");
        
        JLabel lcmp1 = new JLabel("Titulo:");
        final JTextField cmp1 = new JTextField(20);
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        Font font = new Font("helvitica", Font.BOLD, 24);
        final JLabel titulo = new JLabel();
        titulo.setFont(font);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                titulo.setText(cmp1.getText());
                painel_baixo.add( titulo, pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
                operacoes_painel.atualizar_painel(painel_baixo);
                
            }
        });
        
        return painel;
    }    
    
}

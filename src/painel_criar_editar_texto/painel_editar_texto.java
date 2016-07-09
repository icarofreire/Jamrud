/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painel_criar_editar_texto;

import ferramenta_gui.GBHelper;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import trei_big.aviso;

/**
 *
 * @author icaro
 */
public class painel_editar_texto extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JTextArea campo_exibir_texto = new JTextArea();
    private JButton botao_aplicar = new JButton("Aplicar", new ImageIcon("icones/2-aplicar-24.png"));
    private JButton botao_editar = new JButton("Editar", new ImageIcon("icones/editar-24.png"));
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    private int largura = 500;
    private int altura = 200;
    private String titulo_janela;
    private String texto_editado;
    public boolean se_botao_fechar_clicado = false;
    
    public void exibir_texto(String titulo_janela, String texto) {
    
        botao_aplicar.setEnabled(false);
        
        campo_exibir_texto.setText(texto);
        campo_exibir_texto.setEditable(false);
        campo_exibir_texto.setColumns(20);
        campo_exibir_texto.setRows(5);
        JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_exibir_texto);
        scroll_observacoes.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
        
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                
        JPanel painel_botoes = new JPanel();
        GBHelper pos_painel_botoes = new GBHelper();
        painel_botoes.add(botao_aplicar);
        painel_botoes.add(botao_editar);
        painel_botoes.add(botao_fechar);        
        
        painel.add(scroll_observacoes, pos.expandir());
        painel.add(painel_botoes, pos.nextRow().expandW());
        
        botao_aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                texto_editado = campo_exibir_texto.getText();
                dispose();
            }
        });
        
        botao_editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                campo_exibir_texto.setEditable(true);
                campo_exibir_texto.requestFocusInWindow();
            }
        });
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                se_botao_fechar_clicado = true;
                dispose();
            }
        });
        
        campo_exibir_texto.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                botao_aplicar.setEnabled(true);
                botao_editar.setEnabled(false);
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        
        super.setContentPane(painel);
        super.pack();
        super.setTitle(titulo_janela);
        super.setSize(new Dimension(largura, altura));
        super.setVisible(true);
        super.setLocationRelativeTo(null);
    }
    
    // ***
    
    public void exibir_texto(String titulo_janela) {
    
        campo_exibir_texto.setColumns(20);
        campo_exibir_texto.setRows(5);
        JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_exibir_texto);
        scroll_observacoes.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
        
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                
        JPanel painel_botoes = new JPanel();
        GBHelper pos_painel_botoes = new GBHelper();
        painel_botoes.add(botao_aplicar);
        painel_botoes.add(botao_fechar);        
        
        painel.add(scroll_observacoes, pos.expandir());
        painel.add(painel_botoes, pos.nextRow().expandW());
        
        botao_aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if( !campo_exibir_texto.getText().isEmpty() ){
                    texto_editado = campo_exibir_texto.getText();                    
                    dispose();
                }else{
                    aviso.mensagem_atencao("O campo não pode estar vazio.");
                }
            }
        });
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                se_botao_fechar_clicado = true;
                dispose();
            }
        });
        
        super.setContentPane(painel);
        super.pack();
        super.setTitle(titulo_janela);
        super.setSize(new Dimension(largura, altura));
        super.setVisible(true);
        super.setLocationRelativeTo(null);
    }

    public String obter_texto_editado() {
        return texto_editado;
    }
 
}

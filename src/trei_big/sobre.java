/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import ferramenta_gui.GBHelper;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Dialogo que exibe as minhas informações como autor do programa; 
 * @author icaro
 */
public class sobre extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JTextArea campo_valor = new JTextArea();
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    private int largura = 500;
    private int altura = 500;
    
    private String titulo_janela = "Sobre";
    private String texto = "Desenvolvedor: Ícaro Freire Martins\n"
            + "Email: icarofre99@gmail.com\n"
            + "Telefone: (79)3241-55-68";
    
    public sobre() {
      
        campo_valor.setText(texto);
        campo_valor.setEditable(false);
        
        campo_valor.setColumns(20);
        campo_valor.setRows(5);
        JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_valor);
        scroll_observacoes.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
        
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        painel.add(scroll_observacoes, pos.expandir());
        painel.add(botao_fechar, pos.nextRow().expandW());
        
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
        
}

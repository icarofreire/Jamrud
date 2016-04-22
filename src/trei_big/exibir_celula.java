/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author icaro
 */
public class exibir_celula extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JTextArea observacoes = new JTextArea();
    private JButton botao_fechar = new JButton("Fechar");
    private int largura = 500;
    private int altura = 200;

    public void exibir_valor_celula(String titulo_janela, String texto) {
      
        observacoes.setText(texto);
        observacoes.setEditable(false);
        
        observacoes.setColumns(20);
        observacoes.setRows(5);
        JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(observacoes);
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

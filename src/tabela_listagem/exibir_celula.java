/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela_listagem;

import ferramenta_gui.GBHelper;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class exibir_celula extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JTextArea campo_valor = new JTextArea();
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    private int largura = 500;
    private int altura = 200;

    public exibir_celula(String titulo_janela, String texto) {
      
        campo_valor.setText(texto);
        campo_valor.setEditable(false);
        
        campo_valor.setColumns(20);
        campo_valor.setRows(5);
        JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_valor);
        scroll_observacoes.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
        
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        painel.add(scroll_observacoes, pos.expandir());
        painel.add(operacoes_painel.add_botao_em_painel(botao_fechar), pos.nextRow().expandW());
        
        
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

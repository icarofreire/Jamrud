/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.GBHelper;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class popup extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    private int largura = 600;
    private int altura = 600;
    private String titulo_janela;
    
    public popup(String nome_formulario, String painel_serializado) {
      
        this.titulo_janela = nome_formulario;
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JComponent comp = (JComponent) operacoes_painel.deserializar_obj(painel_serializado);
        painel.add( comp, pos.expandir());
        painel.add(botao_fechar, pos.nextRow().expandW());
        
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        super.setContentPane( operacoes_painel.painel_com_scroll_sem_borda(painel) );
        super.pack();
        super.setTitle(titulo_janela);
        super.setSize(new Dimension(largura, altura));
        super.setVisible(true);
        super.setLocationRelativeTo(null);
        
    }
    
}

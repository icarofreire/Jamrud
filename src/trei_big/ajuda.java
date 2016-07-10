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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author icaro
 */
public class ajuda extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JTextArea campo_valor = new JTextArea();
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    private JLabel l_info = new JLabel("Informações de ajuda.");
    private int largura = 600;
    private int altura = 600;
    
    private String titulo_janela = "Ajuda";
    private String texto = "Painel para Ajuda.\n\n"
            + "Realizar um simples tutorial;\n"
            + "exibir como manupilar o programa.";
    
    public ajuda()
    {
        gui_ajuda(texto);
    }
    
    public ajuda(String conteudo, int largura, int altura)
    {
        this.largura = largura;
        this.altura = altura;
        gui_ajuda(conteudo);
    }
    
    private void gui_ajuda(String conteudo)
    {
        l_info.setIcon(new ImageIcon("icones/informacao-64.png"));
        campo_valor.setText(conteudo);
        campo_valor.setEditable(false);
        
        campo_valor.setColumns(20);
        campo_valor.setRows(5);
        JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_valor);
        scroll_observacoes.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
        
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        painel.add(l_info);
        painel.add(scroll_observacoes, pos.nextRow().expandir());
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

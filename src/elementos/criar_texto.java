/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import trei_big.operacoes;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_texto {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    private String prefixo_painel_texto = "painel_texto_";
    private String prefixo_btn_excluir_painel = "btn_excluir_painel_";
    
    public JPanel texto(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        final JTextArea campo_valor = new JTextArea();
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
        campo_valor.setColumns(20);
        campo_valor.setRows(5);
        final JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_valor);
        
        final JLabel texto = new JLabel();
        
        painel.add(scroll_observacoes, pos.expandW());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                JTextArea campo_valor = new JTextArea();
                campo_valor.setColumns(20);
                campo_valor.setRows(5);
                final JScrollPane scroll_observacoes = new JScrollPane();
                scroll_observacoes.setViewportView(campo_valor);
                
                String html1 = "<html><body style='width: ";
                String html2 = "px'>";
                
                texto.setText( (html1+"500"+html2) + campo_valor.getText() );
                JPanel painel_texto = operacoes_painel.add_componente_em_painel(scroll_observacoes);
                painel_texto.setBorder(BorderFactory.createLineBorder(Color.RED));
                
                JButton btn_excluir_painel = new JButton("Excluir", new ImageIcon("icones/erro-24.png"));
                
                operacoes_painel.add_botao_excluir_painel(painel_baixo, pos_painel_baixo, painel_texto, btn_excluir_painel, prefixo_painel_texto, prefixo_btn_excluir_painel);
                
                painel_baixo.add( painel_texto, pos_painel_baixo.nextRow().expandW() );
                painel_baixo.add( btn_excluir_painel, pos_painel_baixo.nextCol().expandW() );
                
                painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
                operacoes_painel.atualizar_painel(painel_baixo);
                
            }
        });
        
        
        return painel;
    }
    
}

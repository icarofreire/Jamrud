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
import trei_big.aviso;
import trei_big.operacoes;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_texto {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    
    public JPanel texto(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        final JTextArea campo_valor = new JTextArea();
        JButton btn_aplicar = new JButton(nome_botao_aplicar, new ImageIcon("icones/add_formulario-24.png"));
        
        campo_valor.setColumns(20);
        campo_valor.setRows(5);
        final JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(campo_valor);
        
        painel.add(scroll_observacoes, pos.expandW());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                String html1 = "<html><body style='width: ";
                String html2 = "px'>";
                JLabel texto = new JLabel();
                String conteudo = campo_valor.getText().trim();
                if( !conteudo.isEmpty() )
                {
                    texto.setText( (html1+"500"+html2) + campo_valor.getText() );

                    operacoes_painel.add_componente_painel_baixo_e_add_botao_exluir(texto, painel_baixo, pos_painel_baixo, prefixos.prefixo_painel_texto);
                    campo_valor.setText(null);
                }else{
                    aviso.mensagem_atencao("Escreva um texto para adicionar em seu formulário.", "Conteúdo vazio");
                }
            }
        });
        
        
        return painel;
    }
    
}

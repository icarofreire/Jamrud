/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import trei_big.aviso;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_titulo {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    
    public JPanel um_titulo(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        
        JLabel lcmp1 = new JLabel("Titulo:");
        final JTextField cmp1 = new JTextField(20);
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar, new ImageIcon("icones/add_formulario-24.png"));
        final Font font = new Font("helvitica", Font.BOLD, 24);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_aplicar, pos.nextRow().expandW());
        
        btn_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nome_titulo = cmp1.getText().trim();
                if( !nome_titulo.isEmpty() )
                {
                    JLabel titulo = new JLabel();
                    titulo.setFont(font);
                    titulo.setText(nome_titulo);
                    
                    operacoes_painel.add_componente_painel_baixo_e_add_botao_exluir(titulo, painel_baixo, pos_painel_baixo, prefixos.prefixo_painel_criar_titulo);
                    cmp1.setText(null);
                }else{
                    aviso.mensagem_atencao("Informe o nome do titulo.", "Titulo vazio");
                }
            }
        });
        
        return painel;
    }
    
}

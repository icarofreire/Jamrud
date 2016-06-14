/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elementos;

import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import trei_big.aviso;
import trei_big.operacoes;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_campo_input {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    
    public JPanel input_text(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        
        JLabel lcmp1 = new JLabel("Titulo:");
        final JTextField cmp1 = new JTextField(20);
        final JLabel titulo = new JLabel();
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar, new ImageIcon("icones/add_formulario-24.png"));
        
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
                    titulo.setName( operacoes.gerar_name_para_componente(prefixos.prefixo_titulos_dos_componentes) );
                    JTextField campo = new JTextField(20);
                    titulo.setText(nome_titulo);
                    
                    JPanel p_int = operacoes_painel.add_componente_em_painel(titulo, campo, 2);
                    operacoes_painel.add_componente_painel_baixo_e_add_botao_exluir(p_int, painel_baixo, pos_painel_baixo, prefixos.prefixo_painel_criar_campo_input);
                    cmp1.setText(null);
                }else{
                    aviso.mensagem_atencao("Informe um titulo para o campo.", "Titulo vazio");
                }
            }
        });
        
        return painel;
    }
    
}

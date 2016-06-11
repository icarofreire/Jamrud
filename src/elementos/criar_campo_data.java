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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import trei_big.aviso;
import trei_big.operacoes;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class criar_campo_data {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private String nome_botao_aplicar = "Adicionar ao formulário";
    
    public JPanel input_data(final JPanel painel_baixo, final GBHelper pos_painel_baixo){
        
        JPanel painel = new JPanel(new GridBagLayout());
        GBHelper pos = new GBHelper();
        
        JLabel lcmp1 = new JLabel("Titulo:");
        final JTextField cmp1 = new JTextField(20);
        final JLabel titulo = new JLabel();
        
        JButton btn_aplicar = new JButton(nome_botao_aplicar);
        
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
                    final JFormattedTextField campo_data = new JFormattedTextField();
                    campo_data.setName( operacoes.gerar_name_para_componente(prefixos.prefixo_campo_data) );
 
                    try {
                        MaskFormatter dateMask = new MaskFormatter("##/##/####");
                        dateMask.install(campo_data);
                    } catch (ParseException ex) {
                        Logger.getLogger(criar_campo_data.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    campo_data.addFocusListener(new FocusListener(){
                        @Override
                        public void focusGained(FocusEvent fe) {
                            campo_data.setCaretPosition(0);
                        }

                        @Override
                        public void focusLost(FocusEvent fe) {
                        }
                    });
                    
                    
                    titulo.setText(nome_titulo);
                    
                    JPanel p_int = operacoes_painel.add_componente_em_painel(titulo, campo_data, 2);
                    operacoes_painel.add_componente_painel_baixo_e_add_botao_exluir(p_int, painel_baixo, pos_painel_baixo, prefixos.prefixo_painel_criar_campo_data);

                }else{
                    aviso.mensagem_atencao("Informe um titulo para o campo.", "Titulo vazio");
                }
            }
        });
        
        return painel;
    }
    
}

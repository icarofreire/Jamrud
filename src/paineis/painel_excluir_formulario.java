/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import banco.SQL;
import banco.banco;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import trei_big.aviso;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class painel_excluir_formulario {
    
        
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    
    private JPanel painel_interno = new JPanel(new GridBagLayout());
    private GBHelper pos_painel_interno = new GBHelper();
    
    private JLabel lcmp1 = new JLabel("Escolha o formulário que deseja remover:");
    private JComboBox select = new JComboBox();
    private JButton aplicar = new JButton("Excluir", new ImageIcon("icones/deletar-32.png"));
    
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private HashMap<String, String> nome_hash_form = new HashMap<String, String>();
    
    public JPanel excluir_formulario(){
        
        painel_interno.setName("painel_interno_para_selecionar_formularios");
        painel_interno.add(lcmp1, pos_painel_interno.expandW());
        painel_interno.add(select, pos_painel_interno.nextRow().expandW());
        painel_interno.add(new Gap(GAP) , pos_painel_interno.nextCol());  // Add a gap below
        painel_interno.add(aplicar, pos_painel_interno.nextCol().expandW());
        painel_interno.add(new Gap(GAP) , pos_painel_interno.nextRow());  // Add a gap below
        painel_interno.add(new JSeparator(SwingConstants.HORIZONTAL) , pos_painel_interno.nextRow().width(3).expandW());
        painel_interno.add(new Gap(50) , pos_painel_interno.nextRow());
        
        painel.add(painel_interno);
        
        Vector<Vector<String>> formularios = banco.obter_dados_da_tabela(SQL.nome_tabela_formulario);
        
        model.addElement("Selecione um formulário");
        for (int i = 0; i < formularios.size(); i++) {
            Vector<String> form = formularios.get(i);
            nome_hash_form.put( form.get(1) , form.get(2) );// nome do formulario => hash do formulario;
            model.addElement(form.get(1).replaceAll("_", " "));
        }
        
        select.setModel(model);
        
        
        aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int ind = select.getSelectedIndex();                
                if( ind > 0 )
                {
                    try{
                            String form = select.getSelectedItem().toString().replaceAll(" ", "_");
                            
                            if(
                                banco.executar_query(
                                        SQL.montar_sql_deletar_linha(SQL.nome_tabela_formulario.toUpperCase(), "NOME", form)
                                )
                                &&
                                banco.executar_query( "DROP TABLE " + form.toUpperCase() )
                            ){
                                aviso.mensagem_sucesso("Formulário excluído com sucesso.");
                                model.removeElementAt(select.getSelectedIndex());
                                select.setSelectedIndex(0);
                            }
                    }catch(Exception e)
                    {
                        //TODO exception
                    }
                    
                }else{
                    aviso.mensagem_atencao("Selecione um formulário.");
                }
            }
        });
        
        
        return painel;
        
    }
    
}

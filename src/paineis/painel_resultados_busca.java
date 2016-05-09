/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import ferramenta_gui.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author icaro
 */
// [[ELIMINAR CLASSE]]; 
public class painel_resultados_busca {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JButton botao_localizar = new JButton("Localizar");
    private JList lista_de_resultados;
    
    
    private JTable table;
    private int ind_linha_ir;

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setInd_linha_ir(int ind_linha_ir) {
        this.ind_linha_ir = ind_linha_ir;
    }
    
    
    public JPanel painel_exibir_resultados(String resultados_encontrados[]) {
        
        lista_de_resultados = new JList(resultados_encontrados);
//        lista_de_resultados.setListData(resultados_encontrados);
        JScrollPane scroll_lista_de_resultados = new JScrollPane(lista_de_resultados);
//        scroll_lista_de_resultados.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
//        lista_de_resultados.setBorder(BorderFactory.createTitledBorder("Resultados encontrados:"));
        scroll_lista_de_resultados.setBorder(BorderFactory.createTitledBorder("Resultados encontrados:"));

        
        scroll_lista_de_resultados.setPreferredSize(new Dimension(0, 150));
        
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(scroll_lista_de_resultados, pos.expandW());
        painel.add(botao_localizar, pos.nextRow().expandW());
        
        botao_localizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if( lista_de_resultados.getSelectedValue() != null )
                {
                    System.out.println("localizar: " + lista_de_resultados.getSelectedValue());
                    
                    if( table != null ){
//                        busca_tabela b = new busca_tabela();
//                        ArrayList<Integer> arr = b.comparacao_dados_coluna_2(table, indice_coluna, dado_pesquisar);
//                        ArrayList<String> arr2 = new ArrayList<String>();
//                        
//                        System.out.println("P: "+ dado_pesquisar + " em : " + indice_coluna);
//                        System.out.println(arr.size() + " resultados;");
//                        
//                        for(int i=0; i<arr.size(); i++){
//                            System.out.println("->"+arr.get(i));
//                            arr2.add("ID: " + arr.get(i));
//                        }
//                        String[] dados_lista = arr2.toArray(new String[]{});
//                        lista_de_resultados.setListData(dados_lista);
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione um item na lista fazer a localização.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        return painel;
    }
    
}

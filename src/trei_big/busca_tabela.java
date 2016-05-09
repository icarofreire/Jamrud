/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author icaro
 */
public class busca_tabela {// realizar uma busca na tabela de listagem;
    
    private int comparacao_dados_coluna(JTable table, int indice_coluna, String dado_pesquisar) {
        int p_indice = -1;
        TableModel tb = table.getModel();
        for(int j=0; j<table.getRowCount(); j++)
        {
            String dados_coluna = tb.getValueAt(j, indice_coluna).toString();
            if( dado_pesquisar.equalsIgnoreCase(dados_coluna) ){
                p_indice = (j+1); break;
            }
        }
        return p_indice;
    }
    
    public ArrayList<Integer> comparacao_dados_coluna_2(JTable table, int indice_coluna, String dado_pesquisar) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        int p_indice = -1;
        TableModel tb = table.getModel();
        for(int j=0; j<table.getRowCount(); j++)
        {
            String dados_coluna = tb.getValueAt(j, indice_coluna).toString();
//            System.out.println("-- comparar: " + dados_coluna + " = " + dado_pesquisar);
            if( dados_coluna.matches("(.*)" + dado_pesquisar + "(.*)") ){
                p_indice = (j+1);
                indices.add(p_indice);
            }
        }
        return indices;
    }    
    
    /* \/\/ modificar as linhas da tabela para exibir os resultados da busca; */
    public DefaultTableModel modificar_tabela_resultados_busca(JTable table, String[] colunas, ArrayList<Integer> linhas_res) {

        DefaultTableModel model2 = new DefaultTableModel(colunas,0);
        
        for(int ind=0; ind<linhas_res.size(); ind++) {
            model2.addRow( obter_linha_tabela(table, linhas_res.get(ind)) );
        }
        
        return model2;
    }
    
    public Object[] obter_linha_tabela(JTable table, int rowIndex) {
        if( rowIndex > 0 ){
            rowIndex--;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] result = new Object[model.getColumnCount()];

        for (int i = 0; i < model.getColumnCount(); i++) {
            result[i] = model.getValueAt(rowIndex, i);
        }
        
        return result;
    }
    
    public Object[] obter_linha_tabela_model(DefaultTableModel model, int rowIndex) {
        if( rowIndex > 0 ){
            rowIndex--;
        }
        Object[] result = new Object[model.getColumnCount()];

        for (int i = 0; i < model.getColumnCount(); i++) {
            result[i] = model.getValueAt(rowIndex, i);
        }
        
        return result;
    }
    
    /* o segundo parametro rowIndex, deve ser o indice levando em consideração que o model é um array:
    ou seja, o primeiro indice começa em 0(Zero). */
    public Object[] obter_linha_tabela_normal(DefaultTableModel model, int rowIndex) {

        Object[] result = new Object[model.getColumnCount()];

        for (int i = 0; i < model.getColumnCount(); i++) {
            result[i] = model.getValueAt(rowIndex, i);
        }
        
        return result;
    }
    
    public boolean atualizar_registros(DefaultTableModel model)
    {
        boolean flag = false;
        for(int i=0; i<model.getRowCount(); i++)
        {
                Object[] linha = obter_linha_tabela_normal(model, i);
                int ID = operacoes_painel.obj_to_int(linha[0]);
                      
                for(int j=1; j<model.getColumnCount(); j++){
                        String sql = SQL.montar_sql_modificar(model.getColumnName(j), linha[j].toString(), ID);
                        flag = banco.executar_query(sql);
                }
        }
        return flag;
    }
    
    
}

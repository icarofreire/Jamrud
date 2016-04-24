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
    
//    public void realizar_busca_na_tabela(JTable table, String pesquisa, String coluna_pesquisar, int indice_coluna) {
//        
//        System.out.println("buscar: " + pesquisa + " em: " + coluna_pesquisar );
//                    
//        int p_indice = comparacao_dados_coluna(table, indice_coluna, pesquisa);
//        if( p_indice != -1 ){
//            int ind_linha_ir = p_indice;
//            selecionar_linha_tabela(table, ind_linha_ir);
//            modificar_direcao_scroll(table, ind_linha_ir, 0);
//        }else{
//            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.", "Não encontrado", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    
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
            if( dado_pesquisar.matches("(.*)" + dados_coluna + "(.*)") ){
                p_indice = (j+1);
                indices.add(p_indice);
            }
        }
        return indices;
    }    
    
//    private void selecionar_linha_tabela(JTable table, int linha) {
//        linha--;
//        table.getSelectionModel().setSelectionInterval(linha, linha);
//        table.scrollRectToVisible(new Rectangle(table.getCellRect(linha, 0, true)));
//    }
    
    
//    private void modificar_direcao_scroll(JTable table, int rowIndex, int vColIndex) {
//
//        JViewport viewport = (JViewport) table.getParent();
//        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
//        Rectangle viewRect = viewport.getViewRect();
//        rect.setLocation(rect.x - viewRect.x, rect.y - viewRect.y);
//
//        int centerX = (viewRect.width - rect.width) / 2;
//        int centerY = (viewRect.height - rect.height) / 2;
//        if (rect.x < centerX) {
//          centerX = -centerX;
//        }
//        if (rect.y < centerY) {
//          centerY = -centerY;
//        }
//        rect.translate(centerX, centerY);
//        viewport.scrollRectToVisible(rect);
//    }
    
    
    public DefaultTableModel busca(JTable table, String[] colunas, ArrayList<Integer> linhas_res) {
        
        TableModel modelo_t = table.getModel();
        DefaultTableModel model2 = new DefaultTableModel(colunas,0);
                    
        for(int j=0; j<modelo_t.getRowCount(); j++) {
            
                Object id = modelo_t.getValueAt(j, 0);
                if( linhas_res.contains(id) )
                {   
                        Object[] valores = new Object[modelo_t.getColumnCount()];
                        for (int count = 0; count < modelo_t.getColumnCount(); count++) {
                                Object valor = modelo_t.getValueAt(j, count);
                                valores[count] = valor;                                    
                        }
                        model2.addRow( valores );
                                
                }
                           
        }
        return model2;
        
    }
    
}

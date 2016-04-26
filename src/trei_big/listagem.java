/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

/**
 *
 * @author icaro
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

//////////////////////////////////////////////////////////////////// Painel de listagem de dados na tabela;
public class listagem extends JPanel {
    
    private JPanel painel_principal = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();  // Create GridBag helper object.
    
    //... constants
    private static final int BORDER = 12;  // Window border in pixels.
    private static final int GAP = 5;   // Default gap btwn components.
    private static final int BOTAO_DIREITO_MOUSE = 3;
    private int n_colunas_tabela = 0;
    private String[] colunas;
    private ArrayList<Object[]> dados_tabela;
    private String titulo_listagem;
    
    //... classes
    private estado_editar estado = new estado_editar();
    private exibir_celula popup = new exibir_celula();
    private busca_tabela busca = new busca_tabela();
    
    //... models da tabela
    private DefaultTableModel model;
    private DefaultTableModel linhas_resultado_busca;
    
    //... GUI componentes
    private JTable table;
    private JLabel findLbl = new JLabel("Pesquisar:", JLabel.LEFT);
    private JLabel resultados = new JLabel();
    private JTextField campo_pesquisar = new JTextField(20);
    private JButton buscar = new JButton("Buscar");
    private JButton editar = new JButton("Editar registros");
    private JButton deletar = new JButton("Deletar registro");
    private JButton zerar_busca = new JButton("Desfazer busca");
    private JComboBox coluna_busca = new JComboBox();
    
    public JPanel replaceDialog = new JPanel();
    
    // \/ obter todos os dados de uma linha expefifica do model da tabela;
    private Object obter_linha_model(DefaultTableModel model, int linha) {
        Vector v_Object = model.getDataVector();
        return v_Object.get(linha);
    }
    
    /* \/\/ se uma linha da tabela for modificada; \/\/ */
    private void se_tabela_for_modificada(final boolean em_busca) {
        table.getModel().addTableModelListener(
        new TableModelListener() 
        {
            @Override
            public void tableChanged(TableModelEvent tme) {
                  if(!em_busca){
                        System.out.println("[FORA] celula modificada: " +  tme.getLastRow() );
                        estado.setLinha_modificada(tme.getLastRow());
                        editar.setEnabled(true);
                  }else{// \/\/ tabela modificada nos resultados da busca;
                        System.out.println("[BUSCA] celula modificada: " +  tme.getLastRow() );
                        estado.setLinhas_modificadas_busca(tme.getLastRow());
                        editar.setEnabled(true);
                        
                        Object linha_modificada = obter_linha_model(linhas_resultado_busca, tme.getLastRow());
                        
                        Vector v_linha_modificada = (Vector) linha_modificada;
                        int ID_linha = (int) v_linha_modificada.get(0);
                        
                                
                        /* \/\/ adiciona a linha modificada depois da linha antiga;
                        e depois remove a linha antiga; */
                        model.insertRow(ID_linha, (Vector) linha_modificada);
                        model.removeRow(ID_linha-1);

                        estado.apagar_ultimo();
                        
                        /* \/\/ peguei a ultima linha registrada como modificada(por conta da remoção da linha logo acima. /\);
                        apaguei esta linha e registrei como modificada a linha anterior a esta. ou seja: a linha atual menos 1
                        (Por causa da remoção de uma linha para atualizar a tabela, com a linha que foi editada, nos resultados da busca);
                        */
                        int ultima = estado.linhas_modificadas.get(estado.linhas_modificadas.size()-1);
                        estado.apagar_ultimo();
                        estado.setLinha_modificada(ultima-1);
                        
                  }
            }
        });
    }
    
    //============================================================== construtor
    public listagem(String titulo_listagem, String[] nomes_colunas, ArrayList<Object[]> dados_da_tabela) {
        
        this.colunas = nomes_colunas;
        this.dados_tabela = dados_da_tabela;
        this.titulo_listagem = titulo_listagem;
        
        editar.setEnabled(false);
        deletar.setEnabled(false);
        zerar_busca.setEnabled(false);
        
        gui();
        replaceDialog = painel_principal;
    }

    private Component TableExample()
    {
        
        model = new DefaultTableModel(colunas,0);
        
        coluna_busca.addItem("Selecione um campo para busca.");
        for(int i=0; i<colunas.length; i++){
            coluna_busca.addItem(colunas[i]);
        }
         
        this.n_colunas_tabela = colunas.length;

        table = new JTable(){  /* \/ Não permitir editar a coluna ID; \/ */
                                public boolean isCellEditable(int row,int column) {
                                    int coluna_ID = 0;
                                    if( column == coluna_ID ) { return false; }
                                    return true;
                                }
                            };
        
        JScrollPane scroll = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//AUTO_RESIZE_OFF <= para aparecer a barra de scroll horizontal;
        
        
        for (int count = 0; count < this.dados_tabela.size(); count++) {
            model.addRow( this.dados_tabela.get(count) );
        }
        
        table.setModel(model);      
        
        
        table.setDefaultRenderer(Object.class, new colorir_linha_tabela(estado));
        
        TableCellEditor fce = new editar_celula_tabela();
        table.setDefaultEditor(Object.class, fce);
        
        
        table.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("deletar linha: " + table.getSelectedRow() + " => " + me.getButton() );
                deletar.setEnabled(true);
                
                if( me.getButton() == BOTAO_DIREITO_MOUSE ){// <= se clicado com o botão direito do mouse;
                    
                    int linha = table.rowAtPoint(me.getPoint());
                    int coluna = table.columnAtPoint(me.getPoint());
                    
                    popup.exibir_valor_celula(
                            table.getColumnName(coluna) + ": " + table.getValueAt(linha, 0), 
                            table.getValueAt(linha, coluna).toString()
                    );
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

        });
        
        
        se_tabela_for_modificada(false);
        
        
        deletar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(
                        null, 
                        "Deseja realmente apagar este registro ?",
                        "Deletar registro: " + model.getValueAt(table.getSelectedRow(), 0), 
                        dialogButton);
                
                if(dialogResult == 0) {
                  System.out.println("Deletar registro: " + model.getValueAt(table.getSelectedRow(), 0) );
                 
                } 
            }
        });
        
        
        editar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                    estado.apagar_linhas();
                    editar.setEnabled(false);
                    
                  System.out.println("Editar registros.");
                  
                  // \/ Todos os conteúdos da tabela atual a serem salvos \/;
//                  TableModel tb = table.getModel();
//                  for(int i=0; i<table.getColumnCount(); i++){
//                    for(int j=0; j<table.getRowCount(); j++){
//                        System.out.println( tb.getValueAt(j, i) );
//                    }
//                  }
                  
                  JOptionPane.showMessageDialog(null, "Registros atualizados com sucesso!", "OK!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                int ind = coluna_busca.getSelectedIndex()-1;                
                if( ind >= 0 )
                {
//                    estado.apagar_linhas();
                    zerar_busca.setEnabled(true);
                    table.setModel(model);
                    estado.apagar_linhas_busca();
                    
                    // \/ ... iniciar busca ... \/;
                    String pesquisa = campo_pesquisar.getText();
                    String coluna_pesquisar = colunas[ind];
                    System.out.println("buscar: " + pesquisa + " em: " + coluna_pesquisar );
                    
//                    busca.realizar_busca_na_tabela(table, pesquisa, coluna_pesquisar, ind);
                    

                    
                    ArrayList<Integer> linhas_res = busca.comparacao_dados_coluna_2(table, ind, pesquisa);
                    for(int i=0; i<linhas_res.size(); i++){
                        System.out.println("->"+linhas_res.get(i));
                    }
                    
                    linhas_resultado_busca = busca.busca(table, colunas, linhas_res);
                    if( linhas_resultado_busca.getRowCount() > 0 ){
                        table.setModel(linhas_resultado_busca);
                        resultados.setText( "Busca: " + linhas_resultado_busca.getRowCount() + " Resultados." );
                        
                        se_tabela_for_modificada(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.", "Não encontrado", JOptionPane.ERROR_MESSAGE);
                    }

                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione uma coluna para realizar a pesquisa.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
                
            }//fim void actionPerformed;
        });
        
        
        zerar_busca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerar_busca.setEnabled(false);
                campo_pesquisar.setText(null);
                resultados.setText(null);
                coluna_busca.setSelectedIndex(0);
                estado.apagar_linhas_busca();
                table.setModel(model);
            }
        });
                
        
        return scroll;//new JScrollPane(table);
    }
    
    //======================================================== createContentPane
    private void gui() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1, GAP, GAP));
        buttonPanel.add(coluna_busca);
        buttonPanel.add(buscar);

        painel_principal.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        
        //... primeira linha
        painel_principal.add(findLbl, pos);
        painel_principal.add(new Gap(GAP), pos.nextCol());
        painel_principal.add(campo_pesquisar      , pos.nextCol().expandW());
        painel_principal.add(new Gap(GAP), pos.nextCol());
        painel_principal.add(buttonPanel , pos.nextCol());
        
        painel_principal.add(new Gap(GAP) , pos.nextRow());
        painel_principal.add(new Gap(GAP) , pos.nextRow());
        
        painel_principal.add(TableExample(), pos.width(this.n_colunas_tabela+1).expandir());
        
        painel_principal.add(new Gap(GAP) , pos.nextRow());
        painel_principal.add(new Gap(GAP) , pos.nextRow());
        
        JPanel p_botoes_finais = new JPanel();
        p_botoes_finais.setLayout(new GridLayout(1, 2, GAP, GAP));
        p_botoes_finais.add(editar);
        p_botoes_finais.add(deletar);
        p_botoes_finais.add(zerar_busca);
        p_botoes_finais.add(resultados);
        
        painel_principal.add(p_botoes_finais  , pos.width(3));
    }
    

}


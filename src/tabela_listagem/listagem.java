/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabela_listagem;

/**
 *
 * @author icaro
 */
import banco.SQL;
import banco.banco;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import trei_big.aviso;
import trei_big.operacoes_painel;

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
    
    private String nome_da_tabela;
    
    //... Hash do ID da linha da tabela, que carrega como valor o indice desta linha na tabela;
    private HashMap<Integer, Integer> map_IDlinha_indice = new HashMap<Integer, Integer>();
    
    //... classes
//    private estado_editar estado = new estado_editar();
    private exibir_celula popup = new exibir_celula();
    private busca_tabela busca = new busca_tabela();
    
    //... models da tabela
    private DefaultTableModel model;
    private DefaultTableModel linhas_resultado_busca;
    
    //... GUI componentes
    private JTable table;
    private JLabel lbl_pesquisar = new JLabel("Pesquisar: ", JLabel.LEFT);
    private JLabel resultados = new JLabel();
    private JTextField campo_pesquisar = new JTextField(20);
    private JButton buscar = new JButton("Buscar", new ImageIcon("icones/pesquisar-32.png"));
    private JButton editar = new JButton("Atualizar registros", new ImageIcon("icones/atualizar-32.png"));
    private JButton deletar = new JButton("Deletar registro", new ImageIcon("icones/deletar-32.png"));
    private JButton zerar_busca = new JButton("Desfazer busca", new ImageIcon("icones/voltar-32.png"));
    private JComboBox coluna_busca = new JComboBox();
    
    public JPanel replaceDialog = new JPanel();
    
    
    /* \/\/ se uma linha da tabela for modificada; \/\/ */
    private void se_tabela_for_modificada(final boolean em_busca) {
        table.getModel().addTableModelListener(
        new TableModelListener() 
        {
            @Override
            public void tableChanged(TableModelEvent tme) {
                  if(!em_busca){
//                        System.out.println("[FORA] celula modificada: " +  tme.getLastRow() );
                        editar.setEnabled(true);
                  }else{// \/\/ tabela modificada nos resultados da busca;
//                        System.out.println("[BUSCA] celula modificada: " +  tme.getLastRow() );
                        editar.setEnabled(true);
                        
                            
                        Object[] linha_modificada = busca.obter_linha_tabela_model(linhas_resultado_busca, tme.getLastRow()+1);
                            
                        int lug = map_IDlinha_indice.get( operacoes_painel.obj_to_int(linha_modificada[0]) );
                            
//                        System.out.println( "ID: " + linha_modificada[0] + " lug: " + lug );
                            
                        Object[] linha_modificada_p = busca.obter_linha_tabela_model(model, lug+1);

                        model.insertRow(lug, linha_modificada);
                        model.removeRow(lug-1);

                        
                  }
            }
        });
    }
    
    //============================================================== construtor
    public listagem(String titulo_listagem, String[] nomes_colunas, ArrayList<Object[]> dados_da_tabela, String nome_da_tabela) {
        
        this.colunas = nomes_colunas;
        this.dados_tabela = dados_da_tabela;
        this.titulo_listagem = titulo_listagem;
        this.nome_da_tabela = nome_da_tabela;
        
        editar.setEnabled(false);
        deletar.setEnabled(false);
        zerar_busca.setEnabled(false);
        
        gui();
        replaceDialog = painel_principal;
    }
    
    public void exibir_numero_registros() {
        int n = model.getRowCount();
        if( n > 1 ){
            resultados.setText( model.getRowCount() + ": Registros." );
        }else if( n == 1 ){
            resultados.setText( "1: Registro." );
        }else{
            resultados.setText( "Nenhum registro." );
        }
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
        exibir_numero_registros();
        
        
//        table.setDefaultRenderer(Object.class, new colorir_linha_tabela(estado));
        
        TableCellEditor fce = new editar_celula_tabela();
        table.setDefaultEditor(Object.class, fce);
        
        
        table.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
//                System.out.println("deletar linha: " + table.getSelectedRow() + " => " + me.getButton() );
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
                
                if( table.getSelectedRow() >= 0 )
                {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(
                            null, 
                            "Deseja realmente apagar este registro ?",
                            "Deletar registro: " + model.getValueAt(table.getSelectedRow(), 0), 
                            dialogButton);

                    if(dialogResult == 0)
                    {
                        Object ID = model.getValueAt(table.getSelectedRow(), 0);
                        int ID__ = Integer.parseInt(ID.toString());
//                        System.out.println("Deletar registro: [" + ID__ + "]" );

                        if(banco.executar_query(SQL.montar_sql_deletar_linha(nome_da_tabela.toUpperCase(), ID__)))
                        {
                            model.removeRow(table.getSelectedRow());
                            aviso.mensagem_ok("Registro deletado com sucesso!");
                        } 
                    }
                }else{
                    deletar.setEnabled(false);
                    aviso.mensagem_atencao("Selecione um registro na tabela para poder deletar.");
                }
            }
        });
        
        
        editar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                    editar.setEnabled(false);
                    
//                  System.out.println("Editar registros.");
                  
                  if( busca.atualizar_registros(model) && busca.atualizar_registros(linhas_resultado_busca) ){
                        aviso.mensagem_sucesso("Registros atualizados com sucesso!");
                  }
                  
            }
        });
        
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                int ind = coluna_busca.getSelectedIndex()-1;                
                if( ind >= 0 )
                {
                    String pesquisa = campo_pesquisar.getText();
                    String coluna_pesquisar = colunas[ind];
                    if( !pesquisa.isEmpty() )
                    {
                        zerar_busca.setEnabled(true);
                        deletar.setEnabled(false);
                        table.setModel(model);

                        // \/ ... iniciar busca ... \/;
    //                    String pesquisa = campo_pesquisar.getText();
    //                    String coluna_pesquisar = colunas[ind];
//                        System.out.println("buscar: " + pesquisa + " em: " + coluna_pesquisar );


                        ArrayList<Integer> linhas_res = busca.comparacao_dados_coluna_2(table, ind, pesquisa);
                        for(int i=0; i<linhas_res.size(); i++){
//                            System.out.println("->"+linhas_res.get(i));

                            Object[] obj_ = busca.obter_linha_tabela(table, linhas_res.get(i));
                            map_IDlinha_indice.put(
                                    operacoes_painel.obj_to_int( obj_[0] ),
                                    linhas_res.get(i)
                            );
                        }

                        linhas_resultado_busca = busca.modificar_tabela_resultados_busca(table, colunas, linhas_res);
                        if( linhas_resultado_busca.getRowCount() > 0 ){
                            table.setModel(linhas_resultado_busca);
                            resultados.setText( "Busca: " + linhas_resultado_busca.getRowCount() + " Resultados." );

                            se_tabela_for_modificada(true);
                        }else{
                            aviso.mensagem_falha("Nenhum registro encontrado.", "Não encontrado");
                        }
                    }else{
                        aviso.mensagem_atencao("Digite alguma informação que deseja buscar.", "Campo vazio");
                    }
                }else{
                    aviso.mensagem_atencao("Selecione uma coluna para realizar a pesquisa.");
                }
                
            }//fim void actionPerformed;
        });
        
        
        zerar_busca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerar_busca.setEnabled(false);
                deletar.setEnabled(false);
                campo_pesquisar.setText(null);
                exibir_numero_registros();
                coluna_busca.setSelectedIndex(0);
                table.setModel(model);
            }
        });
                
        
        return scroll;
    }
    
    //======================================================== createContentPane
    private void gui() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1, GAP, GAP));
        buttonPanel.add(coluna_busca);
        buttonPanel.add(buscar);

        painel_principal.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        
//        lbl_pesquisar.setIcon(new ImageIcon("icones/pesquisar-32.png"));
        
        //... primeira linha
        painel_principal.add(lbl_pesquisar, pos);
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


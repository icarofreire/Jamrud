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

//////////////////////////////////////////////////////////////////// DemoGridBag
public class listagem extends JPanel {
    
    private JPanel content = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();  // Create GridBag helper object.
    
    //================================================================ constants
    private static final int BORDER = 12;  // Window border in pixels.
    private static final int GAP    = 5;   // Default gap btwn components.
    private static final int BOTAO_DIREITO_MOUSE = 3;
    private int n_colunas_tabela = 0;
    private String[] colunas;
    private ArrayList<Object[]> dados_tabela;
    private String titulo_listagem;
    
    private boolean linhas_deletadas = false;
    
    private estado_editar estado = new estado_editar();
    private exibir_celula popup = new exibir_celula();
    private busca_tabela busca = new busca_tabela();
//    private painel_resultados_busca painel_res_busca = new painel_resultados_busca();
    
    //=================================================================== fields
    //... GUI components
    JLabel    findLbl     = new JLabel("Pesquisar:"   , JLabel.LEFT);
    JTextField findTF     = new JTextField(20);
    JButton   buscar     = new JButton("Buscar");
    JButton   editar     = new JButton("Editar registros");
    JButton   deletar  = new JButton("Deletar registro");
    JComboBox coluna_busca = new JComboBox();
    
    
//    JDialog   replaceDialog = new JDialog();
//    public JFrame   replaceDialog = new JFrame();
    public JPanel   replaceDialog = new JPanel();
    
    private void selecionar_linha_tabela(JTable table, int linha){
        linha--;
        table.getSelectionModel().setSelectionInterval(linha, linha);
        table.scrollRectToVisible(new Rectangle(table.getCellRect(linha, 0, true)));
    }
    
    private static void modificar_direcao_scroll(JTable table, int rowIndex, int vColIndex) {

        JViewport viewport = (JViewport) table.getParent();
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
        Rectangle viewRect = viewport.getViewRect();
        rect.setLocation(rect.x - viewRect.x, rect.y - viewRect.y);

        int centerX = (viewRect.width - rect.width) / 2;
        int centerY = (viewRect.height - rect.height) / 2;
        if (rect.x < centerX) {
          centerX = -centerX;
        }
        if (rect.y < centerY) {
          centerY = -centerY;
        }
        rect.translate(centerX, centerY);
        viewport.scrollRectToVisible(rect);
    }
    
        
    private int comparacao_dados_coluna(JTable table, int indice_coluna, String dado_pesquisar){
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
    
    //--
    
    //============================================================== constructor
    public listagem(String titulo_listagem, String[] nomes_colunas, ArrayList<Object[]> dados_da_tabela) {
        
        this.colunas = nomes_colunas;
        this.dados_tabela = dados_da_tabela;
        this.titulo_listagem = titulo_listagem;
        
        editar.setEnabled(false);
        deletar.setEnabled(false);
        
        //... Create a dialog box with GridBag content pane.
//        replaceDialog.setContentPane(createContentPane());
//        replaceDialog.setTitle(this.titulo_listagem);
//        replaceDialog.pack();
//        replaceDialog.setLocationRelativeTo(this);
//        
//        replaceDialog.setSize(new Dimension(900, 600));
//        replaceDialog.setVisible(true);
        
//            replaceDialog.add( createContentPane() );
        createContentPane();
        replaceDialog = content;//createContentPane();
  
        
        /*
        //... Create a button for the window to display this dialog.
//        JButton showDialogBtn = new JButton("---> Exibir Listagem <---");
//        showDialogBtn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                replaceDialog.setVisible(true);
//            }
//        });
//
//        //... Create content pane with one button and set window attributes.
//        JPanel windowContent = new JPanel();
//        windowContent.setLayout(new BorderLayout());
//        windowContent.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
//        windowContent.add(showDialogBtn, BorderLayout.CENTER);
//        
//        
//        //... Set the window characteristics.
//        super.setContentPane(windowContent);
//        super.pack();                               // Layout components.
//        super.setTitle("DemoGridBag");
//        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        super.setLocationRelativeTo(null);          // Center window.
        */
    }

    private Component TableExample()
    {
        
        final DefaultTableModel model = new DefaultTableModel(colunas,0);   
        
        coluna_busca.addItem("Selecione um campo para busca.");
        for(int i=0; i<colunas.length; i++){
            coluna_busca.addItem(colunas[i]);
        }
         
        this.n_colunas_tabela = colunas.length;

        final JTable table = new JTable();
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
        
        table.getModel().addTableModelListener(
        new TableModelListener() 
        {
            @Override
            public void tableChanged(TableModelEvent tme) {
                  System.out.println("celula modificada: " +  tme.getLastRow() );
                  estado.setLinha_modificada(tme.getLastRow());
                  editar.setEnabled(true);
            }
        });
        
        
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
                    // \/ ... iniciar busca ... \/;
                    String pesquisa = findTF.getText();
                    String coluna_pesquisar = colunas[ind];
                    System.out.println("buscar: " + pesquisa + " em: " + coluna_pesquisar );
                    
//                    busca.realizar_busca_na_tabela(table, pesquisa, coluna_pesquisar, ind);
                    
                    // \/\/ APAGAR DO MODEL DA TABELA, TODAS AS LINHAS QUE NÃO FAZEM PARTE DO RESULTADO DA BUSCA;
                    
                    ArrayList<Integer> linhas_res = busca.comparacao_dados_coluna_2(table, ind, pesquisa);
                    for(int i=0; i<linhas_res.size(); i++){
                        System.out.println("->"+linhas_res.get(i));
                    }
                    
                    TableModel modelo_t = table.getModel();
//                    DefaultTableModel model2 = new DefaultTableModel(colunas,0);
                    
                        for(int j=0; j<modelo_t.getRowCount(); j++) {
                            
                            Object id = modelo_t.getValueAt(j, 0);
                            if( linhas_res.contains(id) == false )
                            {
                                int id_remover = ((int) id);
                                    model.removeRow(id_remover);
                                    estado.linhas_modificadas.remove(id);
//                                System.out.println( "remover: " + ((int) id) );
                            }
                           
                        }
                    
                    
//                    if(!linhas_deletadas){
//                        int id = 5;
//                        model.removeRow(id-1);
//                        estado.linhas_modificadas.remove(new Integer(id-1));
//                    }
                      
                    // /\/\ APAGAR DO MODEL DA TABELA, TODAS AS LINHAS QUE NÃO FAZEM PARTE DO RESULTADO DA BUSCA;
                    
                    
//                    content.add(new Gap(GAP) , pos.nextRow());
//                    content.add(new Gap(GAP) , pos.nextRow());
//                    String resultados_encontrados[] = { "A", "B", "C", "D","E", "F", "G", "H","I", "J" };
                    
//                    painel_resultados_busca painel_res_busca = new painel_resultados_busca();
                    
//                    painel_res_busca.setTable(table);
                    

           
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione uma coluna para realizar a pesquisa.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
                
            }//fim void actionPerformed;
        });
        
        
        findTF.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if( ke.getKeyCode() == KeyEvent.VK_ENTER ) 
                {
                        // \/ ... iniciar busca ... \/;
                        int ind = coluna_busca.getSelectedIndex()-1;                
                        if( ind >= 0 )
                        {
                            String pesquisa = findTF.getText();
                            String coluna_pesquisar = colunas[ind];
                            System.out.println("buscar: " + pesquisa + " em: " + coluna_pesquisar );

                            busca.realizar_busca_na_tabela(table, pesquisa, coluna_pesquisar, ind);

                        }else{
                            JOptionPane.showMessageDialog(null, "Selecione uma coluna para realizar a pesquisa.", "Atenção", JOptionPane.WARNING_MESSAGE);
                        }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
                
        
        return scroll;//new JScrollPane(table);
    }
    
    //======================================================== createContentPane
    private void createContentPane() {
//        selectionCB.setEnabled(false);
        
        
        //... Create an independent GridLayout panel of buttons. 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1, GAP, GAP));
        buttonPanel.add(coluna_busca);
        buttonPanel.add(buscar);

        
        //... Create GridBagLayout content pane; set border.
//        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, 
                BORDER, BORDER));

//\\//\\//\\//\\//\\ GridBagLayout code begins here
//        GBHelper pos = new GBHelper();  // Create GridBag helper object.
        
        
        //... First row
        content.add(findLbl, pos);
        content.add(new Gap(GAP), pos.nextCol());
        content.add(findTF      , pos.nextCol().expandW());
        content.add(new Gap(GAP), pos.nextCol());
        content.add(buttonPanel , pos.nextCol()
                                             /*.align(GridBagConstraints.NORTH)*/);
        
        content.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
        content.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
        
        content.add(TableExample(), pos.width(this.n_colunas_tabela+1).expandir());
        
        content.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
        content.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
        
        JPanel p_botoes_finais = new JPanel();
        p_botoes_finais.setLayout(new GridLayout(1, 2, GAP, GAP));
        p_botoes_finais.add(editar);
        p_botoes_finais.add(deletar);
//        p_botoes_finais.add(fechar);
        
        content.add(p_botoes_finais  , pos.width(3));
        
 //\\//\\//\\//\\//\\ GridBagLayout code ends here
//        return content;
    }
    

}


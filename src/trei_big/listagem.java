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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

//////////////////////////////////////////////////////////////////// DemoGridBag
public class listagem extends JFrame {
    //================================================================ constants
    private static final int BORDER = 12;  // Window border in pixels.
    private static final int GAP    = 5;   // Default gap btwn components.
    private int n_colunas_tabela = 0;
    private String[] colunas;
    private ArrayList<Object[]> dados_tabela;
    private String titulo_listagem;
    
    //=================================================================== fields
    //... GUI components
    JLabel    findLbl     = new JLabel("Pesquisar:"   , JLabel.LEFT);
    JTextField findTF     = new JTextField(20);
    JButton   buscar     = new JButton("Buscar");
    JButton   editar     = new JButton("Editar registros");
    JButton   deletar  = new JButton("Deletar registro");
    JComboBox coluna_busca = new JComboBox();
    
    
//    JDialog   replaceDialog = new JDialog();
    public JFrame   replaceDialog = new JFrame();
    
    private void selecionar_linha_tabela(JTable table, int linha){
        linha--;
        table.getSelectionModel().setSelectionInterval(linha, linha);
        table.scrollRectToVisible(new Rectangle(table.getCellRect(linha, 0, true)));
    }
    
    //--
    private static void scrollToCenter(JTable table, int rowIndex, int vColIndex) {

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
        replaceDialog.setContentPane(createContentPane());
        replaceDialog.setTitle(this.titulo_listagem);
        replaceDialog.pack();
        replaceDialog.setLocationRelativeTo(this);
        
        replaceDialog.setSize(new Dimension(900, 600));
        replaceDialog.setVisible(true);
//            replaceDialog.add( createContentPane() );
        
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
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        
        for (int count = 0; count < this.dados_tabela.size(); count++) {
            model.addRow( this.dados_tabela.get(count) );
        }
        
        table.setModel(model);      
        
        
        table.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("deletar linha: " + table.getSelectedRow() + " => " + me.getButton() );
                deletar.setEnabled(true);
                
                if( me.getButton() == 3 ){// <= se clicado com o botão direito do mouse;
                    
                    int linha = table.rowAtPoint(me.getPoint());
                    int coluna = table.columnAtPoint(me.getPoint());
                    
                    JOptionPane.showMessageDialog(listagem.this, 
                            table.getValueAt(linha, coluna).toString(), 
                            table.getColumnName(coluna) + ": " + table.getValueAt(linha, 0), 
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent me) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent me) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
        table.getModel().addTableModelListener(
        new TableModelListener() 
        {
            @Override
            public void tableChanged(TableModelEvent tme) {
                  System.out.println("celula modificada: " +  tme.getLastRow() );
                  editar.setEnabled(true);
            }
        });
        
        
        deletar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(
                        listagem.this, 
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
               
                  System.out.println("Editar registros.");
                  
                  // \/ Todos os conteúdos da tabela atual a serem salvos \/;
//                  TableModel tb = table.getModel();
//                  for(int i=0; i<table.getColumnCount(); i++){
//                    for(int j=0; j<table.getRowCount(); j++){
//                        System.out.println( tb.getValueAt(j, i) );
//                    }
//                  }
                  
            }
        });
        
        
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                int ind = coluna_busca.getSelectedIndex()-1;                
                if( ind >= 0 ){
                    String pesquisa = findTF.getText();
                    String coluna_pesquisar = colunas[ind];
                    System.out.println("buscar: " + pesquisa + " em: " + coluna_pesquisar );
                    
                    int p_indice = comparacao_dados_coluna(table, ind, pesquisa);
                    if( p_indice != -1 ){
                        
                        int ind_linha_ir = p_indice;
                        selecionar_linha_tabela(table, ind_linha_ir);
                        scrollToCenter(table, ind_linha_ir, 0);
                    }else{
                        JOptionPane.showMessageDialog(listagem.this, "Nenhum registro encontrado.", "Não encontrado", JOptionPane.ERROR_MESSAGE);
                    }
           
                }else{
                    JOptionPane.showMessageDialog(listagem.this, "Selecione uma coluna para realizar a pesquisa.", "Atenção", JOptionPane.WARNING_MESSAGE);
                }
                
            }//fim void actionPerformed;
        });
                
        
        return scroll;//new JScrollPane(table);
    }
    
    //======================================================== createContentPane
    private JPanel createContentPane() {
//        selectionCB.setEnabled(false);
        
        
        //... Create an independent GridLayout panel of buttons. 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1, GAP, GAP));
        buttonPanel.add(coluna_busca);
        buttonPanel.add(buscar);

        
        //... Create GridBagLayout content pane; set border.
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, 
                BORDER, BORDER));

//\\//\\//\\//\\//\\ GridBagLayout code begins here
        GBHelper pos = new GBHelper();  // Create GridBag helper object.
        
        
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
        return content;
    }
    

}

class exibir_listagem {
    public JPanel p1;
    exibir_listagem(final String titulo_listagem, final String[] nomes_colunas, final ArrayList<Object[]> dados_da_tabela)
    {
        //... Set Look and Feel.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                listagem window = new listagem(titulo_listagem, nomes_colunas, dados_da_tabela);
                window.setVisible(true);
            }
        });
    }
    exibir_listagem(){};
    public JPanel obj(final String titulo_listagem, final String[] nomes_colunas, final ArrayList<Object[]> dados_da_tabela){
        listagem window = new listagem(titulo_listagem, nomes_colunas, dados_da_tabela);
//        p1 = window.replaceDialog;
        return p1;
    }
    
}
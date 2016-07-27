/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import banco.*;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import trei_big.aviso;

/**
 *
 * @author icaro
 */
public class painel_backup {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    
    /*\/ gui para importar \/*/
    private String arquivo_selecionado;
    private JFileChooser arquivo_importar = new JFileChooser();
    private JLabel txt = new JLabel("Importar Banco de Dados:");
    private JTextField caminho_importar = new JTextField(20);
    private JButton btn_arquivo_importar = new JButton("Selecionar arquivo", new ImageIcon("icones/documento-32.png"));
    private JButton btn_importar = new JButton("Importar", new ImageIcon("icones/salvar-32.png"));
    
    
    private JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
    
    
    /*\/ gui para exportar \/*/
    private String pasta_selecionada;
    private JFileChooser produrar_pasta = new JFileChooser();
    private JLabel txt2 = new JLabel("Exportar Banco de Dados:");
    private JTextField caminho_exportar = new JTextField(20);
    private JButton btn_selecionar_exportar = new JButton("Selecionar local", new ImageIcon("icones/pasta-32.png"));
    private JButton btn_exportar = new JButton("Exportar", new ImageIcon("icones/exportar-32.png"));
    
    public JPanel painel_p_backup(){
        
        caminho_importar.setEditable(false);
        caminho_exportar.setEditable(false);
        btn_importar.setEnabled(false);
        btn_exportar.setEnabled(false);
        
        arquivo_importar.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        arquivo.setCurrentDirectory(new java.io.File("."));
        
//        arquivo.setDialogTitle("Escolha um local para salvar");
//        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        arquivo.setAcceptAllFileFilterUsed(false);
        
        produrar_pasta.setDialogTitle("Escolha um local para salvar");
        produrar_pasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        produrar_pasta.setAcceptAllFileFilterUsed(false);
        
        painel.add(txt, pos.expandW());
        painel.add(btn_arquivo_importar, pos.nextRow().expandW());
        painel.add(caminho_importar, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_importar, pos.nextRow().expandW());
        
        
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(separador , pos.nextRow().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        
        painel.add(txt2, pos.expandW());
        painel.add(btn_selecionar_exportar, pos.nextRow().expandW());
        painel.add(caminho_exportar, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_exportar, pos.nextRow().expandW());
        
        
        /*\/ funcionalidades para exportar; \/*/
        btn_selecionar_exportar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if (produrar_pasta.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    pasta_selecionada = produrar_pasta.getSelectedFile().toString() + File.separator;
                    caminho_exportar.setText(pasta_selecionada);
                    btn_exportar.setEnabled(true);   
                }
                
            }
        });
        
        btn_exportar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if( !pasta_selecionada.isEmpty() )
                {
//                    String sufixo_ateatorio = banco.gerar_nome(5);
//                    if(
//                        banco.executar_query("CALL SYSCS_UTIL.SYSCS_EXPORT_TABLE(null,'" + 
//                                SQL.nome_tabela.toUpperCase() + 
//                                "','" + 
//                                pasta_selecionada + SQL.nome_tabela.toUpperCase() + "-" + sufixo_ateatorio +
//                                ".del',';','%',null)")
//                      )
//                    {
//                        aviso.mensagem_sucesso("Dados exportados com sucesso.");
//                    }
                }
                
            }
        });
        /*/\ funcionalidades para exportar; /\*/
        
        
        /*\/ funcionalidades para importar; \/*/
        btn_arquivo_importar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if (arquivo_importar.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    arquivo_selecionado = arquivo_importar.getSelectedFile().toString();
                    caminho_importar.setText(arquivo_selecionado);
                    btn_importar.setEnabled(true);   
                }
                
            }
        });
        
        btn_importar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if( !arquivo_selecionado.isEmpty() )
                {
                    String nome_colunas = "NOME,ENDERECO,TELEFONE,DATA,OBSERVACOES";//<= para inserir;
                    String indice_colunas = "2,3,4,5,6";//<= para inserir;
//                    if(
//                        banco.executar_query("CALL SYSCS_UTIL.SYSCS_IMPORT_DATA_LOBS_FROM_EXTFILE(null, '" + SQL.nome_tabela.toUpperCase() + 
//                                "', '"+ nome_colunas +"', '"+ indice_colunas +"','" + arquivo_selecionado + "', ';','%',null,  0)")
//                      )
//                    {
//                        aviso.mensagem_sucesso("Dados importados com sucesso.");
//                    }
                }
                
            }
        });
        /*/\ funcionalidades para importar; /\*/
        
        
        return painel;
    }
    
}

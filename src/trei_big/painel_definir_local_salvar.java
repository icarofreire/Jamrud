/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.SQL;
import banco.banco;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author icaro
 */
public class painel_definir_local_salvar extends JDialog {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JButton botao_aplicar = new JButton("Aplicar", new ImageIcon("icones/aplicar-24.png"));
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    private int largura = 700;
    private int altura = 150;
    
    private String titulo_janela = "Definir local para salvar arquivos";
    
    /*\/ gui para exportar \/*/
    private String pasta_selecionada;
    private JFileChooser produrar_pasta = new JFileChooser();
    private JTextField caminho_exportar = new JTextField(20);
    private JButton btn_selecionar_exportar = new JButton("Selecionar local", new ImageIcon("icones/pasta-24.png"));
    
    private static String caminho_definido;
    private static String ID_caminho_definido;
    
    public painel_definir_local_salvar() {
        
        Obter_caminho_e_definido();
        if( caminho_definido != null ){
            caminho_exportar.setText(caminho_definido);
        }
        
        caminho_exportar.setEditable(false);
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        produrar_pasta.setDialogTitle("Escolha um local para salvar");
        produrar_pasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        produrar_pasta.setAcceptAllFileFilterUsed(false);
        
        painel.add(btn_selecionar_exportar, pos.nextRow().expandW());
        painel.add(caminho_exportar, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(botao_aplicar, pos.nextRow().expandW());
        painel.add(botao_fechar, pos.nextCol().expandW());
        
        
        /*\/ funcionalidades para exportar; \/*/
        btn_selecionar_exportar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if (produrar_pasta.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    pasta_selecionada = produrar_pasta.getSelectedFile().toString() + File.separator;
                    caminho_exportar.setText(pasta_selecionada);
                }
                
            }
        });
        
        botao_aplicar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if( (pasta_selecionada != null) && (!pasta_selecionada.isEmpty()) )
                {                    
                    if( (caminho_definido == null) && (ID_caminho_definido == null) )
                    {
                        //Inserido;
                        banco.executar_query( 
                                SQL.montar_sql_insert( SQL.nome_tabela_local_arquivos, new String[]{"LOCAL_ARQUIVOS"}, new String[]{pasta_selecionada}) 
                        );
                    }else
                    {
                        //TEM UM local definido;
//                        banco.exibir_tabela( SQL.nome_tabela_local_arquivos.toUpperCase() );
                        banco.executar_query( SQL.montar_sql_modificar_local_arquivo(pasta_selecionada, ID_caminho_definido) );
                    }
                    dispose();
                }else{
                    aviso.mensagem_erro("Selecione um local para salvar os arquivos.");
                }
            }
        });
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        super.setContentPane(painel);
        super.pack();
        super.setTitle(titulo_janela);
        super.setSize(new Dimension(largura, altura));
        super.setVisible(true);
        super.setLocationRelativeTo(null);
        
    }
    
    private static void Obter_caminho_e_definido()
    {
        Vector<Vector<String>> linhas = banco.obter_dados_da_tabela(SQL.nome_tabela_local_arquivos);
        if( (linhas != null) && (!linhas.isEmpty()) )
        {
            Vector<String> vl = linhas.lastElement();
            if( !vl.lastElement().isEmpty() ){
                caminho_definido = vl.lastElement();
                ID_caminho_definido = vl.firstElement();
            }
        }
    }
        
}

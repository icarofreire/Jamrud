/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author icaro
 */
public class painel_backup {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JFileChooser arquivo = new JFileChooser();
    private JLabel txt = new JLabel("Importar Banco de Dados:");
    private JTextField caminho = new JTextField(20);
    private JButton btn_arquivo = new JButton("Selecionar arquivo", new ImageIcon("icones/documento-32.png"));
    private JButton btn_salvar = new JButton("Importar", new ImageIcon("icones/salvar-32.png"));
    private String pasta_selecionada;
    private JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
    
    private JLabel txt2 = new JLabel("Exportar Banco de Dados:");
    private JTextField caminho2 = new JTextField(20);
    private JButton btn_arquivo2 = new JButton("Selecionar local", new ImageIcon("icones/pasta-32.png"));
    private JButton btn_salvar2 = new JButton("Exportar", new ImageIcon("icones/exportar-32.png"));
    
    public JPanel painel_p_backup(){
        
        caminho.setEditable(false);
        btn_salvar.setEnabled(false);
//        arquivo.setCurrentDirectory(new java.io.File("."));
        arquivo.setDialogTitle("Escolha um local para salvar");
        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        arquivo.setAcceptAllFileFilterUsed(false);
        
        painel.add(txt, pos.expandW());
        painel.add(btn_arquivo, pos.nextRow().expandW());
        painel.add(caminho, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_salvar, pos.nextRow().expandW());
        
        
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(separador , pos.nextRow().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(new Gap(GAP) , pos.nextRow());
        
        
        painel.add(txt2, pos.expandW());
        painel.add(btn_arquivo2, pos.nextRow().expandW());
        painel.add(caminho2, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());
        painel.add(btn_salvar2, pos.nextRow().expandW());
        
        
        btn_arquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if (arquivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    pasta_selecionada = arquivo.getSelectedFile().toString();
                    caminho.setText(pasta_selecionada);
                    btn_salvar.setEnabled(true);
                }
                
            }
        });
        
        
        btn_salvar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                System.out.println("[FAZER BACKUP DO BANCO DE DADOS]");
                
            }
        });
        
        
        return painel;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JLabel txt = new JLabel("Informe o local para salvar o arquivo:");
    private JTextField caminho = new JTextField(20);
    private JButton btn_arquivo = new JButton("Selecionar pasta");
    private JButton btn_salvar = new JButton("Salvar dados");
    private String pasta_selecionada;
    
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
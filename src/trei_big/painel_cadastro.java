/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author icaro
 */
public class painel_cadastro {
    
    private static final int GAP = 5;   // Default gap btwn components.
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    
    public JPanel painel_p_cadastrar(){
            
        JLabel lcmp1 = new JLabel("Nome:");
        JTextField cmp1 = new JTextField(20);
        
        JLabel lcmp2 = new JLabel("Endereço:");
        JTextField cmp2 = new JTextField(20);
        
        JLabel lcmp3 = new JLabel("Telefone:");
        JTextField cmp3 = new JTextField(20);
        
        JLabel lcmp4 = new JLabel("Data:");
        JTextField cmp4 = new JTextField(20);
        
        JLabel l1 = new JLabel();
        Font f = l1.getFont();
        l1.setFont(f.deriveFont(f.getStyle() | Font.BOLD));// negrito
        
        JButton btn_cadastrar = new JButton("Cadastrar");
        JButton btn_cancelar = new JButton("Cancelar");
        
        JLabel lobs = new JLabel("Observações:");
        JTextArea observacoes = new JTextArea();
        
        observacoes.setColumns(20);
        observacoes.setRows(5);
        JScrollPane scroll_observacoes = new JScrollPane();
        scroll_observacoes.setViewportView(observacoes);
        
        painel.add(lcmp1, pos.expandW());
        painel.add(cmp1, pos.nextCol().expandW());
        
        
        painel.add(lcmp2, pos.nextRow().expandW());
        painel.add(cmp2, pos.nextCol().expandW());
        
        
        painel.add(lcmp3, pos.nextRow().expandW());
        painel.add(cmp3, pos.nextCol().expandW());
        
        
        painel.add(lcmp4, pos.nextRow().expandW());
        painel.add(cmp4, pos.nextCol().expandW());
        
        
        painel.add(lobs, pos.nextRow().expandW());
        painel.add(scroll_observacoes, pos.nextCol().expandW());
        
        
        painel.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
        painel.add(btn_cadastrar, pos.nextRow().expandW());
        painel.add(btn_cancelar, pos.nextCol().expandW());
        
        painel.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
        
        return painel;
    }
    
}

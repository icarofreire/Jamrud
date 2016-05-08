/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author icaro
 */
public class painel_temas {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JLabel lcmp1 = new JLabel("Opções para modificar a aparência do sistema:");
    private JComboBox temas = new JComboBox();
    private JButton aplicar = new JButton("Aplicar", new ImageIcon("icones/aplicar-32.png"));
    
    public JPanel painel_p_temas(){
        
        temas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { 
            "Selecione um tema",
            "Tema: 1",
            "Tema: 2",
            "Tema: 3",
            "Tema: 4",
            "Tema: 5",
            "Tema: 6",
            "Tema: 7",
            "Tema: 8",
            "Tema: 9",
            "Tema: 10",
            "Tema: 11",
            "Tema: 12",
            "Tema: 13",
            "Tema: 14"            
        }));
        
        painel.add(lcmp1, pos.expandW());
        painel.add(temas, pos.nextRow().expandW());
        painel.add(aplicar, pos.nextCol().expandW());
        painel.add(new Gap(GAP) , pos.nextRow());  // Add a gap below
        
        
        aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int ind = temas.getSelectedIndex();                
                if( ind > 0 )
                {
                    System.out.println("selecionado: " + ind );
                    try{
                        switch(ind)
                        {
                            case 1:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                                break;
                            
                            case 2:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                                break;
                            
                            case 3:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                                break;
                                                            
                            case 4:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                                break;                        
                                                                                            
                            case 5:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                                break;
                                                                                                                     
                            case 6:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                                break;
                                                                                                                                                     
                            case 7:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
                                break;
                                                                                                                                                     
                            case 8:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                                break;
                                                                                                                                                     
                            case 9:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                                break;
                                                                                                                                                                                     
                            case 10:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                                break;
                                                                                                                                                                                     
                            case 11:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                                break;
                                                                                                                                                                                     
                            case 12:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                                break;
                                                                                                                                                                                     
                            case 13:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                                break;
                                                                                                                                                                                     
                            case 14:
                                UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                                break;
                        }
                        
                        aviso.mensagem_ok("Aparência alterada com sucesso.");
                        operacoes_painel.atualizar_painel(painel);
                    }catch(Exception e)
                    {
                        //TODO exception
                    }
                    
                }else{
                    aviso.mensagem_atencao("Selecione um tema.");
                }
            }
        });
        
        
        return painel;
        
    }
    
}

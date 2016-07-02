/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import banco.SQL;
import banco.banco;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import trei_big.aviso;
import trei_big.criar_planilha_excel;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class painel_criar_planilha_excel {
    
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    
    private JPanel painel_interno = new JPanel(new GridBagLayout());
    private GBHelper pos_painel_interno = new GBHelper();
    
    private JLabel lcmp1 = new JLabel("Escolha a tabela que deseja criar uma planilha:");
    private JComboBox select = new JComboBox();
    private JTextField caminho = new JTextField();
    private JButton btn_selecionar_local = new JButton("Local para salvar", new ImageIcon("icones/pasta-32.png"));
    private JButton aplicar = new JButton("Criar planilha", new ImageIcon("icones/aplicar-32.png"));
    
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    
    private String pasta_selecionada;
    private JFileChooser produrar_pasta = new JFileChooser();
    
    public JPanel painel_criar_planilha(){
        
        aplicar.setEnabled(false);
        caminho.setEditable(false);
        
        produrar_pasta.setDialogTitle("Escolha um local para salvar");
        produrar_pasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        produrar_pasta.setAcceptAllFileFilterUsed(false);
        
        painel_interno.setName("painel_interno_para_selecionar_formularios");
        painel_interno.add(lcmp1, pos_painel_interno.expandW());
        painel_interno.add(select, pos_painel_interno.nextRow().expandW());
        painel_interno.add(new Gap(GAP), pos_painel_interno.nextRow().expandW());
        painel_interno.add(caminho, pos_painel_interno.nextRow().expandW());
        painel_interno.add(new Gap(GAP) , pos_painel_interno.nextCol());  // Add a gap below
        painel_interno.add(btn_selecionar_local, pos_painel_interno.nextCol().expandW());
        painel_interno.add(new Gap(GAP), pos_painel_interno.nextCol().expandW());
        painel_interno.add(aplicar, pos_painel_interno.nextCol().expandW());
        painel_interno.add(new Gap(GAP) , pos_painel_interno.nextRow());  // Add a gap below
        painel_interno.add(new JSeparator(SwingConstants.HORIZONTAL) , pos_painel_interno.nextRow().width(3).expandW());
        painel_interno.add(new Gap(50) , pos_painel_interno.nextRow());
        
        painel.add(painel_interno);
        
        Vector<Vector<String>> formularios = banco.obter_dados_da_tabela(SQL.nome_tabela_formulario);
        
        model.addElement("Selecione uma tabela");
        for (int i = 0; i < formularios.size(); i++) {
            Vector<String> form = formularios.get(i);
            model.addElement(form.get(1).replaceAll("_", " "));
        }
        
        select.setModel(model);
        
        
        /*\/ funcionalidades para exportar; \/*/
        btn_selecionar_local.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if (produrar_pasta.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    pasta_selecionada = produrar_pasta.getSelectedFile().toString() + File.separator;
                    caminho.setText(pasta_selecionada);
                    aplicar.setEnabled(true);
                }
                
            }
        });
        
        
        aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int ind = select.getSelectedIndex();                
                if( ind > 0 )
                {
                    System.out.println("selecionado: " + ind );
                    try{
                            if( pasta_selecionada != null )
                            {
                                new criar_planilha_excel(pasta_selecionada, (String) select.getSelectedItem());
                                aplicar.setEnabled(false);
                                caminho.setText(null);
                                select.setSelectedIndex(0);
                            }
                    }catch(Exception e)
                    {
                        //TODO exception
                    }
                    
                }else{
                    aviso.mensagem_atencao("Selecione uma tabela.");
                }
            }
        });
        
        
        return painel;
        
    }
    
}

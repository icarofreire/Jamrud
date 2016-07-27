/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.SQL;
import banco.banco;
import elementos.popup;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author icaro
 */
public class popup_lista_imagens extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JTextArea campo_exibir_texto = new JTextArea();
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    private JButton botao_excluir_imagem = new JButton("Excluir imagem", new ImageIcon("icones/deletar-24.png"));
    private int largura = 600;
    private int altura = 600;
    private String titulo_janela;
    
    //-- listagem
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel_lista = new JPanel(new GridBagLayout());
    private GBHelper pos_lista = new GBHelper();
    private JList lista;
    private Vector<HashMap<String, Object>> imagens;
    

    public popup_lista_imagens() {
    }
    
    public popup_lista_imagens(String nome_do_formulario) {
      
        JPanel painel = painel_p_historico(nome_do_formulario);
        gui_popup(nome_do_formulario, painel, largura, altura);
    }
    
    private void gui_popup(String nome_formulario, JComponent comp, int largura, int altura) {
      
        this.titulo_janela = "Imagens: " + nome_formulario;
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        painel.add( comp, pos.expandir());
        
        JPanel p_botoes = new JPanel();
        p_botoes.add(botao_fechar);
        p_botoes.add(botao_excluir_imagem);
        
//        painel.add(operacoes_painel.add_botao_em_painel(botao_fechar), pos.nextRow().expandW());
        painel.add(p_botoes, pos.nextRow().expandW());
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        botao_excluir_imagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if( !lista.isSelectionEmpty() )
                {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(
                    null, 
                    "Deseja realmente deletar '"+ lista.getSelectedValue() +"' ?",
                    "Deletar imagem", 
                    dialogButton);

                    if(dialogResult == 0)
                    {
                        for (int i = 0; i < imagens.size(); i++)
                        {
                            String nome_img = lista.getSelectedValue().toString();
                            String nome = (String) imagens.get(i).get("nome");
                            if( nome_img.equals(nome) ){
                                String ID = (String) imagens.get(i).get("id");
                                int ID__ = Integer.parseInt(ID);
                                if(
                                        banco.executar_query(SQL.montar_sql_deletar_linha(SQL.nome_tabela_imagens, ID__))
                                  ){
                                        aviso.mensagem_sucesso("Imagem excluída com sucesso!");
                                        lista.remove(lista.getSelectedIndex());
                                   }
//                                System.out.println("deletar: " + ID__ );
                            }
                        }
                        
                    }
                }
            }
        });
        
        super.setContentPane( operacoes_painel.painel_com_scroll_sem_borda(painel) );
        super.pack();
        super.setTitle(titulo_janela);
        super.setSize(new Dimension(largura, altura));
        super.setVisible(true);
        super.setLocationRelativeTo(null);
    }
    
    public JPanel painel_p_historico(String nome_do_formulario)
    {
        Vector<String> nomes = new Vector<String>();
        
        try {
            imagens = banco.obter_imagens_banco(nome_do_formulario);
            if( !imagens.isEmpty() ){
                for (int i = 0; i < imagens.size(); i++) {
                    BufferedImage get = (BufferedImage) imagens.get(i).get("imagem");
                    String nome = (String) imagens.get(i).get("nome");
                    nomes.add( nome );
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(popup_lista_imagens.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(popup_lista_imagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lista = new JList(nomes);
        JScrollPane scroll = new JScrollPane(lista);
        painel_lista.add(new JLabel("Clique duas vezes para exibir a imagem."));
        painel_lista.add(new Gap(GAP), pos_lista.nextRow());
        painel_lista.add(scroll, pos_lista.nextRow().expandir());
        
        lista.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) // <= evento de 2 clicks no mouse;
                {
                    int index = list.locationToIndex(evt.getPoint());
                    Object conteudo_obj = list.getSelectedValue();
                    if( conteudo_obj != null )
                    {
                        String conteudo = conteudo_obj.toString();
                        exibir_imagem exi = new exibir_imagem();

                        for (int i = 0; i < imagens.size(); i++) {
                            String nome = (String) imagens.get(i).get("nome");
                            if( nome.equals(conteudo_obj) )
                            {
                                BufferedImage image = (BufferedImage) imagens.get(i).get(conteudo_obj);
                                exi.popup_imagem(conteudo, image );
                            }
                        }
                    }
                }
            }
        });
        
        lista.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if( ke.getKeyCode() == KeyEvent.VK_ENTER )
                {
                    Object conteudo_obj = lista.getSelectedValue();
                    if( conteudo_obj != null ){
                        String conteudo = conteudo_obj.toString();
//                        new popup().exibir_texto("Registro de número: " + (list.getSelectedIndex()+1), conteudo);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        
        return painel_lista;
    }
    
}


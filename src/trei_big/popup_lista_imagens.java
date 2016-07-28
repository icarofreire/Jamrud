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
import java.awt.Graphics2D;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author icaro
 */
public class popup_lista_imagens extends JFrame {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JTextArea campo_exibir_texto = new JTextArea();
//    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    private JButton botao_excluir_imagem = new JButton("Excluir imagem", new ImageIcon("icones/deletar-24.png"));
    private int largura = 800;
    private int altura = 600;
    private String titulo_janela;
    
    //-- listagem
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel_lista = new JPanel(new GridBagLayout());
    private GBHelper pos_lista = new GBHelper();
    private JList lista;
    private Vector<HashMap<String, Object>> imagens;
    private JPanel p_in_painel_lista;
    private DefaultListModel nomes = new DefaultListModel();
    
    //-- lista ID
    private JList lista_id;
    private DefaultListModel ids = new DefaultListModel();
    private JLabel amostra_imagem = new JLabel();
    
    //-- redimencionar a imagem
    private static int n = 100;
    private static final int COMBINE_IMG_WIDTH = 180;
    private static final int COMBINE_IMG_HEIGHT = 90;
    
    //-- amostra de exemplo da imagem
    JPanel painel_amostra_imagem = new JPanel();
    private JButton botao_exibir_imagem = new JButton("Exibir em tamanho real", new ImageIcon("icones/expandir-24.png"));
    
    
    public popup_lista_imagens(String nome_do_formulario) {
      
        p_in_painel_lista = painel_p_listar_imagens(nome_do_formulario);
        gui_popup(nome_do_formulario, p_in_painel_lista, largura, altura);
    }
    
    private void gui_popup(String nome_formulario, JComponent comp, int largura, int altura) {
      
        this.titulo_janela = "Imagens: " + nome_formulario;
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        painel.add( comp, pos.expandir());
        
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
                                        nomes.remove( lista.getSelectedIndex() );
                                   }
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
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// <= efetua um "dispose()" quando clicado no "x" para fechar;
    }
    
    public JPanel painel_p_listar_imagens(String nome_do_formulario)
    {
        try {
            imagens = banco.obter_imagens_banco(nome_do_formulario);
            if( !imagens.isEmpty() ){
                for (int i = 0; i < imagens.size(); i++) {
                    BufferedImage get = (BufferedImage) imagens.get(i).get("imagem");
                    String nome = (String) imagens.get(i).get("nome");
                    String id = (String) imagens.get(i).get("id");
                    nomes.addElement( nome );
                    ids.addElement( "ID: " + id );
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(popup_lista_imagens.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(popup_lista_imagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lista = new JList(nomes);
        JScrollPane scroll = new JScrollPane(lista);
        
        lista_id = new JList(ids);
        JScrollPane scroll_id = new JScrollPane(lista_id);
        
//        painel_lista.add(new JLabel("Clique duas vezes no nome da imagem para exibir em tamanho real."), pos_lista.width(2));
//        painel_lista.add(new Gap(GAP), pos_lista.nextRow());
        
        painel_lista.add(scroll_id, pos_lista.nextRow().expandir());
        painel_lista.add(scroll, pos_lista.nextCol().expandir());
        
        painel_amostra_imagem.setLayout(new BoxLayout(painel_amostra_imagem, BoxLayout.Y_AXIS));
        painel_amostra_imagem.setBorder(BorderFactory.createTitledBorder(""));
        painel_amostra_imagem.add(Box.createRigidArea(new Dimension(10,10)));
        painel_amostra_imagem.add( amostra_imagem );
        painel_amostra_imagem.add(Box.createRigidArea(new Dimension(10,10)));
        painel_amostra_imagem.add( botao_exibir_imagem );
        painel_amostra_imagem.add(Box.createRigidArea(new Dimension(10,10)));
        painel_amostra_imagem.add( botao_excluir_imagem );
        painel_amostra_imagem.setVisible(false);
        painel_lista.add(painel_amostra_imagem, pos_lista.nextCol().expandir());
        
        //-- \/ Se o indice de uma lista for selecionado, o indice da outra lista também será selecionado; \/
        lista.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                painel_amostra_imagem.setVisible(true);
                int index = lista.getSelectedIndex();
                lista_id.setSelectedIndex(index);

                BufferedImage image = pegar_imagem_selecionada_lista(lista);
                if( image != null ){
                    image = resize_image(image);
                    amostra_imagem.setIcon(new ImageIcon(image));
                    painel_amostra_imagem.setBorder(BorderFactory.createTitledBorder( lista.getSelectedValue().toString() ));
                }
            }
        });
        
        lista_id.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                int index = lista_id.getSelectedIndex();
                lista.setSelectedIndex(index);
            }
        });
        //-- /\ Se o indice de uma lista for selecionado, o indice da outra lista também será selecionado; /\
        
        botao_exibir_imagem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                exibir_imagem exi = new exibir_imagem();
                BufferedImage image = pegar_imagem_selecionada_lista(lista);
                if( image != null ){
                    exi.popup_imagem(lista.getSelectedValue().toString(), image );
                }
            }
        });
        
        return painel_lista;
    }
    
    private BufferedImage resize_image(BufferedImage originalImage){
        BufferedImage resizedImage = new BufferedImage(COMBINE_IMG_WIDTH+n, COMBINE_IMG_HEIGHT+n, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, COMBINE_IMG_WIDTH+n, COMBINE_IMG_HEIGHT+n, null);
        g.dispose();
        return resizedImage;
    }
    
    private BufferedImage pegar_imagem_selecionada_lista(JList lista)
    {
        BufferedImage image = null;
        Object conteudo_obj = lista.getSelectedValue();
        if( conteudo_obj != null )
        {
            String conteudo = conteudo_obj.toString();
            exibir_imagem exi = new exibir_imagem();

            for (int i = 0; i < imagens.size(); i++) {
                String nome = (String) imagens.get(i).get("nome");
                if( nome.equals(conteudo_obj) )
                {
                    image = (BufferedImage) imagens.get(i).get(conteudo_obj);
                }
            }
        }
        return image;
    }
    
}


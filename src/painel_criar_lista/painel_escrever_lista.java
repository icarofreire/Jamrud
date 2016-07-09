/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painel_criar_lista;

import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import trei_big.aviso;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class painel_escrever_lista extends JDialog {
    
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    private JButton botao_aplicar = new JButton("Aplicar", new ImageIcon("icones/2-aplicar-24.png"));
    private JButton botao_fechar = new JButton("Fechar", new ImageIcon("icones/erro-24.png"));
    public boolean se_botao_fechar_clicado = false;
    private String titulo_janela = "Criar Lista";
    
    private JPanel painel_direito = new JPanel(new GridBagLayout());
    private GBHelper pos_painel_direito = new GBHelper();
    
    private JTextField campo = new JTextField(20);
    private JButton btn_add = new JButton("Adicionar", new ImageIcon("icones/add-24.png"));
    private JButton btn_del = new JButton("Remover", new ImageIcon("icones/deletar-24.png"));
    private JButton btn_del_tudo = new JButton("Remover Tudo", new ImageIcon("icones/deletar-24.png"));
    
    private JPanel painel_esquerdo = new JPanel(new GridBagLayout());
    private GBHelper pos_painel_esquerdo= new GBHelper();
    
    private JPanel painel_final = new JPanel(new GridBagLayout());
    private GBHelper pos_painel_final= new GBHelper();
    
    private DefaultListModel model = new DefaultListModel();
    private JList lista = new JList(model);
    private JScrollPane scroll = new JScrollPane(lista);
    
    public static Vector<String> itens = new Vector<String>();
    
    public painel_escrever_lista() {
        gui();
    }
    
    public painel_escrever_lista(Vector<String> itens) {
        this.itens = itens;
        gui();
    }
    
    /*\/ construtor criado para editar uma lista; inserir a lista como parametro. */
    public painel_escrever_lista(String[] itens_da_lista) {
        
        for (int i = 0; i < itens_da_lista.length; i++) {
            String itens_da_lista1 = itens_da_lista[i];
            model.addElement(itens_da_lista1);
        }
        gui();
    }

    
    private void alterar_titulo_lista()
    {
        scroll.setBorder(BorderFactory.createTitledBorder(model.size() + " Itens"));
    }
    
    private void procedimento_para_add_na_lista()
    {
        String dado = campo.getText().trim();
        if( !dado.isEmpty() ){
            model.addElement(dado);
            campo.setText(null);
            alterar_titulo_lista();
            se_ativar_ou_nao_botao_remover_tudo();
        }
    }
    
    private void se_ativar_ou_nao_botao_remover_tudo()
    {
        if( model.size() > 0 ){
            btn_del_tudo.setEnabled(true);
        }else{
            btn_del_tudo.setEnabled(false);
        }
    }
    
    public void gui() {
      
        alterar_titulo_lista();
        btn_del.setEnabled(false);
        se_ativar_ou_nao_botao_remover_tudo();
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        
        painel_direito.add(campo, pos_painel_direito.expandW());
        painel_direito.add(new Gap(20), pos_painel_direito.nextRow());
        painel_direito.add(btn_add, pos_painel_direito.nextRow().expandW());
        painel_direito.add(btn_del, pos_painel_direito.nextRow().expandW());
        painel_direito.add(btn_del_tudo, pos_painel_direito.nextRow().expandW());
        painel.add(painel_direito, pos.expandir());
        painel.add(new Gap(10), pos.nextCol());
        
        
        int largura_lista = 300;
        int altura_lista = 130;
        scroll.setPreferredSize(new Dimension(largura_lista, altura_lista));
        scroll.setViewportBorder(null);// <= remover a borda quadrada do scroll;
        painel_esquerdo.add(scroll, pos_painel_esquerdo.expandir());
        painel.add(painel_esquerdo, pos.nextCol().expandir());
        
        
        painel_final.add(new Gap(20), pos_painel_final.expandW());
        painel_final.add( botao_aplicar, pos_painel_final.nextRow().expandW());
        painel_final.add( botao_fechar, pos_painel_final.nextCol().expandW());        
        painel.add(painel_final, pos.nextRow().width(3).expandW());
        
        
        
        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                btn_del.setEnabled(true);
                int index = lista.getSelectedIndex();
            }
        });
        
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                procedimento_para_add_na_lista();
            }
        });
        
        campo.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if( ke.getKeyCode() == KeyEvent.VK_ENTER ){
                    procedimento_para_add_na_lista();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
         
        btn_del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = lista.getSelectedIndex();
                model.remove(index);
                btn_del.setEnabled(false);
                alterar_titulo_lista();
                se_ativar_ou_nao_botao_remover_tudo();
            }
        });
        
        btn_del_tudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if( model.size() > 0 )
                {
                    model.removeAllElements();
                    btn_del.setEnabled(false);
                    btn_del_tudo.setEnabled(false);
                    alterar_titulo_lista();
                }
            }
        });
        
        
        // ***
        

        botao_aplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if( model.size() > 0 )
                {
                    atualizar_itens();
                    dispose();
                }else{
                    aviso.mensagem_atencao("Adicione itens na sua lista.", "Lista vazia");
                }
            }
        });
        
        botao_fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                se_botao_fechar_clicado = true;
                dispose();
            }
        });
        
        super.setContentPane(painel);
        super.pack();
        super.setTitle(titulo_janela);
        super.setVisible(true);
        super.setLocationRelativeTo(null);
        
    }
    
    private void atualizar_itens()
    {
        itens.clear();
        for (int i = 0; i < model.size(); i++) {
            String item = (String) model.getElementAt(i);
            itens.add(item);
        }
    }
    
    private void exibir_itens_lista()
    {
        for (int i = 0; i < itens.size(); i++) {
            System.out.println( itens.get(i) );
        }
    }
    
    public String[] getItens() {
        return itens.toArray(new String[]{});
    }
    
}

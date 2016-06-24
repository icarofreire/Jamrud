/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.SQL;
import banco.banco;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import elementos.prefixos;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import tabela_listagem.exibir_listagem;

/**
 *
 * @author icaro
 */
public class operacoes_painel {
    
    private static final int GAP = 5;   // Default gap btwn components.
    
    public static void atualizar_painel(JPanel painel) {
        painel.revalidate();
        painel.repaint();
    }
    
    public static JScrollPane painel_com_scroll_sem_borda(JPanel painel) {
        JScrollPane scroll = new JScrollPane(painel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;
        return scroll;
    }
    
    public static JPanel remover_componentes_painel(JPanel painel){
        if(painel.getComponents().length >= 1){
            painel.removeAll();
            operacoes_painel.atualizar_painel(painel);
        }
        return painel;
    }
    
    /* \/ Verifica se existe um componente X no painel; 
       ATENÇÃO: A verificação é realizada atraves do nome do componente, que é definido pelo metodo 'setName'.
    Exemplo:
        JPanel painel = new JPanel();
        painel.setName("painel_interno"); <= Este metodo define o nome do componente(Similar a um 'ID').
    através deste nome, será realizada a busca no painel para ver se ele existe;
    \/ \/ \/ */
    public static boolean se_componente_em_painel(JPanel painel, String name_componente_no_painel) {
            boolean f = false;
            Component[] components = painel.getComponents();
            for (int i=0; i < components.length; i++) {
                   String name_componente = components[i].getName();
                   if( (name_componente != null) && name_componente.equalsIgnoreCase(name_componente_no_painel) ){
                       f = true; break;
                   }
            }
            return f;
    }
    
    public static JPanel remover_componente_em_painel(JPanel painel, String name_componente_no_painel) {
            Component[] components = painel.getComponents();
            for (int i=0; i < components.length; i++) {
                   String name_componente = components[i].getName();
                   if( (name_componente != null) && name_componente.equalsIgnoreCase(name_componente_no_painel) ){
                       painel.remove( components[i] );
                       break;
                   }
            }
            return painel;
    }
    
    public static Component pegar_componente_em_painel(JPanel painel, String name_componente_no_painel) {
        Component x = null;
        Component[] components = painel.getComponents();
            for (int i=0; i < components.length; i++) {
                String name_componente = components[i].getName();
                if( (name_componente != null) && (name_componente.indexOf(name_componente_no_painel) != -1)  ){
                    x = components[i]; break;
                }
            }//fim for;
        return x;
    }
    
    public static void exibir_names_em_painel(JPanel painel) {
        System.out.println("----------Names----------");
        Component[] components = painel.getComponents();
            for (int i=0; i < components.length; i++) {
                String name_componente = components[i].getName();
                System.out.println("name: " + name_componente);
            }//fim for;
        System.out.println("-------------------------");
    }
    
    public static Vector<Component> pegar_todos_componentes_em_painel_com_prefixo(JPanel painel, String prefixo_componente_no_painel) {
        Vector<Component> vc = new Vector<Component>();
        Component[] components = painel.getComponents();
            for (int i=0; i < components.length; i++) {
                String name_componente = components[i].getName();
                if( (name_componente != null) && (name_componente.indexOf(prefixo_componente_no_painel) != -1)  ){
                    vc.add(components[i]);
                }
            }//fim for;
        return vc;
    }
    
    public static int obj_to_int(Object inteiro) {
        int valor = Integer.parseInt(inteiro.toString());
        return valor;
    }
    
    public static JPanel add_painel_filho_ao_PAI(JPanel painel_principal, JPanel painel_filho, String ID_painel_filho, GBHelper pos){
        operacoes_painel.remover_componentes_painel(painel_principal);
        JScrollPane scroll_painel_backup = operacoes_painel.painel_com_scroll_sem_borda(painel_filho);
        scroll_painel_backup.setName(ID_painel_filho);
                        
        if( !operacoes_painel.se_componente_em_painel(painel_principal, ID_painel_filho) ){
            painel_principal.add(scroll_painel_backup, pos.expandir());
        }
        return painel_principal;
    }
    
    
    public static String serializar_obj(Object myObject) {
        String serializedObject = "";
         // serialize the object
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(myObject);
            so.flush();
            serializedObject = new String(Base64.encode(bo.toByteArray()));//bo.toString();
        } catch (Exception e) {
            System.out.println("->" + e);
        }
        return serializedObject;
    }
    
    public static Object deserializar_obj(String serializedObject) {
        Object myObject = null;
        // deserialize the object
         try {
             byte b[] = Base64.decode(serializedObject);
             ByteArrayInputStream bi = new ByteArrayInputStream(b);
             ObjectInputStream si = new ObjectInputStream(bi);
             myObject = si.readObject();
         } catch (Exception e) {
             System.out.println("x->" + e);
         }
         return myObject;
    }
    
    public static JPanel add_componente_em_painel(JComponent comp) {
        
        JPanel p_group = new JPanel(new GridBagLayout());
        GBHelper pos_p_group = new GBHelper();
        
        p_group.setName( operacoes.gerar_name_para_componente(prefixos.prefixo_painel_interno) );
        p_group.add(comp, pos_p_group.expandir());
        
        return p_group;
    }
    
    /*\/ Com um botão adicionado em um painel, quando este painel for adicionado a outro painel
    que usa os metodos da classe GBHelper, o botão não expandirá para um tamanho a preencher a celula que
    ele está adicionado pela classe GridBagConstraints. O que expandirá será o painel do botão, 
    mas o botão manterá seu tamanho default. \/ */
    public static JPanel add_botao_em_painel(JButton botao) {
        
        JPanel painel_botao = new JPanel();
        painel_botao.add(botao);
        
        painel_botao.setName( operacoes.gerar_name_para_componente(prefixos.prefixo_painel_interno_para_botao) );
        
        return painel_botao;
    }
    
    /*  \/\/
        vertical_ou_horizontal = 1 --> vertical;
        vertical_ou_horizontal = 2 --> horizontal; \/\/
    */
    public static JPanel add_componente_em_painel(JComponent comp1, JComponent comp2, int vertical_ou_horizontal) {
        
        JPanel p_group = new JPanel(new GridBagLayout());
        GBHelper pos_p_group = new GBHelper();
        
        p_group.setName( operacoes.gerar_name_para_componente(prefixos.prefixo_painel_interno) );
        p_group.add(comp1, pos_p_group.expandir());
        
        if( vertical_ou_horizontal == 1 ){
            p_group.add(comp2, pos_p_group.nextRow().expandir());
        }else if( vertical_ou_horizontal == 2 ){
            p_group.add(comp2, pos_p_group.nextCol().expandir());
        }
        
        return p_group;
    }
    
    public static void add_botao_excluir_painel(final JPanel painel_baixo, final GBHelper pos_painel_baixo, final JPanel painel_criado, final JButton btn_excluir_painel, final String prefixo_painel, final String prefixo_btn_excluir_painel)
    {
        final String name_painel = operacoes.gerar_name_para_componente(prefixo_painel);
        final String name_btn_excluir_painel = operacoes.gerar_name_para_componente(prefixo_btn_excluir_painel);
                
        painel_criado.setName(name_painel);
//        btn_excluir_painel.setName(name_btn_excluir_painel);
        
        /*-- cria um painel para o botão excluir, para o botão ficar com o tamanho default;
        e adiciona este painel do botão ao painel externo; Cria um name para ele, e este mesmo name será 
        usado para exclui-lo caso ele seja clicado. */
        JPanel painel_botao = new JPanel();
        painel_botao.add(btn_excluir_painel);
        final String name_painel_botao = operacoes.gerar_name_para_componente(prefixos.prefixo_painel_interno_para_botao);
        painel_botao.setName(name_painel_botao);
        painel_baixo.add( painel_botao, pos_painel_baixo.nextCol().expandW() );
        painel_baixo.add(new Gap(GAP) , pos_painel_baixo.nextRow());
        /*--*/
                
        btn_excluir_painel.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
                            
                String chave_painel = operacoes.pegar_chave_em_string(name_painel);      
                String chave_btn = operacoes.pegar_chave_em_string(name_painel_botao);
                        
                painel_baixo.remove( operacoes_painel.pegar_componente_em_painel(painel_baixo, operacoes.pegar_name(prefixo_painel, chave_painel)) );
                painel_baixo.remove( operacoes_painel.pegar_componente_em_painel(painel_baixo, operacoes.pegar_name(prefixos.prefixo_painel_interno_para_botao, chave_btn)) );

                operacoes_painel.atualizar_painel(painel_baixo);
            }
        });
    }
    
    /* Adiciona o componente em um painel com borda vermelha, e um botão de excluir com prefixo já definido.
    */
    public static void add_componente_painel_baixo_e_add_botao_exluir(final JComponent componente, final JPanel painel_baixo, final GBHelper pos_painel_baixo, final String prefixo_painel)
    {
        JButton btn_excluir_painel = new JButton("Excluir", new ImageIcon("icones/erro-24.png"));
                    
        JPanel painel_criar_titulo = operacoes_painel.add_componente_em_painel( componente );
        painel_criar_titulo.setBorder(BorderFactory.createLineBorder(Color.RED));
                    
        painel_baixo.add( painel_criar_titulo, pos_painel_baixo.nextRow().expandW() );
        operacoes_painel.add_botao_excluir_painel(painel_baixo, pos_painel_baixo, painel_criar_titulo, btn_excluir_painel, prefixo_painel, prefixos.prefixo_btn_excluir_painel);
        operacoes_painel.atualizar_painel(painel_baixo);
    }
    
    /* Adiciona o componente em um painel com borda vermelha, e um botão de excluir com prefixo já definido.
    */
    public static void add_componente_painel_baixo_e_add_botao_exluir(final JComponent componente, final JPanel painel_baixo, final GBHelper pos_painel_baixo, final String prefixo_painel, final String titulo_para_componente)
    {
        JButton btn_excluir_painel = new JButton("Excluir", new ImageIcon("icones/erro-24.png"));
        
        JLabel label_titulo_componente = new JLabel(titulo_para_componente);
        label_titulo_componente.setName( operacoes.gerar_name_para_componente(prefixos.prefixo_titulos_dos_componentes) );
                
        JPanel painel_criar_titulo = operacoes_painel.add_componente_em_painel( label_titulo_componente, componente, 1 );
        
        painel_criar_titulo.setBorder(BorderFactory.createLineBorder(Color.RED));
        
        painel_baixo.add( painel_criar_titulo, pos_painel_baixo.nextRow().expandW() );
        operacoes_painel.add_botao_excluir_painel(painel_baixo, pos_painel_baixo, painel_criar_titulo, btn_excluir_painel, prefixo_painel, prefixos.prefixo_btn_excluir_painel);
        operacoes_painel.atualizar_painel(painel_baixo);
    }
    
    /* insere o nome da tabela, e retorna o painel da tabela de listagem, com todos os dados do banco
    inseridos na tabela do painel;
    */
    public static JPanel obter_dados_banco_em_painel_listagem(final String nome_da_tabela){
        
        ArrayList<Object[]> dados_da_tabela = new ArrayList<Object[]>();
        Vector<Vector<String>> linhas = banco.obter_dados_da_tabela(nome_da_tabela);
        
        String[] colunas = banco.nome_colunas_consulta.toArray(new String[]{});
        banco.nome_colunas_consulta.clear();
        
        for (Vector<String> linha : linhas) {
            dados_da_tabela.add( linha.toArray(new Object[]{}) );
        }       
                
       return new exibir_listagem().obj("", colunas, dados_da_tabela);
    }
        
}

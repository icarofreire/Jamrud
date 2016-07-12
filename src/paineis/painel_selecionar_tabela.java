/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import banco.SQL;
import banco.banco;
import elementos.popup;
import elementos.prefixos;
import ferramenta_gui.GBHelper;
import ferramenta_gui.Gap;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import menu_modulos.obter_dados_formulario;
import trei_big.aviso;
import trei_big.debugar;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class painel_selecionar_tabela {
     
    private static final int GAP = 5;   // Default gap btwn components.
    private JPanel painel = new JPanel(new GridBagLayout());
    private GBHelper pos = new GBHelper();
    
    private JPanel painel_interno = new JPanel(new GridBagLayout());
    private GBHelper pos_painel_interno = new GBHelper();
    
    private JLabel lcmp1 = new JLabel("Escolha a tabela que deseja utilizar:");
    private JComboBox select = new JComboBox();
//    private JButton exibir = new JButton("Exibir", new ImageIcon("icones/exibir-popup-32.png"));
//    private JButton exibir_abaixo = new JButton("Exibir abaixo", new ImageIcon("icones/aplicar-32.png"));
    private JButton expandir = new JButton("Expandir tabela", new ImageIcon("icones/expandir-32.png"));
    
    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private HashMap<String, String> nome_hash_form = new HashMap<String, String>();
    
    private JComponent componente_esconder = null;
    private JPanel painel_externo_esq = null;

    public void pegar_componente_para_esconder(JComponent componente_esconder, JPanel painel_externo_esq) {
        this.componente_esconder = componente_esconder;
        this.painel_externo_esq = painel_externo_esq;
    }
    
    public JPanel painel_selecionar_tabela_forms(){
        
        painel_interno.setName("painel_interno_para_selecionar_formularios");
        painel_interno.add(lcmp1, pos_painel_interno.expandW());
        painel_interno.add(select, pos_painel_interno.nextRow().expandW());
        painel_interno.add(new Gap(GAP) , pos_painel_interno.nextCol());  // Add a gap below
        painel_interno.add(expandir, pos_painel_interno.nextCol().expandW());
        painel_interno.add(new Gap(GAP) , pos_painel_interno.nextRow());  // Add a gap below
        painel_interno.add(new JSeparator(SwingConstants.HORIZONTAL) , pos_painel_interno.nextRow().width(4).expandW());
        painel_interno.add(new Gap(10) , pos_painel_interno.nextRow());
        
        painel.add(painel_interno);
        
        JPanel painel_vazio_preenchimento = new JPanel();
        painel.add( painel_vazio_preenchimento, pos.nextRow().expandir());
        
        Vector<Vector<String>> formularios = banco.obter_dados_da_tabela(SQL.nome_tabela_formulario);
        
        model.addElement("Selecione um formulário");
        for (int i = 0; i < formularios.size(); i++) {
            Vector<String> form = formularios.get(i);
            nome_hash_form.put( form.get(1) , form.get(2) );// nome do formulario => hash do formulario;
            model.addElement(form.get(1).replaceAll("_", " "));
        }
        
        select.setModel(model);
        
        select.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if( select.getSelectedIndex() > 0 )
                {                    
                    String item = select.getSelectedItem().toString();
                    String form = select.getSelectedItem().toString().replaceAll(" ", "_");
                    if( nome_hash_form.containsKey(form) )
                    {
                        operacoes_painel.remover_componente_em_painel(painel, "tabela_de_dados_do_formulario");
                        JPanel pl = operacoes_painel.obter_dados_banco_em_painel_listagem(form);
                        pl.setName("tabela_de_dados_do_formulario");
                        pl.setBorder(BorderFactory.createTitledBorder(form.replaceAll("_", " ")));
                        painel.add(pl , pos.nextRow().expandir());
                        operacoes_painel.atualizar_painel(painel);
                    }
                    painel.remove(painel_vazio_preenchimento);
                    operacoes_painel.atualizar_painel(painel);
                }
            }
        });        
        
        expandir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int ind = select.getSelectedIndex();                
                if( ind > 0 )
                {
                    if( componente_esconder != null )
                    {
                        if(componente_esconder.isVisible()){
                            componente_esconder.setVisible(false);
                            expandir.setText("Exibir menu");
                            expandir.setIcon(new ImageIcon("icones/exibir-popup-32.png"));
                        }else{
                            componente_esconder.setVisible(true);
                            expandir.setText("Expandir tabela");
                            expandir.setIcon(new ImageIcon("icones/expandir-32.png"));
                        }
                        operacoes_painel.atualizar_painel(painel_externo_esq);
                        operacoes_painel.atualizar_painel(painel);
                    }
                }else{
                    aviso.mensagem_atencao("Selecione um formulário.");
                }
            }
        });
        
        return painel;
        
    }
    
    
    private void eventos_botao_enviar_e_data_hora(String nome_do_formulario, final JPanel painel_formulario)
    {
                        final String nome_tabela_cadastrar = nome_do_formulario;
                        JPanel p_botao = (JPanel) operacoes_painel.pegar_componente_em_painel(painel_formulario, prefixos.prefixo_painel_interno_para_botao);
                        JButton btn_enviar = (JButton) operacoes_painel.pegar_componente_em_painel(p_botao, prefixos.prefixo_botao_enviar);
                        
                        final obter_dados_formulario dados_form = new obter_dados_formulario();
                        
                        // \/\/ >> aqui o evento do click no botao ENVIAR em cada formulário; << \/\/
                        if( btn_enviar != null )
                        {
                            btn_enviar.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ae) {
//                                    System.out.println("ENVIAR");
                                    
                                    dados_form.apagar_dados();
                                    dados_form.zerar_numero_painel_radios();
                                    dados_form.quantos_paineis_radios(painel_formulario);
                                    dados_form.buscar_componentes_recursivo(painel_formulario);
                                    
                                    if( dados_form.verifica_se_radios_selecionados() )
                                    {
                                        String[] titulos = dados_form.getTitulos().toArray(new String[]{});
                                        String[] dados = dados_form.getDados().toArray(new String[]{});
                                        String sql_inserir = SQL.montar_sql_insert(nome_tabela_cadastrar, titulos, dados);
                                        if( banco.executar_query(sql_inserir) ){
                                            aviso.mensagem_sucesso("Informações inseridas com sucesso!");
                                        }
                                    }
                                    else{
                                        aviso.mensagem_atencao("Campo de opções vazio", "Selecione uma opção");
                                    }
                                    
                                }
                            });
                        }
                        // /\/\ >> aqui o evento do click no botao ENVIAR em cada formulário; << /\/\
                        

                        // \/\/ >> aqui os eventos dos campos de data e hora; << \/\/
                        Vector<Component> paineis_campo_hora = operacoes_painel.pegar_todos_componentes_em_painel_com_prefixo(painel_formulario, prefixos.prefixo_painel_criar_campo_hora);
                        if( paineis_campo_hora != null )
                        {
                            for (int j = 0; j < paineis_campo_hora.size(); j++) {
                                JPanel p_hora = (JPanel) paineis_campo_hora.get(j);
                                JPanel p_int = (JPanel) operacoes_painel.pegar_componente_em_painel(p_hora, prefixos.prefixo_painel_interno);
                                if( p_int != null )
                                {
                                    final JTextField hora = (JTextField) operacoes_painel.pegar_componente_em_painel(p_int, prefixos.prefixo_campo_hora);

                                    hora.addFocusListener(new FocusListener(){
                                        @Override
                                        public void focusGained(FocusEvent fe) {
                                            hora.setCaretPosition(0);
                                        }

                                        @Override
                                        public void focusLost(FocusEvent fe) {}
                                    });
                                }
                            }
                        }
                        
                        //***
                        
                        Vector<Component> paineis_campo_data = operacoes_painel.pegar_todos_componentes_em_painel_com_prefixo(painel_formulario, prefixos.prefixo_painel_criar_campo_data);
                        if( paineis_campo_data != null )
                        {
                            for (int j = 0; j < paineis_campo_data.size(); j++) {
                                JPanel p_data = (JPanel) paineis_campo_data.get(j);
                                JPanel p_int = (JPanel) operacoes_painel.pegar_componente_em_painel(p_data, prefixos.prefixo_painel_interno);
                                if( p_int != null )
                                {
                                    final JTextField data = (JTextField) operacoes_painel.pegar_componente_em_painel(p_int, prefixos.prefixo_campo_data);

                                    data.addFocusListener(new FocusListener(){
                                        @Override
                                        public void focusGained(FocusEvent fe) {
                                            data.setCaretPosition(0);
                                        }

                                        @Override
                                        public void focusLost(FocusEvent fe) {}
                                    });
                                }
                            }
                        }
                        // /\/\ >> aqui os eventos dos campos de data e hora; << /\/\
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_modulos;

import java.awt.Component;
import java.awt.Container;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import trei_big.debugar;
import trei_big.operacoes;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class obter_dados_formulario {
    
    private Vector<String> titulos;
    private Vector<String> dados;
    private Vector<String> dados_checkbox;
    public Vector<Vector<String>> v_dados_checkbox = new Vector<Vector<String>>();
    private String titulo_para_campo_vazio = "Campo vazio";
    public static String token_separador_dados_checkbox = "|::|";
    public static String regex_token_separador_dados_checkbox = "\\|\\:\\:\\|";
    private int n_painel_radios = 0;
    public static String sufixo_selecionado_campo_radio = "__opc_radio_selecionado";

    public obter_dados_formulario() {
        titulos = new Vector<String>();
        dados = new Vector<String>();
        dados_checkbox = new Vector<String>();
    }

    public Vector<String> getTitulos() {
        return titulos;
    }

    public void zerar_numero_painel_radios() {
        this.n_painel_radios = 0;
    }
        
    
    private int n_radios_selecionados_em_dados()
    {
        int n_radio_selecionado = 0;
        for (int i = 0; i < dados.size(); i++) {
            String get = dados.get(i);
            if( get.indexOf(sufixo_selecionado_campo_radio) != -1 ){//<< se alguma opção foi selecionada;
                dados.setElementAt(get.substring(0, get.indexOf(sufixo_selecionado_campo_radio)), i);
                n_radio_selecionado++;
            }
        }
        return n_radio_selecionado;
    }
    
    public boolean verifica_se_radios_selecionados()
    {
        return ( n_painel_radios == n_radios_selecionados_em_dados() );
    }

    public Vector<String> getDados() {
        
        dados = operacoes.remover_visinhos_duplicados(dados);
        if( v_dados_checkbox.size() > 0 ){
            for (int i = 0; i < v_dados_checkbox.size(); i++) {
                Vector<String> get = v_dados_checkbox.get(i);
                if( get.size() > 0 ){
                    String ser = operacoes.serializar_string(get.toArray(new String[]{}));
                    dados_checkbox.add(ser);
                }
            }
        }
        operacoes.modificar_dadosV1_por_dadosV2_por_token("JCheckBox", dados, dados_checkbox);
        
        for (int i = 0; i < dados.size(); i++) {
            String get = dados.get(i);
            if(get.equals(":")){
                dados.setElementAt(titulo_para_campo_vazio, i);                
            }
            if(get.equals("//")){
                dados.setElementAt(titulo_para_campo_vazio, i);                
            }
        }
        
        return dados;
    }
    
    public void apagar_dados() {
        titulos.clear();
        dados.clear();
        dados_checkbox.clear();
    }
        
    public void buscar_componentes_recursivo(Component component) {
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents())
            {
                buscar_componentes_recursivo(child);
                /* \/ Pega o nome da classe do componente. Ex: JLabel, JTextField, JTextArea, JViewport; \/ */
                String nome_classe = child.getClass().getSimpleName();
                
                
//                System.out.println("nome_classe: " + nome_classe );
                if( nome_classe.equalsIgnoreCase("JLabel") )
                {
                    JLabel titulo = (JLabel) child;
                    if( titulo.getName() != null ){
                        titulos.add(titulo.getText());
                    }
                }
                
                if( nome_classe.equalsIgnoreCase("JTextField") )
                {
                    JTextField campo = (JTextField) child;
                    String dado_campo = campo.getText();
                    if(dado_campo.isEmpty()){
                        dados.add(titulo_para_campo_vazio);
                    }else{
                        dados.add(dado_campo);
                    }
                }
                
                if( nome_classe.equalsIgnoreCase("JFormattedTextField") )
                {
                    JTextField campo = (JFormattedTextField) child;
                    String dado_campo = campo.getText().replaceAll(" ", "");
                    dados.add(dado_campo);
                }
                
                if( nome_classe.equalsIgnoreCase("JTextArea") )
                {
                    JTextArea campo = (JTextArea) child;
                    String dado_campo = campo.getText();
                    if(dado_campo.isEmpty()){
                        dados.add(titulo_para_campo_vazio);
                    }else{
                        dados.add(dado_campo);
                    }
                }
                
                if( nome_classe.equalsIgnoreCase("JComboBox") )
                {
                    JComboBox campo = (JComboBox) child;
                    String dado_campo = (String) campo.getSelectedItem();
                    dados.add(dado_campo);
                }
                
                if( nome_classe.equalsIgnoreCase("JRadioButton") )// << RADIO
                {
                    JRadioButton campo = (JRadioButton) child;
                    if( campo.isSelected() ){
                        String dado_campo = (String) campo.getText();
                        dados.add(dado_campo + sufixo_selecionado_campo_radio);
                    }
                }
                
                if( nome_classe.equalsIgnoreCase("JCheckBox") )
                {
                    dados.add("JCheckBox");
                }

            }
        }
    }// fim buscar_componentes_recursivo;
    
    public void quantos_paineis_radios(Component component) {
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents())
            {
                quantos_paineis_radios(child);
                /* \/ Pega o nome da classe do componente. Ex: JLabel, JTextField, JTextArea, JViewport; \/ */
//                String nome_classe = child.getClass().getSimpleName();
                String name_componente = child.getName();
                
                if( (name_componente != null) && name_componente.equalsIgnoreCase("painel_radios") )
                {
                    n_painel_radios++;
                }

            }
        }
    }// fim;
    
    public void pegar_chekbox_selecionados(JPanel painel_formulario) {
        
        Vector<Component> paineis_opcoes = operacoes_painel.pegar_todos_componentes_em_painel_com_prefixo(painel_formulario, "painel_opcoes_");
                                    
        for (int i = 0; i < paineis_opcoes.size(); i++) 
        {
            Component get = paineis_opcoes.get(i);
            JPanel p_cheks = (JPanel) get;
            Vector<Component> cheks = operacoes_painel.pegar_todos_componentes_em_painel_com_prefixo(p_cheks, "painel_checkbox");
            
            for (int j = 0; j < cheks.size(); j++) {
                Component chek = cheks.get(j);
                JPanel pp = (JPanel) chek;
                
                debugar.exibir_names_em_painel(pp);
                Vector<Component> comps = operacoes_painel.pegar_todos_componentes_em_painel(pp);
                
                Vector<String> v = new Vector<String>();
                for (int k = 0; k < comps.size(); k++) {
                    Component get1 = comps.get(k);
                    JCheckBox campo = (JCheckBox) get1;
                    if( campo.isSelected() ){
                        String dado_campo = (String) campo.getText();
                        v.add(dado_campo);
                    }
                }
                v_dados_checkbox.add(v);
            }
        } 
    }
  
}

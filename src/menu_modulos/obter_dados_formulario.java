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
import trei_big.operacoes;

/**
 *
 * @author icaro
 */
public class obter_dados_formulario {
    
    private Vector<String> titulos;
    private Vector<String> dados;
    private Vector<String> dados_checkbox;
    private String titulo_para_campo_vazio = "Campo vazio";
    private String token_separador_dados_checkbox = "|::|";
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
        
        String dados_checkbos_serializados = serializar_dados_checkbox();
        dados.setElementAt(dados_checkbos_serializados, 
                dados.indexOf(token_separador_dados_checkbox)
        );
        
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
    
    public String serializar_dados_checkbox()
    {
        String ser = titulo_para_campo_vazio;
        if( !dados_checkbox.isEmpty() ){
            ser = operacoes.serializar_string( dados_checkbox.toArray(new String[]{}) );
        }
        return ser;
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
                    if(!dados.contains(token_separador_dados_checkbox)){
                        dados.add(token_separador_dados_checkbox);
                    }
                    JCheckBox campo = (JCheckBox) child;
                    if( campo.isSelected() ){
                        String dado_campo = (String) campo.getText();
                        dados_checkbox.add(dado_campo);
                        if( titulos.contains(dado_campo) ){
                            titulos.remove(dado_campo);
                        }
                    }
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
    
}

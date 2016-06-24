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
    private Vector<String> dados_radio;
    private String token_campo_vazio_radio = "Campo vazio radio";
    public static String sufixo_detect_campo_radio = "__opc_radio_ok";

    public obter_dados_formulario() {
        titulos = new Vector<String>();
        dados = new Vector<String>();
        dados_checkbox = new Vector<String>();
        dados_radio = new Vector<String>();
    }

    public Vector<String> getTitulos() {
        return titulos;
    }

    public Vector<String> getDados() {
        String dados_checkbos_serializados = serializar_dados_checkbox();
        dados.add(dados_checkbos_serializados);
        
//        for (int i = 0; i < dados.size(); i++) {
//            String get = dados.get(i);
//            if( get.equalsIgnoreCase(token_campo_vazio_radio) ){
//                dados.remove(token_campo_vazio_radio);
//            }
//        }
        
        return dados;
    }
    
    public void apagar_dados() {
        titulos.clear();
        dados.clear();
        dados_checkbox.clear();
    }
    
    public String serializar_dados_checkbox()
    {
        String ser = "Campo vazio check";
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
//                        System.out.println("nome PAI: " + child.getParent().getClass().getSimpleName() );
                    }
                }
                
                if( nome_classe.equalsIgnoreCase("JTextField") )
                {
                    JTextField campo = (JTextField) child;
                    String dado_campo = campo.getText();
                    if(dado_campo.isEmpty()){
                        dados.add("Campo vazio");
                    }else{
                        dados.add(dado_campo);
                    }
                }
                if( nome_classe.equalsIgnoreCase("JFormattedTextField") )
                {
                    JTextField campo = (JFormattedTextField) child;
                    String dado_campo = campo.getText();
                    dados.add(dado_campo);
//                    System.out.println( nome_classe + " : " + dado_campo );
                }
                if( nome_classe.equalsIgnoreCase("JTextArea") )
                {
                    JTextArea campo = (JTextArea) child;
                    String dado_campo = campo.getText();
                    if(dado_campo.isEmpty()){
                        dados.add("Campo vazio txt");
                    }else{
                        dados.add(dado_campo);
                    }
//                    System.out.println( nome_classe + " : " + dado_campo );
                }
                if( nome_classe.equalsIgnoreCase("JComboBox") )
                {
                    JComboBox campo = (JComboBox) child;
                    String dado_campo = (String) campo.getSelectedItem();
                    dados_radio.add(dado_campo);
//                    System.out.println( nome_classe + " : " + dado_campo );
                }
                if( nome_classe.equalsIgnoreCase("JRadioButton") )// << RADIO
                {
                    JRadioButton campo = (JRadioButton) child;
                    if( campo.isSelected() ){
                        String dado_campo = (String) campo.getText();
                        dados.add(dado_campo+sufixo_detect_campo_radio);
    //                        System.out.println( nome_classe + " : " + dado_campo );
                    }
                    
                }
                if( nome_classe.equalsIgnoreCase("JCheckBox") )
                {
                    JCheckBox campo = (JCheckBox) child;
                    if( campo.isSelected() ){
                        String dado_campo = (String) campo.getText();
                        dados_checkbox.add(dado_campo);
                        if( titulos.contains(dado_campo) ){
                            titulos.remove(dado_campo);
                        }
//                        System.out.println( nome_classe + " : " + dado_campo );
                    }
                }

            }
        }
    }// fim buscar_componentes_recursivo;
    
}

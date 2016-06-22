/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu_modulos;

import banco.*;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author icaro
 */
public class MenuLateral {
    
    /* \/\/ Titulo para os modulos no menu; \/\/ */
    public static String form_cadastrar = "Cadastrar";
    public static String pesquisar_editar = "Pesquisar/Editar";
    public static String backup_banco = "Backup";
    public static String temas = "Temas";
    public static String historico = "Historico";
    public static String criar_formulario = "Criar formulário";
    public static String gerar_planilha = "Gerar planilha";
    
    public Vector<Vector<String>> formularios = new Vector<Vector<String>>();
    
    private Map<String, ImageIcon> map = new HashMap<>();
    private Map<String, Integer> modulo = new HashMap<>();
    private Vector<String> nomes = new Vector<String>();
    private Vector<String> icones = new Vector<String>();
    
    public JList lista() {
        
        /* \/ A ordem em que forem adicionados será a ordem em que aparecerá no menu. \/ */
        nomes.add(form_cadastrar); /* => */ icones.add("icones/cadastrar.png");
        
        /* \/ Criar links no menu para os formulários cadastrados no banco; \/ */
        formularios = banco.obter_dados_da_tabela(SQL.nome_tabela_formulario);
        if( (formularios != null) && (!formularios.isEmpty()) )
        {
            Vector<String> nomes_p_tabelas = new Vector<String>();
            for(int i=0; i<formularios.size(); i++)
            {
                Vector<String> linha = formularios.get(i);
                if( !linha.lastElement().isEmpty() ){
                    String nome_formulario = linha.get(1);
                    
                    String token_procurar = "_";
                    String token_substituir = " ";
                    if( nome_formulario.indexOf(token_procurar) != -1 ){
                        nome_formulario = nome_formulario.replaceAll(token_procurar, token_substituir);
                    }
                    
                    String hash = linha.get(2);
                    
                    /* \/ Primeiro insere no menu os itens de todos os formulários; 
                    Depois, através do vetor 'nomes_p_tabelas', será adicionado no menu todos os itens
                    para as tabelas de registros de cada formulário. */
                    nomes.add(nome_formulario); /* => */ icones.add("icones/cadastrar.png");
                    nomes_p_tabelas.add(nome_formulario);
                }
            }
            /* \/ O menu ficará organizado em: 
            primeiros os itens dos formulários, e depois os itens das tabelas dos formulários; */
            if(!nomes_p_tabelas.isEmpty()){
                for (int i = 0; i < nomes_p_tabelas.size(); i++) {
                    String get = nomes_p_tabelas.get(i);
                    nomes.add("Tabela:" + get); /* => */ icones.add("icones/pesquisar_e_editar.png");
                }
            }
        }
        
        
        nomes.add(pesquisar_editar); /* => */ icones.add("icones/pesquisar_e_editar.png");
        nomes.add(gerar_planilha); /* => */ icones.add("icones/planilha.png");
        //        nomes.add("Lixeira"); /* => */ icones.add("icones/lixo2.png");
//                nomes.add(backup_banco); /* => */ icones.add("icones/1460597575_document-save.png");
        nomes.add(criar_formulario); /* => */ icones.add("icones/criar.png");
        nomes.add(historico); /* => */ icones.add("icones/historico.png");
        nomes.add(temas); /* => */ icones.add("icones/aparencia1.png");

        
        //...
        try {
            for(int i = 0; i < nomes.size(); i++) {
                map.put( nomes.elementAt(i), new ImageIcon(icones.elementAt(i)));
                modulo.put( nomes.elementAt(i), i );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
                
        JList list = new JList(nomes);
        list.setCellRenderer(new MenuLateralRenderer());
        return list;
    }

    private class MenuLateralRenderer extends DefaultListCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            label.setIcon(map.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }
    
    public boolean se_chave(String chave, int indice) {
        if( modulo.containsKey(chave) && modulo.get(chave) == indice ) {
            return true;
        }else{
            return false;
        }
    }
    
    public void exibir_mapa_menu() {
        
        System.out.println("----------Mapa Menu----------");
        for(Entry<String, Integer> entry : modulo.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println( "[" + key + "] -> [" + value + "]" );
        }
        System.out.println("----------=========----------");
    }

    public Map<String, ImageIcon> getMap() {
        return map;
    }


}

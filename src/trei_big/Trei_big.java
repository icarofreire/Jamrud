/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.SQL;
import banco.banco;
import elementos.popup;
import elementos.prefixos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import menu_modulos.*;
import paineis.editar_formulario;
import paineis.painel_historico;
import painel_criar_editar_texto.obter_texto_editado;
import painel_criar_lista.obter_dados_lista_criada;
import painel_criar_lista.painel_escrever_lista;

/**
 *
 * @author icaro
 */
public class Trei_big {
    
    private static String titulo = "Sistema de Cadastro";
    
    public static void menu(){
        banco.criar_tabela_para_os_formularios();
        banco.executar_query(SQL.sql_tabela_imagens);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               menu m = new menu(titulo);
               m.setVisible(true);
            }
        });
    }
    
    public static void menu_elementos(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               elementos.menu m = new elementos.menu("Criar um formulário");
               m.setVisible(true);
            }
        });
    }
    
    public static void editar(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new editar_formulario("testfulk");
//                    new editar_formulario("gsrt");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Trei_big.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public static void ajuda(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ajuda("Informações de ajuda.", 600, 400);
            }
        });
    }
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
          
//        menu();
//        ajuda();
//        editar();
        
//        *******************************************************
        // exemplo de como criar uma lista e obter o array;
//        obter_dados_lista_criada ob = new obter_dados_lista_criada();
//        String[] itens_da_lista = ob.obter_itens_da_lista();
//        for (int i = 0; i < itens_da_lista.length; i++) {
//            String x = itens_da_lista[i];
//            System.out.println("-->" + x);
//        }
        
        // exemplo de como editar uma lista e obter o array
//        String[] lista = new String[]{"1","2","3","4","5","6","7","8","9","10"};
//        obter_dados_lista_criada ob = new obter_dados_lista_criada(lista, lista.length);
//        
//        String[] itens_da_lista = ob.obter_itens_da_lista();
//        for (int i = 0; i < itens_da_lista.length; i++) {
//            String x = itens_da_lista[i];
//            System.out.println("-->" + x);
//        }
//        *******************************************************
        
        
        
//        *******************************************************
        // exemplo para editar um texto e obter o valor editado;
//        obter_texto_editado edt = new obter_texto_editado("Edite","fbasbfksfban");
//        System.out.println("->" + edt.retornar_texto_editado() );
        
        // exemplo para criar um texto e obter o valor criado;
//        obter_texto_editado edt = new obter_texto_editado("Crie um texto/titulo");
//        System.out.println("->" + edt.retornar_texto_editado() );
//        *******************************************************
        
        
        
//        debugar.excluir_tabelas_e_formularios_criados();
//        debugar.exibir_tabelas();
//        debugar.exibir_tabela(SQL.nome_tabela_formulario);
//        debugar.exibir_tabela("mara");
//        banco.executar_query( "DROP TABLE IMAGENS_FORMULARIO" );
        
//        String img = "/home/icaro/Imagens/img_19922_monica-bellucci.jpg";
//        banco.add_imagem("teste-img", img);
//        
//        img = "/home/icaro/Imagens/Atheris-Squamigera.jpeg";
//        banco.add_imagem("teste-img", img);
//        
//        img = "/home/icaro/Imagens/Anton-Chigurh.jpg";
//        banco.add_imagem("teste-img", img);
//        
//        img = "/home/icaro/Imagens/GARRAFA.jpg";
//        banco.add_imagem("teste-img", img);
        
//        banco.executar_query(SQL.montar_sql_deletar_linha("IMAGENS_FORMULARIO", 101));
//        debugar.exibir_tabela("IMAGENS_FORMULARIO");
        
//        Vector<HashMap<String, Object>> imagens = banco.obter_imagens_banco("teste-img");
//        if( !imagens.isEmpty() ){
//            for (int i = 0; i < imagens.size(); i++) {
//                BufferedImage get = (BufferedImage) imagens.get(i).get("imagem");
//                String nome = (String) imagens.get(i).get("nome");
//                
//                exibir_imagem exi = new exibir_imagem();
//                exi.popup_imagem( nome, get );
//            }
//        }
        
//        new popup_lista_imagens("teste-img");
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception unused) {
//            // Nothing can be done, so just ignore it.
//        }
//        //... Start up GUI.
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new popup_lista_imagens("teste-img");
//            }
//        });

//        String dado = "/home/icaro/Imagens/img_19922_monica-bellucci.jpg";
//        System.out.println( operacoes.se_arquivo_de_imagem(dado) );
        
        
    }

}

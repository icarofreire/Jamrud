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
import java.io.IOException;
import java.util.Vector;
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
import paineis.painel_escrever_lista;

/**
 *
 * @author icaro
 */
public class Trei_big {
    
    private static String titulo = "Sistema de Cadastro";
    
    public static void menu(){
        banco.criar_tabela_exemplo();
//        banco.executar_query(SQL.sql_tabela_local_arquivos);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               menu m = new menu(titulo);
               Thread t = new Thread(m);t.start();
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
    
    public static void escrever_lista(){
        final String[] lista = new String[]{"fabn","ve","vtry","w3","x45","wcar","yjt"};
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               painel_escrever_lista es = new painel_escrever_lista(lista);
                System.out.println("->" + es.getItens());
            }
        });
    }
    
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
          
//        menu();
//        escrever_lista();
        
        String[] lista = new String[]{"fabn","ve","vtry","w3","x45","wcar","yjt"};
        painel_escrever_lista es = new painel_escrever_lista(lista);
        
        System.out.println("->" + es.getItens().size());
        System.out.println("->" + es.itens.size());
        
//        painel_escrever_lista es = new painel_escrever_lista();
//               
//               String[] itens = es.getItens();
//               if( itens != null ){
//                for (int i = 0; i < itens.length; i++) {
//                     String iten = itens[i];
//                     System.out.println(iten);
//                 }
//               }
        
//        menu_elementos();
//        new painel_definir_local_salvar();
//        new sobre();  
        
//        banco.conectar();
//        String nome_tabela = "cadastro_1";
//        String arq = "/home/icaro/Imagens/CADASTRO_1-hnq9f.del";
//        banco.executar_query("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'"+ nome_tabela.toUpperCase() +"','"+ arq +"',';','%',null,0)");
//        banco.executar_query( "SHOW COLUMNS FROM "+ SQL.nome_tabela_local_arquivos.toUpperCase() +"" );
        
//        banco.executar_query( SQL.montar_sql_deletar_linha(SQL.nome_tabela_local_arquivos.toUpperCase(), 1) );
//        banco.exibir_tabela( SQL.nome_tabela_local_arquivos.toUpperCase() );
        
//        System.out.println( SQL.sql_tabela + "\n***" );
//        System.out.println( SQL.montar_sql_criar_tabela(new String[]{"nome","endereço","telefone","data","observações"}, "X_TESTE_Y") );
//         System.out.println( SQL.sql_tabela_formulario );
        
        
//        banco.executar_query( "DROP TABLE BILLY" );
//        banco.executar_query( "DROP TABLE EXX" );
//        banco.executar_query( SQL.montar_sql_deletar_linha(SQL.nome_tabela_formulario.toUpperCase(), 2202) );


//        banco.executar_query( "DELETE FROM " + SQL.nome_tabela_formulario.toUpperCase() );
//        banco.exibir_tabela( SQL.nome_tabela_formulario.toUpperCase() );
//        banco.exibir_tabelas();
        
//        String ser = operacoes.serializar_string(new String[] { "AB", "CD", "EF" } );
//        System.out.println( ser );
//        String[] gg = operacoes.deserializar_string(ser);
//        
//        for (int i = 0; i < gg.length; i++) {
//            String gg1 = gg[i];
//            System.out.println(gg1);
//        }
        
    }

}

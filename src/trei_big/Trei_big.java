/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.SQL;
import banco.banco;
import lib.criar_layout_grid;
import elementos.popup;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import menu_modulos.*;

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
    
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
          
//        menu();
        menu_elementos();
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
        
        
//        criar_layout_grid c = new criar_layout_grid();
//        
//        c.add(new JLabel("Button 1"), new JTextField(20));
//        
//        c.add(new JLabel("Button 1asfas"), new JTextField(20));
//        
//        c.add(new JLabel("Bla fuasfn fsnf frfiuasfn fwaurfr:"));
//        
//        c.add(new JLabel("Button 1frf fasduyf fasgfg"), new JTextField(20));
//        
//        new popup("teste", c.getPainel_externo() );

        
        
    }
    
}

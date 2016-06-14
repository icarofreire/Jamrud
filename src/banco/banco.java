/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import banco.*;
import static banco.SQL.nome_tabela;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Random;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author icaro
 */
public class banco {
    
    private static String dbURL = "jdbc:derby:DefaultAddressBook;create=true;user=me;password=mine";
    private static String nome_tabela = SQL.nome_tabela;
    
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private static String sql_tabela = SQL.sql_tabela;
    public static Vector<String> nome_colunas_consulta;

    public static void iniciar()
    {
        criar_tabela_exemplo();
        
        String[] colunas = new String[]{"NOME", "ENDERECO", "TELEFONE", "DATA", "OBSERVACOES"};
        String[] valores = new String[]{"NOME-1", "ENDERECO-1", "TELEFONE-1", "DATA-1", "OBSERVACOES-1"};
        
        insertRestaurants(colunas, valores);
        
//        Vector<Vector<String>> linhas = selectRestaurants();
//        for (Vector<String> linha : linhas){
//            for (String dado : linha){
//                System.out.print( dado + "\t\t");
//            }
//            System.out.println();
//        }
            
        
        shutdown();

        
    }
    
    public static void criar_tabela_exemplo()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
            criar_tabela(sql_tabela);
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    
    public static void conectar_banco()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    
    private static boolean criar_tabela(String nome_da_tabela) {
        boolean bCreatedTables = false;
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(nome_da_tabela);
            bCreatedTables = true;
        } catch (SQLException ex) {
            if( ex.getSQLState().equalsIgnoreCase("X0Y32") ) {// <= se tabela existe;
//                System.out.println( ex.getSQLState() + ": Tabela já existe." );
            }else{
                ex.printStackTrace();
            }
        }

        return bCreatedTables;
    }
    
    public static boolean executar_query(String sql)
    {
        conectar_banco();
        boolean flag = false;
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(sql);
            flag = true;
        } catch (SQLException ex) {
            if( ex.getSQLState().equalsIgnoreCase("X0Y32") ) {// <= se tabela existe;
            }else if( ex.getSQLState().equalsIgnoreCase("XIE0S") ){// <= se o arquivo de backup já existe;
            }else{
                System.out.println("Estado do ERRO: " + ex.getSQLState() );
                ex.printStackTrace();
            }
        }

        return flag;
    }
    
    private static void insertRestaurants(String[] colunas, String[] valores)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute( SQL.montar_sql_insert(SQL.nome_tabela, colunas, valores) );
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public static Vector<Vector<String>> obter_dados_da_tabela(String nome_da_tabela)
    {
        conectar_banco();
        nome_colunas_consulta = new Vector<String>();
        Vector<Vector<String>> dados = new Vector<Vector<String>>();
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery( "select * from " + nome_da_tabela );
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                nome_colunas_consulta.add( rsmd.getColumnLabel(i) );
            }

//            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
//                String restName = results.getString(2);
//                String cityName = results.getString(3);
//                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
                
                Vector<String> v = new Vector<String>();
                for (int i=1; i<=numberCols; i++){
                    String dado = results.getString(i);
                    v.add(dado);
                }
                dados.add(v);
                
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return dados;
    }
    
    public static void exibir_tabela(String nome_da_tabela)
    {
        conectar_banco();
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery( "select * from " + nome_da_tabela );
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print( rsmd.getColumnLabel(i) + "\t|\t" );
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                 
                for (int i=1; i<=numberCols; i++){
                    String dado = results.getString(i);
                    System.out.println(id + "\t|\t" + dado );                
                }
                
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        
    }
    
    private static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
       
    public static String gerar_nome(int tamanho)
    {
        String AB = "0123456789abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        String sb = "";
        for( int i = 0; i < tamanho; i++ ){
           sb += AB.charAt( rnd.nextInt(AB.length()) );
        }
        return sb;
    }
    
}

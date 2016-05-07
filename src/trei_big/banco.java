/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 *
 * @author icaro
 */
public class banco {
    
    private static String dbURL = "jdbc:derby:DefaultAddressBook;create=true;user=me;password=mine";
    private static String nome_tabela = "cadastro_1";
    
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
    private static String sql_tabela = 
        "CREATE table "+ nome_tabela +" (\n" +
        "ID          INTEGER NOT NULL \n" +
        "                PRIMARY KEY GENERATED ALWAYS AS IDENTITY \n" +
        "                (START WITH 1, INCREMENT BY 1),\n" +
        "NOME    VARCHAR(100), \n" +
        "ENDERECO   VARCHAR(100),\n" +
        "TELEFONE  VARCHAR(100),\n" +
        "DATA       VARCHAR(100),\n" +
        "OBSERVACOES       VARCHAR(100) )";

    public static void iniciar()
    {
        createConnection();
        criar_tabela();
        
        String[] colunas = new String[]{"NOME", "ENDERECO", "TELEFONE", "DATA", "OBSERVACOES"};
        String[] valores = new String[]{"NOME-1", "ENDERECO-1", "TELEFONE-1", "DATA-1", "OBSERVACOES-1"};
        
//        insertRestaurants(colunas, valores);
        
        Vector<Vector<String>> linhas = selectRestaurants();
        for (Vector<String> linha : linhas){
            for (String dado : linha){
                System.out.print( dado + "\t\t");
            }
            System.out.println();
        }
            
        
        shutdown();

        
    }
    
    private static void createConnection()
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
    
    private static boolean criar_tabela() {
        boolean bCreatedTables = false;
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute(sql_tabela);
            bCreatedTables = true;
        } catch (SQLException ex) {
            if( ex.getSQLState().equalsIgnoreCase("X0Y32") ) {
                System.out.println( ex.getSQLState() + ": Tabela já existe." );
            }else{
                ex.printStackTrace();
            }
        }

        return bCreatedTables;
    }
    
    public static String montar_sql_insert(String[] colunas, String[] valores)
    {        
        String j1 = "";
        String j2 = "";
        String SQL = "";
        if( colunas.length == valores.length )
        {
            for(int i=0; i<colunas.length; i++){
                j1 += colunas[i];
                j2 += "'" + valores[i] + "'";
                if(i < (colunas.length-1) ){ j1 += ","; j2 += ","; }
            }
            j1 = "(" + j1 + ")";
            j2 = "(" + j2 + ")";
            SQL = "insert into " + nome_tabela + " " + j1 + " values " + j2;
        }
        return SQL;
    }
    
    private static void insertRestaurants(String[] colunas, String[] valores)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute(montar_sql_insert(colunas, valores));
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    private static Vector<Vector<String>> selectRestaurants()
    {
        Vector<Vector<String>> dados = new Vector<Vector<String>>();
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + nome_tabela);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
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
    
}

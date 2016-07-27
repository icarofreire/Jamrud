/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import banco.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
//import static banco.SQL.nome_tabela;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author icaro
 */
public class banco {
    
    private static String dbURL = "jdbc:derby:DefaultAddressBook;create=true;user=me;password=mine";
//    private static String nome_tabela = SQL.nome_tabela;
    
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;
    
//    private static String sql_tabela = SQL.sql_tabela;
    public static Vector<String> nome_colunas_consulta;
    
    public static void criar_tabela_para_os_formularios()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
            criar_tabela(SQL.sql_tabela_formulario);
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
    
    public static boolean se_tabela_existe(String nome_da_tabela)
    {
        boolean f = false;
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, nome_da_tabela.toUpperCase(), null);
            if (tables.next()) {
                f = true;
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return f;
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
    
    public static boolean inserir_hash_formulario_serializado(String nome_formulario, String hash)
    {
        conectar_banco();
        banco.executar_query(SQL.sql_tabela_formulario);
        
        boolean flag = false;
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO "+ SQL.nome_tabela_formulario.toUpperCase() +" (NOME,HASH_FORMULARIO) VALUES (?,?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome_formulario);
            pstmt.setString(2, hash);
            pstmt.executeUpdate();
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
    
//    private static void insertRestaurants(String[] colunas, String[] valores)
//    {
//        try
//        {
//            stmt = conn.createStatement();
//            stmt.execute( SQL.montar_sql_insert(SQL.nome_tabela, colunas, valores) );
//            stmt.close();
//        }
//        catch (SQLException sqlExcept)
//        {
//            sqlExcept.printStackTrace();
//        }
//    }
    
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
            
                while(results.next())
                {
                    int id = results.getInt(1);                
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
            if( sqlExcept.getSQLState().equalsIgnoreCase("XCL16") ) {
                System.err.println(">> NULO--");
            }else{
                sqlExcept.printStackTrace();
            }
        }
        return dados;
    }
    
    public static String obter_formulario(String nome_formulario)
    {
        conectar_banco();
        nome_colunas_consulta = new Vector<String>();
        Vector<Vector<String>> dados = new Vector<Vector<String>>();
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery( "select * from " + SQL.nome_tabela_formulario.toUpperCase() + " where NOME = '" + nome_formulario + "'" );
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                nome_colunas_consulta.add( rsmd.getColumnLabel(i) );
            }

            while(results.next())
            {
                int id = results.getInt(1);                
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
        
        String hash = "";
        if( (dados != null) && (!dados.isEmpty()) )
        {
            Vector<String> linha = dados.lastElement();
            if( !linha.lastElement().isEmpty() ){
                hash = linha.get(2);
            }
        }
        
        return hash;
    }
    
    public static void exibir_tabela(String nome_da_tabela)
    {
        conectar_banco();
        try
        {
            System.out.println("-------------------------------------------------");
            
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
    
    public static void exibir_tabelas()
    {
        conectar_banco();
        System.out.println("----------Tabelas do banco----------");
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet results = conn.getMetaData().getTables(null, null, null, null);
            while(results.next()) {
                System.out.println( results.getString("TABLE_NAME") );
            }
            results.close();
        } catch (SQLException ex) {
            if( ex.getSQLState().equalsIgnoreCase("X0Y32") ) {// <= se tabela existe;
            }else if( ex.getSQLState().equalsIgnoreCase("XIE0S") ){// <= se o arquivo de backup já existe;
            }else{
                System.out.println("Estado do ERRO: " + ex.getSQLState() );
                ex.printStackTrace();
            }
        }
        System.out.println("------------------------------------");
    }
    
    public static void excluir_todas_as_tabelas_criadas()
    {
        Vector<String> tabelas_padrao_JavaDB = new Vector<String>();
        tabelas_padrao_JavaDB.add( "SYSALIASES" );
        tabelas_padrao_JavaDB.add( "SYSCHECKS" );
        tabelas_padrao_JavaDB.add( "SYSCOLPERMS" );
        tabelas_padrao_JavaDB.add( "SYSCOLUMNS" );
        tabelas_padrao_JavaDB.add( "SYSCONGLOMERATES" );
        tabelas_padrao_JavaDB.add( "SYSCONSTRAINTS" );
        tabelas_padrao_JavaDB.add( "SYSDEPENDS" );
        tabelas_padrao_JavaDB.add( "SYSFILES" );
        tabelas_padrao_JavaDB.add( "SYSFOREIGNKEYS" );
        tabelas_padrao_JavaDB.add( "SYSKEYS" );
        tabelas_padrao_JavaDB.add( "SYSPERMS" );
        tabelas_padrao_JavaDB.add( "SYSROLES" );
        tabelas_padrao_JavaDB.add( "SYSROUTINEPERMS" );
        tabelas_padrao_JavaDB.add( "SYSSCHEMAS" );
        tabelas_padrao_JavaDB.add( "SYSSEQUENCES" );
        tabelas_padrao_JavaDB.add( "SYSSTATEMENTS" );
        tabelas_padrao_JavaDB.add( "SYSSTATISTICS" );
        tabelas_padrao_JavaDB.add( "SYSTABLEPERMS" );
        tabelas_padrao_JavaDB.add( "SYSTABLES" );
        tabelas_padrao_JavaDB.add( "SYSTRIGGERS" );
        tabelas_padrao_JavaDB.add( "SYSUSERS" );
        tabelas_padrao_JavaDB.add( "SYSVIEWS" );
        tabelas_padrao_JavaDB.add( "SYSDUMMY1" );
        tabelas_padrao_JavaDB.add( "ADDRESS" );
        tabelas_padrao_JavaDB.add( "FORMULARIOS_CADASTROS" );
        //---
        
        conectar_banco();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet results = conn.getMetaData().getTables(null, null, null, null);
            while(results.next()) {
                if( tabelas_padrao_JavaDB.contains(results.getString("TABLE_NAME")) == false )
                {
                    banco.executar_query( "DROP TABLE " + results.getString("TABLE_NAME") );
                    System.out.println("Excluida tabela: " + results.getString("TABLE_NAME") );
                }
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public static boolean se_tabela_criada_ja_existe(String nome_da_nova_tabela)
    {
        boolean f = false;
        Vector<String> tabelas_padrao_JavaDB = new Vector<String>();
        tabelas_padrao_JavaDB.add( "SYSALIASES" );
        tabelas_padrao_JavaDB.add( "SYSCHECKS" );
        tabelas_padrao_JavaDB.add( "SYSCOLPERMS" );
        tabelas_padrao_JavaDB.add( "SYSCOLUMNS" );
        tabelas_padrao_JavaDB.add( "SYSCONGLOMERATES" );
        tabelas_padrao_JavaDB.add( "SYSCONSTRAINTS" );
        tabelas_padrao_JavaDB.add( "SYSDEPENDS" );
        tabelas_padrao_JavaDB.add( "SYSFILES" );
        tabelas_padrao_JavaDB.add( "SYSFOREIGNKEYS" );
        tabelas_padrao_JavaDB.add( "SYSKEYS" );
        tabelas_padrao_JavaDB.add( "SYSPERMS" );
        tabelas_padrao_JavaDB.add( "SYSROLES" );
        tabelas_padrao_JavaDB.add( "SYSROUTINEPERMS" );
        tabelas_padrao_JavaDB.add( "SYSSCHEMAS" );
        tabelas_padrao_JavaDB.add( "SYSSEQUENCES" );
        tabelas_padrao_JavaDB.add( "SYSSTATEMENTS" );
        tabelas_padrao_JavaDB.add( "SYSSTATISTICS" );
        tabelas_padrao_JavaDB.add( "SYSTABLEPERMS" );
        tabelas_padrao_JavaDB.add( "SYSTABLES" );
        tabelas_padrao_JavaDB.add( "SYSTRIGGERS" );
        tabelas_padrao_JavaDB.add( "SYSUSERS" );
        tabelas_padrao_JavaDB.add( "SYSVIEWS" );
        tabelas_padrao_JavaDB.add( "SYSDUMMY1" );
        tabelas_padrao_JavaDB.add( "ADDRESS" );
        tabelas_padrao_JavaDB.add( "FORMULARIOS_CADASTROS" );
        //---
        
        conectar_banco();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet results = conn.getMetaData().getTables(null, null, null, null);
            while(results.next()) {
                if( tabelas_padrao_JavaDB.contains(results.getString("TABLE_NAME")) == false )
                {
                    if( results.getString("TABLE_NAME").equalsIgnoreCase(nome_da_nova_tabela) ){
                        f = true; break;
                    }
                }
            }
            results.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return f;
    }
    
    public static void excluir_formularios_criados()
    {
        banco.executar_query( "DELETE FROM " + SQL.nome_tabela_formulario.toUpperCase() );
    }
    
    public static void add_imagem(String nome_formulario, String arquivo_imagem) throws IOException
    {
        conectar_banco();
        PreparedStatement ps;
        try {
            File arq_img = new File( arquivo_imagem );
            ps = conn.prepareStatement("INSERT INTO " + SQL.nome_tabela_imagens.toUpperCase() + "(NOME_FORMULARIO,NOME_IMAGEM,IMAGEM) " + "VALUES(?,?,?)");
            ps.setString(1, nome_formulario);
            ps.setString(2, arq_img.getName());
            
            Blob blob = conn.createBlob();
            ImageIcon ii = new ImageIcon(arquivo_imagem);

            ObjectOutputStream oos;
            oos = new ObjectOutputStream(blob.setBinaryStream(1));
            oos.writeObject(ii);
            oos.close();
            ps.setBlob(3, blob);
            ps.execute();
            blob.free();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Vector<HashMap<String, Object>> obter_imagens_banco(String nome_do_formulario) throws IOException, ClassNotFoundException
    {
        Vector<HashMap<String, Object>> imagens = new Vector<HashMap<String, Object>>();
        conectar_banco();
        ResultSet rs;
        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM "+ SQL.nome_tabela_imagens.toUpperCase() +" WHERE NOME_FORMULARIO = '"+ nome_do_formulario +"'");
            while (rs.next())
            {
                Blob photo = rs.getBlob(4);
                ObjectInputStream ois = null;
                ois = new ObjectInputStream(photo.getBinaryStream());

                ImageIcon image = (ImageIcon) ois.readObject();
                BufferedImage bi = new BufferedImage(
                        image.getIconWidth(),
                        image.getIconHeight(),
                        BufferedImage.TYPE_INT_RGB
                );

                Graphics g = bi.createGraphics();
                image.paintIcon(null, g, 0, 0);
                g.dispose();
                
                HashMap<String, Object> mapa = new HashMap<String, Object>();
                mapa.put("imagem", bi);
                mapa.put("nome", rs.getString(3) );
                mapa.put(rs.getString(3), bi );
                mapa.put("id", rs.getString(1) );
                imagens.add(mapa);
            }
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imagens;
    }
    
}

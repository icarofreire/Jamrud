/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

/**
 * Classe de todos os SQL's do projeto;
 * @author icaro
 */
public class SQL {
    
    public static String nome_tabela = "cadastro_1";
    public static String listar = "select * from " + nome_tabela;
    
    public static String sql_tabela = 
    "CREATE table "+ nome_tabela +" (\n" +
    "ID INTEGER NOT NULL \n" +
    "PRIMARY KEY GENERATED ALWAYS AS IDENTITY \n" +
    "(START WITH 1, INCREMENT BY 1),\n" +
    "NOME VARCHAR(100), \n" +
    "ENDERECO VARCHAR(100),\n" +
    "TELEFONE VARCHAR(100),\n" +
    "DATA VARCHAR(100),\n" +
    "OBSERVACOES VARCHAR(100) )";
    
    public static String montar_sql_deletar_linha(int ID)
    {
        return "DELETE FROM "+ nome_tabela +" WHERE id = "+ ID +"";
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
    
    public static String montar_sql_modificar(String campo, String valor_campo, int id)
    {
        String SQL = "update "+ nome_tabela +" set "+ campo +" = '"+ valor_campo +"' where id = " + id;
        return SQL;
    }
    
}

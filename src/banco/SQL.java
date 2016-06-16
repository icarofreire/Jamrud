/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.io.File;

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
    
    public static String nome_tabela_formulario = "formularios_cadastros";
    public static String sql_tabela_formulario = 
    "CREATE table "+ nome_tabela_formulario +" (\n" +
    "ID INTEGER NOT NULL \n" +
    "PRIMARY KEY GENERATED ALWAYS AS IDENTITY \n" +
    "(START WITH 1, INCREMENT BY 1),\n" +
    "NOME VARCHAR(200), \n" +
    "HASH_FORMULARIO CLOB )";
    
//    public static String nome_tabela_local_arquivos = "local_salvar_arquivos";
//    public static String sql_tabela_local_arquivos = 
//    "CREATE table "+ nome_tabela_local_arquivos +" (\n" +
//    "ID INTEGER NOT NULL \n" +
//    "PRIMARY KEY GENERATED ALWAYS AS IDENTITY \n" +
//    "(START WITH 1, INCREMENT BY 1),\n" +
//    "LOCAL_ARQUIVOS VARCHAR(200) )";
    
//    public static String montar_sql_modificar_local_arquivo(String novo_local, String id)
//    {
//        String sql_modificar =
//        "UPDATE "+ nome_tabela_local_arquivos.toUpperCase() +"\n" +
//        "SET LOCAL_ARQUIVOS='"+ novo_local +"'\n" +
//        "WHERE ID = "+ id +"";
//        
//        return sql_modificar;
//    }

    public static String montar_sql_deletar_linha(String nome_da_tabela, int ID)
    {
        return "DELETE FROM "+ nome_da_tabela.toUpperCase() +" WHERE id = "+ ID +"";
    }
    
    public static String montar_sql_insert(String nome_da_tabela, String[] colunas, String[] valores)
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
            SQL = "insert into " + nome_da_tabela.toUpperCase() + " " + j1 + " values " + j2;
        }
        return SQL;
    }
    
    public static String montar_sql_modificar(String campo, String valor_campo, int id)
    {
        String SQL = "update "+ nome_tabela +" set "+ campo +" = '"+ valor_campo +"' where id = " + id;
        return SQL;
    }
    
    public static String montar_sql_criar_tabela(String[] colunas, String nome_para_tabela)
    {
        String sql_tabela = 
        "CREATE table "+ nome_para_tabela +" (\n" +
        "ID INTEGER NOT NULL \n" +
        "PRIMARY KEY GENERATED ALWAYS AS IDENTITY \n" +
        "(START WITH 1, INCREMENT BY 1),\n";
        
        for(int i=0; i<colunas.length-1; i++) {
            sql_tabela += colunas[i].toUpperCase() + "VARCHAR(200),\n";
        }
        sql_tabela += colunas[colunas.length-1].toUpperCase() + "VARCHAR(200) )";
        
        return sql_tabela;
    }
    
}

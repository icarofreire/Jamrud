/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.banco;
import javax.swing.JPanel;

/**
 *
 * @author icaro
 */
public class debugar {
    
    public static void exibir_array(String[] array)
    {
        System.out.println("*****");
        for (int i = 0; i < array.length; i++) {
            String array1 = array[i];
            System.out.println("->"+array1);
        }System.out.println("tam: "+array.length + "\n*****");
    }
    
    public static void exibir_tabela(String nome_da_tabela_exibir)
    {
        banco.exibir_tabela(nome_da_tabela_exibir.toUpperCase());
    }
    
    public static void exibir_tabelas()
    {
        banco.exibir_tabelas();
    }
    
    public static void excluir_tabelas_e_formularios_criados()
    {
        banco.excluir_todas_as_tabelas_criadas();
        banco.excluir_formularios_criados();
    }
    
    public static void exibir_names_em_painel(JPanel painel)
    {
        operacoes_painel.exibir_names_em_painel(painel);
    }
    
}

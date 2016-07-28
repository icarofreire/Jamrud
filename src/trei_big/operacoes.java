/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import elementos.prefixos;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author icaro
 */
public class operacoes {
    
    public static String gerar_name_para_componente(String prefixo)
    {
        return prefixo + "={" + gerar_chave(20) + "}";
    }
    
    public static String pegar_name(String prefixo, String chave)
    {
        return prefixo + "={" + chave + "}";
    }
    
    public static Vector<Component> pegar_componentes_por_prefixo(JPanel painel, String prefixo)
    {
        Vector<Component> componente = new Vector<Component>();
        Component[] components = painel.getComponents();
        for (int i=0; i < components.length; i++)
        {
            String name_componente = components[i].getName();
            if( (name_componente != null) && (name_componente.indexOf(prefixo) != -1) ){
                 componente.add(components[i]);
            }
        }
        return componente;
    }
    
    public static String gerar_chave()
    {
        return "={" + gerar_chave(20) + "}";
    }
    
    public static String gerar_chave(int tamanho)
    {
        String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        int n = alphabet.length();
        String result = new String(); 
        Random r = new Random();

        for (int i=0; i<tamanho; i++){
            result = result + alphabet.charAt(r.nextInt(n));
        }
        return result;
    }
    
    public static String pegar_chave_em_string(String nome_com_chave)
    {
        String chave = "";
        int a = nome_com_chave.indexOf('{');
        int f = nome_com_chave.indexOf('}');
        if( (a!=-1) && (f!=-1) ){
            chave = nome_com_chave.substring(a+1, f);
        }
        return chave;
    }
    
    /* Se o nome da tabela ou coluna é contido apenas com iniciais alfabeticas,
    e contendo somente: letras alfabeticas, espaços em branco, ou digitos no restante do nome; */
    public static boolean se_titulo_correto(String titulo)
    {
         int con = 0;
         char pri = titulo.charAt(0);
         if (Character.isAlphabetic(pri)){
             con = 1;
             for(int i=1; i<titulo.length(); i++){
                 if( (titulo.charAt(i) == ' ') || Character.isLetterOrDigit(titulo.charAt(i)) ){
                     con++;
                 }
             }
         }
         boolean f = false;
         if( con == titulo.length() ){
             f = true;
         }
         return f;
    }
    
    public static boolean se_dados_duplicados(final String[] zipcodelist)
    {
        Set<String> lump = new HashSet<String>();
        for (String i : zipcodelist)
        {
          i = i.toUpperCase();// <-- trasforma todas em maiuscula para não haver strings duplicadas mesmo insensíveis ao caso;
          if (lump.contains(i)){ 
              return true;
          }else{
              lump.add(i);
          }
        }
        return false;
    }
    
    
    public static String serializar_string(String[] alvo)
    {
        String token = "|::|";
        String str_serializada = "";
        for (int i = 0; i < alvo.length-1; i++) {
            String alvo1 = alvo[i];
            str_serializada += alvo1 + token;
        }
        str_serializada += alvo[alvo.length-1];
        return str_serializada;
    }
    
    public static String[] deserializar_string(String str_serializada)
    {
        return str_serializada.split("\\|\\:\\:\\|");
    }
    
    public static Vector<String> remover_visinhos_duplicados(Vector<String> vetor)
    {
        
        List<String> list = new ArrayList<>(vetor);
        for(int i = list.size() - 1; i > 0; i--){
            if(list.get(i).compareTo(list.get(i-1)) == 0){
                list.remove(i);
            }
        }
        Vector<String> novo = new Vector<String>();
        String[] arr = list.toArray(new String[]{});
        for (int i = 0; i < arr.length; i++) {
            novo.add(arr[i]);
        }
        return novo;
    }
    
    public static Vector<String> modificar_dadosV1_por_dadosV2_por_token(String token_dos_lugares, Vector<String> v1, Vector<String>v2)
    {
        int prox = 0;
        for (int i = 0; i < v1.size(); i++) {
            String get1 = v1.get(i);
            if(get1.compareTo(token_dos_lugares) == 0)
            {
                if( prox < v2.size() ){
                    v1.set(i, v2.get(prox) ); prox++;
                }
            }  
        }
        return v1;
    }
    
    public static Vector<String> modificar_dadosV1_por_dadosV2_por_token_parecido(String token_dos_lugares, Vector<String> v1, Vector<String>v2)
    {
        int prox = 0;
        for (int i = 0; i < v1.size(); i++) {
            String get1 = v1.get(i);
            if(get1.indexOf(token_dos_lugares) != -1)
            {
                if( prox < v2.size() ){
                    v1.set(i, v2.get(prox) ); prox++;
                }
            }  
        }
        return v1;
    }
    
    public static boolean se_arquivo_de_imagem(String dado)
    {
        boolean f = false;
        File arq = new File(dado);
        if( arq.exists() && arq.isFile() ){
            int i = dado.lastIndexOf('.');
            if ( i > 0 ) {
                String extensao = dado.substring(i+1);
                if( 
                        extensao.equalsIgnoreCase("jpg") ||
                        extensao.equalsIgnoreCase("jpeg") ||
                        extensao.equalsIgnoreCase("png") ||
                        extensao.equalsIgnoreCase("gif")
                  ){
                    f = true;
                }
            }
        }
        return f;
    }
    
}

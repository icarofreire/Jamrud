/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.SQL;
import banco.banco;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 *
 * @author icaro
 */
public class serializar {
//    
//    private static String caminho_definido;
//    
//    private static boolean Obter_caminho_e_definido()
//    {
//        boolean f = false;
//        Vector<Vector<String>> linhas = banco.obter_dados_da_tabela(SQL.nome_tabela_local_arquivos);
//        if( (linhas != null) && (!linhas.isEmpty()) )
//        {
//            Vector<String> vl = linhas.lastElement();
//            if( !vl.lastElement().isEmpty() ){
//                caminho_definido = vl.lastElement();
//                f = true;
//            }
//        }else{
//            aviso.mensagem_atencao("Configure no menu o local para salvar os arquivos do sistema.", "Definir local");
//        }
//        return f;
//    }
//    
//    /**
//     * deserialize to Object from given file. We use the general Object so as
//     * that it can work for any Java Class.
//     */
//    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
//        Object obj = null;
//        if( Obter_caminho_e_definido() ){
//            FileInputStream fis = new FileInputStream(caminho_definido + fileName);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            ObjectInputStream ois = new ObjectInputStream(bis);
//            obj = ois.readObject();
//            ois.close();
//        }
//        return obj;
//    }
// 
//    /**
//     * serialize the given object and save it to given file
//     */
//    public static void serialize(Object obj, String fileName) throws IOException {
//        if( Obter_caminho_e_definido() ){
//            FileOutputStream fos = new FileOutputStream(caminho_definido + fileName);
//            BufferedOutputStream bos = new BufferedOutputStream(fos);
//            ObjectOutputStream oos = new ObjectOutputStream(bos);
//            oos.writeObject(obj);
//            oos.close();
//        }
//    }
//    
}

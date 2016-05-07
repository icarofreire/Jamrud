/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author icaro
 */
public class aviso {
    
    public static void mensagem_sucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, 
                mensagem,
                "OK!",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icones/sucesso.png"));
    }
    
    public static void mensagem_falha(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(null, 
                mensagem,
                titulo,
                JOptionPane.ERROR_MESSAGE, new ImageIcon("icones/sem_sucesso.png"));
    }
    
    public static void mensagem_atencao(String mensagem) {
        JOptionPane.showMessageDialog(null, 
                mensagem,
                "Atenção",
                JOptionPane.WARNING_MESSAGE, new ImageIcon("icones/atencao.png"));
    }
    
    public static void mensagem_ok(String mensagem) {
        JOptionPane.showMessageDialog(null, 
                mensagem,
                "OK!",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icones/ok.png"));
    }
    
    public static void mensagem_erro(String mensagem) {
        JOptionPane.showMessageDialog(null, 
                mensagem,
                "Erro!",
                JOptionPane.ERROR_MESSAGE, new ImageIcon("icones/erro-64.png"));
    }
    
}

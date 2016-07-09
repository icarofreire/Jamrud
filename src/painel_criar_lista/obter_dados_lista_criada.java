/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painel_criar_lista;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author icaro
 */
public class obter_dados_lista_criada {

    private painel_escrever_lista es;
    private String[] itens_da_lista;
    private Thread thre;
            
    public obter_dados_lista_criada(final String[] lista) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
               es = new painel_escrever_lista(lista);
               
            }
        });
        
    }
    
    public obter_dados_lista_criada() {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
               es = new painel_escrever_lista();
               
            }
        });
        
    }

    public String[] obter_itens_da_lista() throws InterruptedException {
        
        thre = new Thread(
                new Runnable()
        {
            @Override
            public void run() {
                while( (!Thread.currentThread().isInterrupted()) )
                {
                    if( es != null && (es.se_botao_fechar_clicado == true) ){
                        thre.interrupt();
                    }
                    try {
                        try{
                            itens_da_lista = es.getItens();
                            if( (itens_da_lista != null) && itens_da_lista.length > 0 )
                            {
                                thre.interrupt();
                            }
                        }catch(java.lang.NullPointerException nn){}

                        Thread.sleep(500);// 1000 = um segundo;
                    } catch (InterruptedException ie) {
                        thre.interrupt(); 
                    }
                }
            }
        }
        );
        thre.start();
        thre.join();
        return itens_da_lista;
    }

    
}

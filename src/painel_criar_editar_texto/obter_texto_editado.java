/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painel_criar_editar_texto;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author icaro
 */
public class obter_texto_editado {
    
    private Thread thre;
    private painel_editar_texto edt;
    private String texto_editado;
    
    public obter_texto_editado(final String titulo, final String texto) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               edt = new painel_editar_texto();
               edt.exibir_texto(titulo, texto);
            }
        });
        
    }
    
    public obter_texto_editado(final String titulo) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               edt = new painel_editar_texto();
               edt.exibir_texto(titulo);
            }
        });
        
    }
    
    public String retornar_texto_editado() throws InterruptedException {
        
        thre = new Thread(
                new Runnable()
        {
            @Override
            public void run() {
                while( (!Thread.currentThread().isInterrupted()) )
                {
                    if( edt != null && (edt.se_botao_fechar_clicado == true) ){
                        thre.interrupt();
                    }
                    try {
                        try{
                            texto_editado = edt.obter_texto_editado();
                            if( (texto_editado != null) && (!texto_editado.isEmpty()) )
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
        return texto_editado;
    }
    
}

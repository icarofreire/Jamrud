/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

/**
 * Classe utilizada para modificar a aparencia dos componentes Swing;
 * O estilo da aparencia é definido no arquivo XML;
 * @author icaro
 */
public class aparencia_swing {
    
    private static String synthFile = "aparencia.xml";
    
    public static void mudar_aparencia(Class classe_gui) {
            SynthLookAndFeel lookAndFeel = new SynthLookAndFeel();
            try {
                lookAndFeel.load(classe_gui.getResourceAsStream(synthFile),
                                 classe_gui);
                UIManager.setLookAndFeel(lookAndFeel);
            }
            catch (Exception e) {
                System.err.println("Couldn't get specified look and feel ("
                                   + lookAndFeel
                                   + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
    }
    
}

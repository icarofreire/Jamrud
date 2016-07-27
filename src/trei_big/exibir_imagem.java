/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author icaro
 */
public class exibir_imagem extends JPanel{
    
    private int largura = 800;
    private int altura = 600;
    private BufferedImage image;

    public static BufferedImage abrir_imagem(String caminho_imagem) throws IOException
    {
        return ImageIO.read(new File( caminho_imagem ));
    }

    public void popup_imagem(String titulo_janela_imagem, BufferedImage image)
    {
        this.image = image;
        JFrame frame = new JFrame();
        JScrollPane scroll = new JScrollPane(new JLabel(new ImageIcon(image)));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scroll.setViewportBorder(null);// <= remover a borda quadrada do scroll dentro do painel;

        frame.getContentPane().add( scroll );
        frame.pack();
        frame.setTitle(titulo_janela_imagem);
        frame.setSize(new Dimension(largura, altura));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// <= efetua um "dispose()" quando clicado no "x" para fechar;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}//~exibir_imagem;

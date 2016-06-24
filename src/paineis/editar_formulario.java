/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paineis;

import banco.SQL;
import banco.banco;
import elementos.popup;
import elementos.prefixos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import menu_modulos.obter_dados_formulario;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class editar_formulario {
    
    private static int cy = -1;
    
    public editar_formulario() {
        
        Vector<Vector<String>> dd = banco.obter_dados_da_tabela(SQL.nome_tabela_formulario.toUpperCase());
        final JPanel pi = (JPanel)operacoes_painel.deserializar_obj(dd.lastElement().get(2));
        new popup("teste", pi );
        
        final obter_dados_formulario dados_form = new obter_dados_formulario();
        
        JPanel p_botao = (JPanel) operacoes_painel.pegar_componente_em_painel(pi, prefixos.prefixo_painel_interno_para_botao);
                        JButton btn_enviar = (JButton) operacoes_painel.pegar_componente_em_painel(p_botao, prefixos.prefixo_botao_enviar);
                        
                        // \/\/ >> aqui o evento do click no botao ENVIAR em cada formulário; << \/\/
                        if( btn_enviar != null )
                        {
                            btn_enviar.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    System.out.println("ENVIAR");
                                    dados_form.buscar_componentes_recursivo(pi);
                                }
                            });
                        }
                        // /\/\ >> aqui o evento do click no botao ENVIAR em cada formulário; << /\/\
        
    }
 
    
    
    /* habilita ou desabilita todos os componentes filhos de um componente pai recirsivamente; */
    public static void setEnabled_recursivo(Component component, boolean enabled, final JPanel painel) {
        component.setEnabled(enabled);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                
                setEnabled_recursivo(child, enabled, painel);
//                buscar_componentes_recursivo(child);
                            //---
                           child.addMouseListener(new MouseListener(){
                                @Override
                                public void mouseClicked(MouseEvent me) {
                                    if( me.getY() != cy ){
                                    painel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                                    cy = me.getY();
                                    System.out.println("["+ painel.getLocation().x + ", " + cy+"]");
                                    }
                                }

                                @Override
                                public void mousePressed(MouseEvent me) {
                                }

                                @Override
                                public void mouseReleased(MouseEvent me) {
                                }

                                @Override
                                public void mouseEntered(MouseEvent me) {
                                }

                                @Override
                                public void mouseExited(MouseEvent me) {
//                                    painel.setBorder(BorderFactory.createEmptyBorder());
                                }
                            });
                        //---
            }
        }
    }
    
}

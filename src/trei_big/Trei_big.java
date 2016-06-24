/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.SQL;
import banco.banco;
import elementos.popup;
import elementos.prefixos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import menu_modulos.*;

/**
 *
 * @author icaro
 */
public class Trei_big {
    private static int cy = -1;
    private static String titulo = "Sistema de Cadastro";
    
    public static void menu(){
        banco.criar_tabela_exemplo();
//        banco.executar_query(SQL.sql_tabela_local_arquivos);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               menu m = new menu(titulo);
               Thread t = new Thread(m);t.start();
               m.setVisible(true);
            }
        });
    }
    
    public static void menu_elementos(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {
            // Nothing can be done, so just ignore it.
        }
        
        //... Start up GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               elementos.menu m = new elementos.menu("Criar um formulário");
               m.setVisible(true);
            }
        });
    }
    
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
          
//        menu();
//        menu_elementos();
//        new painel_definir_local_salvar();
//        new sobre();  
        
//        banco.conectar();
//        String nome_tabela = "cadastro_1";
//        String arq = "/home/icaro/Imagens/CADASTRO_1-hnq9f.del";
//        banco.executar_query("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE(null,'"+ nome_tabela.toUpperCase() +"','"+ arq +"',';','%',null,0)");
//        banco.executar_query( "SHOW COLUMNS FROM "+ SQL.nome_tabela_local_arquivos.toUpperCase() +"" );
        
//        banco.executar_query( SQL.montar_sql_deletar_linha(SQL.nome_tabela_local_arquivos.toUpperCase(), 1) );
//        banco.exibir_tabela( SQL.nome_tabela_local_arquivos.toUpperCase() );
        
//        System.out.println( SQL.sql_tabela + "\n***" );
//        System.out.println( SQL.montar_sql_criar_tabela(new String[]{"nome","endereço","telefone","data","observações"}, "X_TESTE_Y") );
//         System.out.println( SQL.sql_tabela_formulario );
        
        
//        banco.executar_query( "DROP TABLE BILLY" );
//        banco.executar_query( "DROP TABLE EXX" );
//        banco.executar_query( SQL.montar_sql_deletar_linha(SQL.nome_tabela_formulario.toUpperCase(), 2202) );


//        banco.executar_query( "DELETE FROM " + SQL.nome_tabela_formulario.toUpperCase() );
//        banco.exibir_tabela( SQL.nome_tabela_formulario.toUpperCase() );
//        banco.exibir_tabelas();
        
        
//        criar_layout_grid c = new criar_layout_grid();
//        
//        c.add(new JLabel("Button 1"), new JTextField(20));
//        
//        c.add(new JLabel("Button 1asfas"), new JTextField(20));
//        
//        c.add(new JLabel("Bla fuasfn fsnf frfiuasfn fwaurfr:"));
//        
//        c.add(new JLabel("Button 1frf fasduyf fasgfg"), new JTextField(20));
//        
        Vector<Vector<String>> dd = banco.obter_dados_da_tabela(SQL.nome_tabela_formulario.toUpperCase());
        JPanel pi = (JPanel)operacoes_painel.deserializar_obj(dd.lastElement().get(2));
        new popup("teste", pi );

        operacoes_painel.exibir_names_em_painel(pi);
        
        for (int i = 0; i < prefixos.prefixos_paineis.length; i++) {
            String prefix = prefixos.prefixos_paineis[i];
            Vector<Component> comps_p = operacoes_painel.pegar_todos_componentes_em_painel_com_prefixo(pi, prefix);
            if( comps_p != null )
            {
                for (int j = 0; j < comps_p.size(); j++)
                {
                    final JPanel painel = (JPanel) comps_p.get(j);
                    System.out.println("name ->: " + painel.getName() );
                    
                    setEnabled_recursivo(painel, false, painel);
                    JPanel p_int = (JPanel) operacoes_painel.pegar_componente_em_painel(painel, prefixos.prefixo_painel_interno);
                    if(p_int != null)
                    {
//                        Component[] components = p_int.getComponents();
//                        for (int c=0; c < components.length; c++) {
//                            components[c].setEnabled(false);
//                            components[c].addMouseListener(new MouseListener(){
//
//                                @Override
//                                public void mouseClicked(MouseEvent me) {
//                                    painel.setBorder(BorderFactory.createLineBorder(Color.RED));
//                                }
//
//                                @Override
//                                public void mousePressed(MouseEvent me) {
////                                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                                }
//
//                                @Override
//                                public void mouseReleased(MouseEvent me) {
////                                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                                }
//
//                                @Override
//                                public void mouseEntered(MouseEvent me) {
////                                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                                }
//
//                                @Override
//                                public void mouseExited(MouseEvent me) {
////                                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                                }
//                            });
//                        }//fim for
//                    }
                    
                    painel.addMouseListener(new MouseListener(){

                        @Override
                        public void mouseClicked(MouseEvent me) {
//                            painel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//                            System.out.println("["+ painel.getLocation().x + ", " + painel.getLocation().y+"]");
                        }

                        @Override
                        public void mousePressed(MouseEvent me) {
//                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void mouseReleased(MouseEvent me) {
//                             System.out.println("R:\nx: "+ painel.getLocation().x+"\ny: " + painel.getLocation().y);
                        }

                        @Override
                        public void mouseEntered(MouseEvent me) {
//                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void mouseExited(MouseEvent me) {
//                             System.out.println("P: ["+ painel.getLocation().x + ", " + painel.getLocation().y+"]");
                            int xm = me.getPoint().x;
                            int ym = me.getPoint().y;
                            
                            int xp = painel.getLocation().x;
                            int yp = painel.getLocation().y;
//                            if( (xm > xp) )
                            
//                            painel.setBorder(BorderFactory.createEmptyBorder());
                        }
                    });
                    
                }
                            
                }
            }
            
        }
        
        
    }
    
    /* habilita ou desabilita todos os componentes filhos de um componente pai recirsivamente; */
    public static void setEnabled_recursivo(Component component, boolean enabled, final JPanel painel) {
        component.setEnabled(enabled);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                
                setEnabled_recursivo(child, enabled, painel);
                buscar_componentes_recursivo(child);
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
    
    public static void buscar_componentes_recursivo(Component component) {
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                buscar_componentes_recursivo(child);
                /* \/ Pega o nome da classe do componente. Ex: JLabel, JTextField, JTextArea, JViewport; \/ */
                String nome_classe = child.getClass().getSimpleName();
                System.out.println("Nome classe: " + nome_classe);
            }
        }
    }
    
}

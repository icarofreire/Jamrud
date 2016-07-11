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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import menu_modulos.obter_dados_formulario;
import painel_criar_editar_texto.obter_texto_editado;
import painel_criar_lista.obter_dados_lista_criada;
import trei_big.aviso;
import trei_big.operacoes_painel;

/**
 *
 * @author icaro
 */
public class editar_formulario {
    
    private static int cy = -1;
    private JPanel painel_deserializado;
    private obter_texto_editado edt;
    private obter_dados_lista_criada ob;
    private Thread thre;
    private int f_radios_ou_checks_ou_select = -1;
    
    public editar_formulario(String nome_do_formulario) throws InterruptedException {
        
        Vector<Vector<String>> dd = banco.obter_dados_da_tabela(SQL.nome_tabela_formulario);
        
        String hash_formulario = null;
        for (int i = 0; i < dd.size(); i++) {
            Vector<String> get = dd.get(i);
            String nome = get.get(1);
            if( nome_do_formulario.equalsIgnoreCase(nome) ){
                hash_formulario = get.get(2);
            }
        }
        
        final JPanel painel_deserializado = (JPanel)operacoes_painel.deserializar_obj(/*dd.lastElement().get(2)*/hash_formulario);
//        new popup("Editar", painel_deserializado );
        
        

            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    new popup("Editar", painel_deserializado );
                }
            });
            
      
                            
                            

        
        
        final obter_dados_formulario dados_form = new obter_dados_formulario();
//        
        JPanel p_botao = (JPanel) operacoes_painel.pegar_componente_em_painel(painel_deserializado, prefixos.prefixo_painel_interno_para_botao);
                        JButton btn_enviar = (JButton) operacoes_painel.pegar_componente_em_painel(p_botao, prefixos.prefixo_botao_enviar);
//                        
                        setEnabled_recursivo(painel_deserializado, false);
                        
//                        // \/\/ >> aqui o evento do click no botao ENVIAR em cada formulário; << \/\/
//                        if( btn_enviar != null )
//                        {
//                            btn_enviar.addActionListener(new ActionListener(){
//                                @Override
//                                public void actionPerformed(ActionEvent ae) {
//                                    System.out.println("ENVIAR");
//                                    dados_form.buscar_componentes_recursivo(pi);
//                                }
//                            });
//                        }
//                        // /\/\ >> aqui o evento do click no botao ENVIAR em cada formulário; << /\/\
//        
    }
// 
//    
//    
//    /* habilita ou desabilita todos os componentes filhos de um componente pai recirsivamente; */
    public void setEnabled_recursivo(Component component, boolean enabled) {
        component.setEnabled(enabled);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                
                setEnabled_recursivo(child, enabled);
                
                String name = child.getName();
                if(  (name != null) && 
                        ( (name.indexOf("pfx_painel_interno") != -1) || (name.indexOf("painel_opcoes") != -1) )
                  )
                {
                    final JPanel pin = (JPanel) child;
                    filhos_clicaveis(pin, false, pin);
                }
            }
        }
    }
    
    public void filhos_clicaveis(Component component, boolean enabled, final JPanel pinterno) {
        component.setEnabled(enabled);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                
                filhos_clicaveis(child, enabled, pinterno);
//                System.out.println("classe: " + child.getName() );
                
                    child.addMouseListener(new MouseListener(){
                        @Override
                        public void mouseClicked(MouseEvent me) {
                            if( me.getClickCount() == 2 )
                            {
                                
                                System.out.println( "Pintar: " + pinterno.getName() );
                                
                                if( (pinterno.getName().indexOf("pfx_painel_interno") != -1) )
                                {
                                    pinterno.setBorder(BorderFactory.createLineBorder(Color.RED));
                                    Vector<Component> tits = operacoes_painel.pegar_todos_componentes_em_painel_com_prefixo(pinterno, "pfx_titulos_componentes");
                                    
                                    final JLabel get = (JLabel) tits.firstElement();
                                    final String titulo = get.getText();
                                    System.out.println("titulo: " + titulo);
                                    
                                    SwingUtilities.invokeLater(new Runnable()
                                    {
                                        @Override
                                        public void run(){
                                            edt = new obter_texto_editado("Edite", titulo);
                                            thre = new Thread(new Runnable()
                                            {
                                                @Override
                                                public void run() {
                                                    while( (!Thread.currentThread().isInterrupted()) )
                                                    {
                                                        if( edt != null )
                                                        {
                                                            try {
                                                                    String titulo_editado = edt.retornar_texto_editado();
                                                                    if(titulo_editado != null){
                                                                        get.setText(titulo_editado);
                                                                    }
                                                                    pinterno.setBorder(null);//<= retirar borda;
                                                                } catch (InterruptedException ex) {
                                                                    Logger.getLogger(editar_formulario.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                            thre.interrupt();
                                                        }
                                                    }
                                                }
                                            }
                                            );
                                            thre.start();
                                        }
                                    });
                                    
                                    
                                }
                                
                                if( (pinterno.getName().indexOf("painel_opcoes") != -1) )
                                {
                                    pinterno.setBorder(BorderFactory.createLineBorder(Color.RED));
                                    final Vector<String> titulos_editar = new Vector<String>();
                                    Vector<Component> tits = operacoes_painel.pegar_todos_componentes_em_painel_com_prefixo(pinterno, "pfx_titulos_componentes");
                                    operacoes_painel.exibir_names_em_painel(pinterno);
                                    
                                    JPanel radios = (JPanel) operacoes_painel.pegar_componente_em_painel(pinterno, "painel_radios");
                                    JPanel checks = (JPanel) operacoes_painel.pegar_componente_em_painel(pinterno, "painel_checkbox");
                                    JPanel select = (JPanel) operacoes_painel.pegar_componente_em_painel(pinterno, "painel_select");
                                    
                                    
                                    JPanel pr = null;
                                    if(radios != null){
                                        pr = radios; f_radios_ou_checks_ou_select = 1;
                                        
                                    }else if(checks != null){
                                        pr = checks; f_radios_ou_checks_ou_select = 2;
                                        
                                    }else if(select != null){
                                        pr = select; f_radios_ou_checks_ou_select = 3;
                                    }
                                    
                                    if( pr != null )
                                    {
                                        final JLabel get = (JLabel) tits.firstElement();
                                        final String titulo = get.getText();
                                        titulos_editar.add(titulo);
                                        
                                        final Component[] cc = pr.getComponents();
                                        for (int i = 0; i < cc.length; i++) 
                                        {
                                            if( f_radios_ou_checks_ou_select == 1 )
                                            {
                                                JRadioButton cc1 = (JRadioButton) cc[i];
                                                titulos_editar.add( cc1.getText() );
                                            }
                                            else if( f_radios_ou_checks_ou_select == 2 )
                                            {
                                                JCheckBox cc1 = (JCheckBox) cc[i];
                                                titulos_editar.add( cc1.getText() );
                                            }
                                            else if( f_radios_ou_checks_ou_select == 3 )
                                            {
                                                JComboBox cc1 = (JComboBox) cc[i];
                                                for (int j = 0; j < cc1.getItemCount(); j++) {
                                                    String opcao_select = cc1.getItemAt(j).toString();
                                                    titulos_editar.add( opcao_select );                                             
                                                }
                                            }
                                        }
                                        
                                        SwingUtilities.invokeLater(new Runnable()
                                        {
                                            @Override
                                            public void run(){
                                                String[] titulos_editar__ =  titulos_editar.toArray(new String[]{});
                                                ob = new obter_dados_lista_criada( titulos_editar__, titulos_editar__.length );
                                                thre = new Thread(new Runnable()
                                                {
                                                    @Override
                                                    public void run() {
                                                        while( (!Thread.currentThread().isInterrupted()) )
                                                        {
                                                            if( ob != null )
                                                            {
                                                                try {
                                                                        String[] itens_da_lista = ob.obter_itens_da_lista();
                                                                        if( itens_da_lista.length > 0 )
                                                                        {
                                                                            get.setText( itens_da_lista[0] );

                                                                            for (int i = 0; i < cc.length; i++) 
                                                                            {
                                                                                String itens_da_lista1 = itens_da_lista[i+1];
                                                                                
                                                                                if( f_radios_ou_checks_ou_select == 1 )
                                                                                {
                                                                                    JRadioButton cc1 = (JRadioButton) cc[i];
                                                                                    cc1.setText(itens_da_lista1);
                                                                                }
                                                                                else if( f_radios_ou_checks_ou_select == 2 )
                                                                                {
                                                                                    JCheckBox cc1 = (JCheckBox) cc[i];
                                                                                    cc1.setText(itens_da_lista1);
                                                                                }
                                                                                else if( f_radios_ou_checks_ou_select == 3 )
                                                                                {
                                                                                    JComboBox cc1 = (JComboBox) cc[i];
                                                                                    cc1.removeAllItems();
                                                                                    for (int j = 1; j < itens_da_lista.length; j++) {
                                                                                        String get1 = itens_da_lista[j];
                                                                                        cc1.addItem( get1 );
                                                                                    }
                                                                                }
                                                                             }

                                                                            pinterno.setBorder(null);//<= retirar borda;
//                                                                            operacoes_painel.atualizar_painel(pinterno);
                                                                        }
                                                                    } catch (InterruptedException ex) {
                                                                        Logger.getLogger(editar_formulario.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                                thre.interrupt();
                                                            }
                                                        }
                                                    }
                                                }
                                                );
                                                thre.start();
                                            }
                                        });
                                    }
//                                    operacoes_painel.exibir_names_em_painel(pr);
                                }
                                
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
                        }
                    });

                
            }
        }
    }
    
}

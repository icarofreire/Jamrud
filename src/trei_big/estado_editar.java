/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import java.util.ArrayList;

/**
 *
 * @author icaro
 */
public class estado_editar {
    
    public ArrayList<Integer> linhas_modificadas = new ArrayList<Integer>();

    public void setLinha_modificada(int linha_modificada) {
        linhas_modificadas.add(linha_modificada);
    }
    
    /* Se a linha inserida está registrada como uma linha modificada/editada; */
    public boolean se_linha_esta_na_lista(int linha) {
        return this.linhas_modificadas.contains(linha);
    }
    
    public void apagar_linhas() {
        this.linhas_modificadas.clear();
    }
    
    /* apagar ultimo registro inserido; */
    public void apagar_ultimo() {
        this.linhas_modificadas.remove(this.linhas_modificadas.size()-1);
    }
    
}

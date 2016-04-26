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
    
    /* \/\/ linhas modificadas nos resultados de uma busca; */
    public ArrayList<Integer> linhas_modificadas_busca = new ArrayList<Integer>();

    public void setLinhas_modificadas_busca(int linha_modificada) {
        this.linhas_modificadas_busca.add(linha_modificada);
    }

    public void setLinha_modificada(int linha_modificada) {
        this.linhas_modificadas.add(linha_modificada);
    }
    
    /* Se a linha inserida está registrada como uma linha modificada/editada; */
    public boolean se_linha_esta_na_lista(int linha) {
        if( linhas_modificadas_busca.isEmpty() ){
            return this.linhas_modificadas.contains(linha);
        }else{
            return this.linhas_modificadas_busca.contains(linha);
        }
    }
    
    public void apagar_linhas() {
        this.linhas_modificadas.clear();
    }
    
    public void apagar_linhas_busca() {
        this.linhas_modificadas_busca.clear();
    }
    
    /* apagar ultimo registro inserido; */
    public void apagar_ultimo() {
        this.linhas_modificadas.remove(this.linhas_modificadas.size()-1);
    }
    
}

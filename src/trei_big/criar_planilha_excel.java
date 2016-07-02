/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trei_big;

import banco.banco;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author icaro
 */
public class criar_planilha_excel {
        
    private final String extensao = ".xlsx";

    public criar_planilha_excel(String caminho_salvar, String nome_da_tabela) {
        
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet(nome_da_tabela);
          
        //This data needs to be written (Object[])
        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
          
        Vector<Vector<String>> dados = banco.obter_dados_da_tabela(nome_da_tabela);
        
        data.put( 0 , banco.nome_colunas_consulta.toArray(new Object[]{}) );
        
        for (int i = 0; i < dados.size(); i++) {
            Vector<String> get = dados.get(i);
            data.put( (i+1) , get.toArray(new Object[]{}) );
        }
        
        //Iterate over data and write to sheet
        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String){
                    cell.setCellValue((String)obj);
               }else if(obj instanceof Integer){
                    cell.setCellValue((Integer)obj);
               }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(caminho_salvar, nome_da_tabela + extensao ));
            workbook.write(out);
            out.close();
            aviso.mensagem_sucesso("Planilha '"+nome_da_tabela + extensao + "' criada com sucesso!");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }

}

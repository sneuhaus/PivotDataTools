/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelidentifier;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sbn
 */
public class FileFinder {
    
    static ArrayList<File> files = new ArrayList<>();
    static File startFile = new File("C://PIVOTData/");  
    
    public static void main(String[] args){
        
      try{
          getFiles(startFile);
          
        for(File file : files){
            System.out.print(file.getName());
            FileInputStream fis = new FileInputStream(file);

            Workbook myWorkBook = new XSSFWorkbook(fis);


            Sheet mySheet = myWorkBook.getSheetAt(0);
            
          
                Row row = mySheet.getRow(0);
              for (int i = 1; i < row.getLastCellNum(); i++) {
               System.out.print("\t"+row.getCell(i).getStringCellValue());

            }
              System.out.println();
        }
      }catch(Exception e ){
          e.printStackTrace();
      }
    }
    
    public static void getFiles(File file){
        if(file.isDirectory() ){
            for(File newFile : file.listFiles()){
                getFiles(newFile);
            }
        }else{
            if(file.getName().contains("xlsx") && file.getName().contains("Extracted") && !file.getName().contains("Weight")){
                files.add(file);
            }
        }
        
    }
    
}

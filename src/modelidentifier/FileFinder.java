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
    static File startFile = new File("C://PIVOTData/AllFilesFromRTI/Files/Sarcoma_Renal_Houghton");  
    
    public static void main(String[] args){
        
      try{
          getFiles(startFile);
          
        for(File file : files){
            System.out.println(file.getName());
            FileInputStream fis = new FileInputStream(file);

            Workbook myWorkBook = new XSSFWorkbook(fis);


            Sheet mySheet = myWorkBook.getSheetAt(0);
            
            for (int i = 1; i < mySheet.getLastRowNum(); i++) {
                Row row = mySheet.getRow(i);
            
         //       System.out.println("\t"+row.getCell(5).getStringCellValue());

            }
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
            if(file.getName().contains("xlsx")){
                files.add(file);
            }
        }
        
    }
    
}

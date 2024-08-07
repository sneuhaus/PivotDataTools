
package modelidentifier;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sbn
 */
public class ModelIdentifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            String[] centers = {"CHOP", "UTHSCSA", "MDA","Lurie"};

            File studies = new File("C://PIVOTData");

            for (String center : centers) {
                HashMap<String, String> models = new HashMap<>();

                for (File study : studies.listFiles()) {
                    if (study.isDirectory()) {
                        //     System.out.println("checking study "+study.getName()+" for "+center+" data");
                        try {
                            File extractedDir = null;
                            try {
                                extractedDir = new File(study.getAbsoluteFile() + "/" + center + "/extracted");
                            } catch (Exception e) {

                            }
                            if (extractedDir != null && extractedDir.listFiles() != null) {
                                for (File data : extractedDir.listFiles()) {

                                    if (data.getAbsolutePath().contains("xlsx")) {
                                        //           System.out.println("found extracted file "+data.getName());

                                        FileInputStream fis = new FileInputStream(data);

                                        Workbook myWorkBook = new XSSFWorkbook(fis);

                                        
                                        // what about the other sheeets 
                                        Sheet mySheet = myWorkBook.getSheetAt(0);
                                        int rowIndex = 1;
                                        
                                        while (mySheet.getRow(rowIndex) != null) {
                                            //                   System.out.println(data.getAbsolutePath()+" \t"+mySheet.getRow(rowIndex).getCell(4).getStringCellValue()+" \t"+mySheet.getRow(rowIndex).getCell(17).getStringCellValue());
                                            String model ="";
                                            try{
                                                if("Lurie".equals(center)){
                                                    model = mySheet.getRow(rowIndex).getCell(6).getStringCellValue();
                                                }
                                                else{
                                                    model = mySheet.getRow(rowIndex).getCell(4).getStringCellValue();
                                                }
                                                if("UTHSCSA".equals(center)){
                                             //       model = model.replace(",", " ").split(" ")[0];
                                                }
                                            }catch(Exception e){}
                                            models.put(model,data.getName()+" "+model);
                                            rowIndex++;
                                        }
                                        //                System.out.println("found "+rowIndex+" rows and "+models.size()+" total models");

                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                System.out.println(center);
                
                String[] values =models.values().toArray(new String[models.size()]);
               
                Arrays.sort(values);
                
                
                for (String model : values) {
                    System.out.println("\t" + model);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

  
}

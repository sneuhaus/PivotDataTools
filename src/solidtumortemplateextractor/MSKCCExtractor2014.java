/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.




 */
package solidtumortemplateextractor;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Initially just for 2008 as this is the only example data set from MSKCC
 * @author sbn
 */
public class MSKCCExtractor2014 {

    static final String NO_VALUE = "NO VALUE";
    static final String NO_DATE_VALUE = "";//"NO DATE";
    static final String NPE = "";

    static final SimpleDateFormat sdf = new SimpleDateFormat();
    static final String DATE_PATTERN = "MM/dd/yyyy";
    static Date BEFORE, AFTER;
    static String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G","H"};
    static final String BEFORE_DATE = null;
    static final String AFTER_DATE = "01/01/2015";
    static final int TIME_POINTS = 25;
    
    // schedule is days 0-28, no actual dates were provided by MSKCC
    static String treatmentStartDate = "04/01/2024";
    static String treatmentEndDate = "04/29/2024";
    
    static int offset = 2;
    
    static String fileName ="";
    
    static final String[] columnNames = {"record_id", "record_description", "st_template_version", 
        "st_panel_code", "dose", "schedule", "st_passage", "transplant_date", 
        "treatment_date", "treatment_end_date", "technician", "study_end_date", "group",
        "dataset", "dataset_date", "day", "mouse","tumor","subtype","st_passage","volume", "diameter_x", "diameter_y", 
        "unexpect_event", "event_comment", "reason", "status", "weight",
        "cage_a_wt", "cage_b_wt", "solid_tumor_data_complete", "record_complete", "redcap_event_name"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        sdf.applyLocalizedPattern(DATE_PATTERN);
        StringBuilder output = new StringBuilder();
        try {

            BEFORE = Calendar.getInstance().getTime();
            AFTER = sdf.parse(AFTER_DATE);

      
              File myFile = new File("C://PIVOTData/2014/MSKCC/");  
        
   
            for(String column : columnNames){
                System.out.print(column+"\t");
                output.append(column+"\t");
            }
            System.out.println();
            output.append("\n");

            for (File file : myFile.listFiles()) {
                if (file.getName().contains(".xlsx")) {
                    fileName = file.getName();

                    FileInputStream fis = new FileInputStream(file);
                  
                    Workbook myWorkBook = new XSSFWorkbook(fis);
               //             System.out.println("Stats for file " + file.getName());
               //     int sheets = getWorkBookStats(myWorkBook);
               
                    for (int s = 1; s < 5; s++) {
                        Sheet mySheet = myWorkBook.getSheetAt(s);


                            printSheetData(mySheet);
                        }
                    
                    fis.close();
                }
            }
        } catch (Exception e) {
            System.out.println(fileName);
            e.printStackTrace();
        }
       
        
    }

    public static void printSheetData(Sheet sheet) {
        
        String model = getValue(sheet.getRow(0).getCell(0));
        String subtype = getValue(sheet.getRow(0).getCell(2));
        int id = 1;
        for(int treatment = 0; treatment < 2; treatment++){
            for(int mouse = 1; mouse < 4; mouse++){
                int measurement =3;
                String volume = getValue(sheet.getRow(measurement).getCell(mouse+(treatment*3)));
                while(!volume.isBlank()){
                    String day = ((int) sheet.getRow(measurement).getCell(0).getNumericCellValue())+"";
                    
                   
                    String group = "A";
                    String dose= "D5W";
                    String schedule = "Q4D x 4 weeks";
                    if(treatment == 1){
                        
                        group = "B";
                        dose= "Gedatolisib 10 mg/kg";
                        schedule = "Q4D x 4 weeks";
                        
                    }
                    String timepoint = "Timepoint "+(measurement -2);
                    System.out.print(fileName+"-"+model+"-"+id+++"\t");
                    System.out.print("Data-Solid Tumor\tMSKCC");
                    System.out.print("\tMSKCC 2014\t");
                    System.out.print(dose+"\t"+schedule+"\t\t\t");
                    System.out.print(treatmentStartDate+"\t"+treatmentEndDate+"\t\t\t");
                    System.out.print(group+"\t"+timepoint+"\t\t"+day+"\t"+group+mouse+"-"+model.split("-")[2]+"\t");
                    System.out.print(model+"\t"+subtype+"\t\t"+volume+"\t");
                    
                    
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t");
                    measurement++;
                    try{
                     volume = getValue(sheet.getRow(measurement).getCell(mouse+(treatment*3)));
                    }catch(NullPointerException npe){
                        volume ="";
                    }
                }
            }
        }
    }

        
        
       

    private static String getValue(Cell cell) {
        String val = NPE;
        try{
            val = getValue(cell, cell.getCellType());
        }catch(NullPointerException npe){
     //       npe.printStackTrace();
        
        }

        return val.trim().replace("\"", "").replace("#", "");
    }

    private static String getValue(Cell cell, CellType type) {

        switch (type) {
            case NUMERIC:
                return cell.getNumericCellValue() + "";
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return getValue(cell, cell.getCachedFormulaResultType());
            default:
                return "";
        }

    }

   

    

}

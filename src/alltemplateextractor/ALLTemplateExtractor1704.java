/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alltemplateextractor;

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
 *
 * @author sbn
 */
public class ALLTemplateExtractor1704 {

    static final String NO_VALUE = "";

    static final SimpleDateFormat sdfIn = new SimpleDateFormat();
    static final String INPUT_DATE_PATTERN = "dd/MM/yyyy";
    
    static final SimpleDateFormat sdfOut = new SimpleDateFormat();
    static final String OUTPUT_DATE_PATTERN = "MM/dd/yyyy";
    
    static Date BEFORE, AFTER;

    static final String BEFORE_DATE = "01/01/2025";
    static final String AFTER_DATE = "01/01/2015";

    static final String NULL_CELL = "null cell";

    static final String[] columnNames = {"record_id", "record_description", "panel_code", "testing", 
        "all_dose", "all_schedule", "date_of_innoculation", "date_of_first_treatment",
        "date_of_treatment_completion", "date_of_randomization", "description", 
        "day_0", "arm", "arm_testing", "all_mouse", "date_of_bleed", 
        "cd45", "code", "footnote", "death_date", "n", "summary_thydym", 
        "summary_toxic", "summary_evaluable", "all_data_complete", "redcap_event_name"};
    
    static String fileName ="";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        sdfIn.applyLocalizedPattern(INPUT_DATE_PATTERN);
        sdfOut.applyLocalizedPattern(OUTPUT_DATE_PATTERN);
        try {

            BEFORE = sdfOut.parse(BEFORE_DATE);
            AFTER = sdfOut.parse(AFTER_DATE);

            File myFile = new File("C://PIVOTData/1704/CCIA");
            //         File myFile = new File("C://1816/raw");

            for (String column : columnNames) {
                System.out.print(column + "\t");
            }
            System.out.println();

            for (File file : myFile.listFiles()) {
                if (file.getName().contains(".xlsx")) {

                    FileInputStream fis = new FileInputStream(file);
                    fileName  = file.getName();

                    Workbook workBook = new XSSFWorkbook(fis);
                    //        System.out.println("Stats for file " + file.getName());

                  getWorkBookData(workBook);
                  System.out.println(fileName);
                  Sheet sheet = workBook.getSheetAt(0);
                
                  
                  

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    private static String getValue(Cell cell) {
        try {
            return getValue(cell, cell.getCellType());
        } catch (NullPointerException npe) {
            return NULL_CELL;
        }
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

    private static String getDateValue(Cell cell) {
        String dateStr = NO_VALUE;
        if (cell == null || CellType.BLANK.equals(cell.getCellType())) {
            return dateStr;
        }
        // CellType.BLANK

        try {

            Calendar c = Calendar.getInstance();
            c.setTime(cell.getDateCellValue());
            
       //     if(c.before(BEFORE) && c.after(AFTER)){

                dateStr =  sdfOut.format(cell.getDateCellValue());
                return dateStr;
      //      }else{
      //          System.out.println(cell.getDateCellValue()+" is wonky 1");
      //      }
        } catch (Exception e) {
        }
        try {
            Date d = sdfIn.parse(getValue(cell));
            
            
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            
            if(c.before(BEFORE) && c.after(AFTER)){
            
            
             return sdfOut.format(d);
            }else{
                System.out.println(sdfOut.format(d)+" is wonky 2");
            }
        } catch (Exception e) {
        }

        return dateStr;
    }

    private static void getWorkBookData(Workbook workBook) {

        Sheet sheet = workBook.getSheetAt(0);
        String panelCode = getValue(sheet.getRow(0).getCell(1));
        String testing = getValue(sheet.getRow(1).getCell(1));
        String model = getValue(sheet.getRow(2).getCell(1));
        String inoculationDate = getDateValue(sheet.getRow(3).getCell(1));
        String firstTreatmentDate = getDateValue(sheet.getRow(4).getCell(1));
        String treatmentCompletion = getValue(sheet.getRow(5).getCell(1));
        String dayZero = getValue(sheet.getRow(5).getCell(7));
        String randomizationDate = getValue(sheet.getRow(6).getCell(1));
        
       

        int rowCount = 1;
       
            int row = 9;

            String armTesting = getValue(sheet.getRow(row).getCell(1));
            if (!NULL_CELL.equals(armTesting) && !armTesting.isBlank()) {
                String arm = getValue(sheet.getRow(row).getCell(0));
                
                String dose = getValue(sheet.getRow(row + 1).getCell(1));
                String schedule1 = getValue(sheet.getRow(row + 2).getCell(1));
                String schedule2 = getValue(sheet.getRow(row + 3).getCell(1));
                String n = getValue(sheet.getRow(row + 4).getCell(1));
                String thyLym = getValue(sheet.getRow(row + 6).getCell(1));
                String toxic = getValue(sheet.getRow(row + 7).getCell(1));
                String evaluable = getValue(sheet.getRow(row + 8).getCell(1));

                String subtype = "";
                for (int mouse = 9; mouse < 50; mouse++) {
                    
                      
                    String nextSubtype = getValue(sheet.getRow(mouse).getCell(2));
                    if(!nextSubtype.isBlank() && !subtype.equals(nextSubtype)){
                         subtype= nextSubtype;
                    }
                     
                 
                    row = 0;
                    String bleedDate, cd45;
                    dayZero = getDateValue(sheet.getRow(row + mouse).getCell(3));
                    String mouseName = subtype +" "+getValue(sheet.getRow(row + mouse).getCell(4))+" "+getValue(sheet.getRow(row + mouse).getCell(5));
                    String codes = getValue(sheet.getRow(row + mouse).getCell(61));
                    String footNote = getValue(sheet.getRow(row + mouse).getCell(62));
                    String deathDate = getDateValue(sheet.getRow(row + mouse).getCell(63));

                    if (mouseName != null && !mouseName.isBlank()) {
                        loop:
                        for (int i = 6; i < 61; i++) {
                            bleedDate = getDateValue(sheet.getRow(7).getCell(i));
                            cd45 = getValue(sheet.getRow(row + mouse).getCell(i));
                            try {
                                
                                StringBuilder dataRow = new StringBuilder();
                                
                                // this will fail if there is no value so the line is skipped
                                // which is the desired behaviour
                                double cd45value = Double.parseDouble(cd45);
                
                                dataRow.append(fileName+"-"+rowCount++).append("\t");
                                dataRow.append("Data-ALL").append("\t");
                                dataRow.append(panelCode).append("\t");
                                dataRow.append(testing).append("\t");
                                dataRow.append(dose).append("\t");
                                dataRow.append(schedule1).append("\t");
                                dataRow.append(inoculationDate).append("\t");
                                dataRow.append(firstTreatmentDate).append("\t");
                                dataRow.append(treatmentCompletion).append("\t");
                                dataRow.append(randomizationDate).append("\t");
                                dataRow.append("").append("\t");
                                dataRow.append(dayZero).append("\t");
                                dataRow.append(arm).append("\t");
                                dataRow.append(armTesting).append("\t");
                                dataRow.append(mouseName).append("\t");
                                dataRow.append(bleedDate).append("\t");
                                dataRow.append(cd45).append("\t");
                                dataRow.append(codes).append("\t");
                                dataRow.append(footNote).append("\t");
                                dataRow.append(deathDate).append("\t");
                                dataRow.append(n).append("\t");
                                dataRow.append(thyLym).append("\t");
                                dataRow.append(toxic).append("\t");
                                dataRow.append(evaluable).append("\t");
                                dataRow.append("all compelete").append("\t");
                                dataRow.append("redcap");
                                           
                                System.out.println(dataRow.toString());
                                
                            } catch (Exception e) {
                            }
                        }

                    }

                }
               

        }

    }

}

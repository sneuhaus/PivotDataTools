/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alltemplateextractor;

import static alltemplateextractor.ALLTemplateExtractor1704.NO_VALUE;
import static alltemplateextractor.ALLTemplateExtractor1704.sdfIn;
import static alltemplateextractor.ALLTemplateExtractor1704.sdfOut;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
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
public class ALLTemplateExtractorWeight1819 {

    static final String NO_VALUE = "";

    static final SimpleDateFormat sdfIn = new SimpleDateFormat();
    static final String INPUT_DATE_PATTERN = "dd/MM/yyyy";
    
    static final SimpleDateFormat sdfOut = new SimpleDateFormat();
    static final String OUTPUT_DATE_PATTERN = "MM/dd/yyyy";
    
    static Date BEFORE, AFTER;

    static final String BEFORE_DATE = "01/01/2022";
    static final String AFTER_DATE = "01/01/2015";

    static final String NULL_CELL = "null cell";

    static final String[] columnNames = {"record_id", "record_description","associated_all_project", "panel_code", "testing", 
        "all_dose", "all_schedule", "date_of_innoculation", "date_of_first_treatment",
        "date_of_treatment_completion", "date_of_randomization", "description", 
        "day_0", "arm", "arm_testing", "all_mouse", "date_of_weight", 
        "all_weight", "code", "footnote", "death_date", "n", "summary_thydym", 
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

            File myFile = new File("C://PIVOTData/1819/CCIA");
           
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

            dateStr =  sdfOut.format(cell.getDateCellValue());
            return dateStr;
            
        } catch (Exception e) {
        }
        try {
            Date d = sdfIn.parse(getValue(cell));
            return sdfOut.format(d);
        } catch (Exception e) {
        }

        return dateStr;
    }

    private static void getWorkBookData(Workbook workBook) {

        Sheet sheet = workBook.getSheetAt(1);
        String panelCode = getValue(sheet.getRow(0).getCell(1));
        String testing = "A";//getValue(sheet.getRow(1).getCell(1));
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
                
                String n = "";
                String thyLym = "";
                String toxic = "";
                String evaluable = "";

                String subtype = "";
                for (int mouse = 9; mouse < 40; mouse++) {
                     
                 
                    row = 0;
                    String weighDate, weight;
                  
                    String mouseName = getValue(sheet.getRow(row + mouse).getCell(3))+" "+getValue(sheet.getRow(row + mouse).getCell(4));
                    String codes = "";
                    String footNote = "";
                    String deathDate = "";
                    dayZero = getDateValue(sheet.getRow(row + mouse).getCell(2));

                    if (mouseName != null && !mouseName.isBlank()) {
                        loop:
                        for (int i = 5; i < 67; i++) {
                            
                            
                            
                            weighDate = getDateValue(sheet.getRow(7).getCell(i));
                            weight = getValue(sheet.getRow(row + mouse).getCell(i));
                            try {
                                
                                StringBuilder dataRow = new StringBuilder();
                                
                                // this will fail if there is no value so the line is skipped
                                // which is the desired behaviour
                                double cd45value = Double.parseDouble(weight);
                
                                dataRow.append(fileName+"-"+rowCount++).append("\t");
                                dataRow.append("Data-ALL").append("\t");
                                dataRow.append("associated project").append("\t");
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
                                dataRow.append(weighDate).append("\t");
                                dataRow.append(weight).append("\t");
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

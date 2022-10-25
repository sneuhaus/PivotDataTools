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
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * !!!!!!!!!!!Format Column O/P in excel as 'text' otherwise mouse names become dates!!!!!!!!!!!!!!
 * 
 
 * 
 * @author sbn
 */
public class ALLTemplateWeightExtractorEndDate {

    static final String NO_VALUE = "";

    static final SimpleDateFormat sdfIn = new SimpleDateFormat();
    static final String INPUT_DATE_PATTERN = "dd/MM/yyyy";
    
    static final SimpleDateFormat sdfOut = new SimpleDateFormat();
    static final String OUTPUT_DATE_PATTERN = "MM/dd/yyyy";
    
    static Date BEFORE, AFTER;

  
    static final String AFTER_DATE = "01/01/2015";

    static final String NULL_CELL = "null cell";

    static final String[] columnNames = {"record_id", "record_description","associated_all_project", "panel_code",
        "tumor", "tumor_category", "testing", 
        "all_dose", "all_schedule", "date_of_innoculation", "date_of_first_treatment",
        "date_of_treatment_completion", "date_of_randomization", "description", 
        "day_0", "arm", "arm_testing", "all_mouse", "date_of_weight", 
        "all_weight", "code", "footnote", "death_date", "n", "summary_thydym", 
        "summary_toxic", "summary_evaluable", "all_data_complete", "redcap_event_name"};
    
    static String fileName ="";
    
    static HashMap<String,String> fileColumns = new HashMap<>();
    static HashMap<String,String> fileMiceRows = new HashMap<>();
    
    static StringBuilder messages = new StringBuilder();

    // end of row with date of weights indicated how many timepoints there are
  
    static int endColumn = 3;  // first date of bleeds
    static int dateOfWeightsRow = 11; // row with date of bleeds
    static int miceRow = 13;  // first row of repeating mouse data should be "Control(A)"


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        sdfIn.applyLocalizedPattern(INPUT_DATE_PATTERN);
        sdfOut.applyLocalizedPattern(OUTPUT_DATE_PATTERN);
        try {

            BEFORE = Calendar.getInstance().getTime();
            AFTER = sdfOut.parse(AFTER_DATE);

            File myFile = new File("C://PIVOTData/2002/CCIA");
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


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
         for (String file : fileColumns.keySet()) {
            messages.append("\n").append(file + " has " + fileColumns.get(file) + " columns and " + fileMiceRows.get(file) + " treatment rows.");
        }

        System.out.println(messages.toString());
        
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
        String model = getValue(sheet.getRow(2).getCell(1));
        String subType = TumorToSubtype.getSubType(model);
        String testing = getValue(sheet.getRow(1).getCell(1));
        String inoculationDate = getDateValue(sheet.getRow(3).getCell(1));
         String firstTreatmentDateA = getDateValue(sheet.getRow(4).getCell(1));
        String treatmentCompletionA = getDateValue(sheet.getRow(5).getCell(1));
        String firstTreatmentDateB = getDateValue(sheet.getRow(6).getCell(1));
        String treatmentCompletionB = getDateValue(sheet.getRow(7).getCell(1));
        String firstTreatmentDateC = getDateValue(sheet.getRow(8).getCell(1));
        String treatmentCompletionC = getDateValue(sheet.getRow(9).getCell(1));
    //    String dayZero = getValue(sheet.getRow(5).getCell(4));
        String randomizationDate = getDateValue(sheet.getRow(10).getCell(1));
        
        
        
        String comment = "";
      
        while(!"Comment".equals(comment)){
            comment = getValue(sheet.getRow(dateOfWeightsRow+1).getCell(endColumn++));
            
        }
        fileColumns.put(fileName,endColumn+"");
        
        int treatmentGroupRows= miceRow;
        String treatment = getValue(sheet.getRow(treatmentGroupRows++).getCell(0));
        if(treatment.toLowerCase().startsWith(("control"))){
            while(! treatment.toLowerCase().startsWith("treatment")){
                treatment = getValue(sheet.getRow(treatmentGroupRows++).getCell(0));
            }
        }else{
             messages.append("\n").append(fileName + " Header is not in normal format Control(A) is not on row " + miceRow);
        }
    
        treatmentGroupRows = treatmentGroupRows - miceRow -1; // this is terrible what is happening!
        // verify
        int treatmentGroups = 0;
        for(int i = miceRow + treatmentGroupRows; i < 80; i = i + treatmentGroupRows){
             treatment = getValue(sheet.getRow(i).getCell(0));
            if (!treatment.toLowerCase().startsWith("treatment")) {
                messages.append("\n").append(fileName + " Inconsistant mice rows expecting treatment at row " + i + " found " + treatment);
            }
            if (getValue(sheet.getRow(i).getCell(1)) != null) {
                treatmentGroups++;
            }
        }
        fileMiceRows.put(fileName,treatmentGroups+"");

        int rowCount = 1;
        boolean moreTreatments = true;
        int index = 0;
        while (moreTreatments) {

             int row = miceRow + (index * treatmentGroupRows);

            String armTesting = getValue(sheet.getRow(row).getCell(1));
            if (!NULL_CELL.equals(armTesting) && !armTesting.isBlank()) {
                String arm = getValue(sheet.getRow(row).getCell(0));
                try{
                    arm = arm.split("\\(")[1].replace(")","");
                }catch(Exception e){
                     messages.append("\n").append(fileName + " can't fix treatment arm to single letter group for " + arm + " at row " + row);
                }
                
                String firstTreatmentDate = "";
                String treatmentCompletion = "";
                if ("A".equals(arm)) {
                    firstTreatmentDate = firstTreatmentDateA;
                    treatmentCompletion = treatmentCompletionA;
                }

                if ("B".equals(arm)) {
                    firstTreatmentDate = firstTreatmentDateB;
                    treatmentCompletion = treatmentCompletionB;
                }

                if ("C".equals(arm)) {
                    firstTreatmentDate = firstTreatmentDateC;
                    treatmentCompletion = treatmentCompletionC;
                }
                
                
                String dose = getValue(sheet.getRow(row + 1).getCell(1));
                String schedule = getValue(sheet.getRow(row + 2).getCell(1))+" "+ getValue(sheet.getRow(row + 3).getCell(1));
                String n = getValue(sheet.getRow(row + 4).getCell(1));
                String thyLym = getValue(sheet.getRow(row + 6).getCell(1));
                String toxic = getValue(sheet.getRow(row + 7).getCell(1));
                String evaluable = getValue(sheet.getRow(row + 8).getCell(1));

                 
                
                for (int mouse = 0; mouse < treatmentGroupRows; mouse++) {

                    String weighDate, weight;
                    String mouseName = getValue(sheet.getRow(row + mouse).getCell(2));
                    comment = getValue(sheet.getRow(row+mouse).getCell(endColumn-1));

                  
                    
                    if (mouseName != null && !mouseName.isBlank()) {
                        loop:
                        for (int i = 3; i < endColumn; i++) {
                            weighDate = getDateValue(sheet.getRow(dateOfWeightsRow).getCell(i));
                            weight = getValue(sheet.getRow(row + mouse).getCell(i));
                            try {
                                
                                StringBuilder dataRow = new StringBuilder();
                                double weightValue = Double.parseDouble(weight);
                
                                dataRow.append(fileName+"-"+rowCount++).append("\t");
                                dataRow.append("Data-ALL").append("\t");
                                dataRow.append("associated project").append("\t");
                                dataRow.append(panelCode).append("\t");
                                dataRow.append(model).append("\t");
                                dataRow.append(subType).append("\t");
                                dataRow.append(testing).append("\t");
                                dataRow.append(dose).append("\t");
                                dataRow.append(schedule).append("\t");
                                dataRow.append(inoculationDate).append("\t");
                                dataRow.append(firstTreatmentDate).append("\t");
                                dataRow.append(treatmentCompletion).append("\t");
                                dataRow.append(randomizationDate).append("\t");
                                dataRow.append("").append("\t");
                                dataRow.append("").append("\t");
                                dataRow.append(arm).append("\t");
                                dataRow.append(armTesting).append("\t");
                                dataRow.append(mouseName).append("\t");
                                dataRow.append(weighDate).append("\t");
                                dataRow.append(weight).append("\t");
                                dataRow.append("").append("\t");
                                dataRow.append(comment).append("\t");
                                
                                // not sure these are ever used
                                dataRow.append("").append("\t");
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
                index++;
            } else {
                moreTreatments = false;
            }

        }

    }

}

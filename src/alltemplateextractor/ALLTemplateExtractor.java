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
import modelidentifier.IDChecker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sbn
 */
public class ALLTemplateExtractor {

    static final String NO_VALUE = "";

    static final SimpleDateFormat sdfIn = new SimpleDateFormat();
    static final String INPUT_DATE_PATTERN = "dd/MM/yyyy";
    
    static final SimpleDateFormat sdfOut = new SimpleDateFormat();
    static final String OUTPUT_DATE_PATTERN = "MM/dd/yyyy";
    
    static Date BEFORE, AFTER;

   
    static final String AFTER_DATE = "01/01/2015";

    static final String NULL_CELL = "null cell";

    static final String[] columnNames = {"record_id", "record_description", "panel_code","model","subtype",
        "testing", "all_dose", "all_schedule", "date_of_innoculation", "date_of_first_treatment",
        "date_of_treatment_completion", "date_of_randomization", "description", 
        "day_0", "arm", "arm_testing", "all_mouse", "date_of_bleed", 
        "cd45", "code", "footnote", "death_date", "n", "summary_thydym", 
        "summary_toxic", "summary_evaluable", "all_data_complete", "redcap_event_name"};
    
    static String fileName ="";
    static HashMap<String,String> fileColumns = new HashMap<>();
    static HashMap<String,String> fileMiceRows = new HashMap<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        sdfIn.applyLocalizedPattern(INPUT_DATE_PATTERN);
        sdfOut.applyLocalizedPattern(OUTPUT_DATE_PATTERN);
        try {

            BEFORE = Calendar.getInstance().getTime();
            AFTER = sdfOut.parse(AFTER_DATE);

            File myFile = new File("C://PIVOTData/1704/CCIA/");
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
        
        for(String file : fileColumns.keySet()){
            System.out.println(file+" has "+fileColumns.get(file)+" columns and "+fileMiceRows.get(file)+" mice rows.");
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

        Sheet sheet = workBook.getSheetAt(0);
        String panelCode = getValue(sheet.getRow(0).getCell(1));
        String testing = getValue(sheet.getRow(1).getCell(1));
        String model = getValue(sheet.getRow(2).getCell(1));
        String subType = IDChecker.getSubtype(model);
        String inoculationDate = getDateValue(sheet.getRow(3).getCell(1));
        String firstTreatmentDate = getDateValue(sheet.getRow(4).getCell(1));
        String treatmentCompletion = getDateValue(sheet.getRow(5).getCell(1));
        String dayZero = getValue(sheet.getRow(8).getCell(4));
        String randomizationDate = getDateValue(sheet.getRow(6).getCell(1));
        
        
        // find out how many columns by looking for header Death Date
        String deathDate = "";
        int endColumn = 3;
        while(!"Death Date".equals(deathDate)){
            deathDate = getValue(sheet.getRow(11).getCell(endColumn++));
            
        }
        fileColumns.put(fileName,endColumn+"");
        int miceRows = 13;
        String treatment = getValue(sheet.getRow(miceRows++).getCell(0));
        if(treatment.toLowerCase().startsWith(("control"))){
            while(! treatment.toLowerCase().startsWith("treatment")){
                treatment = getValue(sheet.getRow(miceRows++).getCell(0));
            }
        }else{
            System.out.println("Header is not in normal format Control(A) is not on row 10");
        }
    
        miceRows = 11;
        // verify
        for(int i = 13; i < 80; i = i + miceRows){
            treatment = getValue(sheet.getRow(i).getCell(0));
            if(!treatment.toLowerCase().startsWith("treatment") || !treatment.toLowerCase().startsWith("control")){
          //      System.out.println("inconsistant mice rows expecting treatment at row "+i+" found "+treatment);
            }
        }
        fileMiceRows.put(fileName,miceRows+"");
        
        

        int rowCount = 1;
        boolean moreTreatments = true;
        int index = 0;
        while (moreTreatments) {

            // 9 should be static as the header fields don't change
            // 11 here and 12 on line 188 are related and should be able to be calculated by 
            // inspecting the format of the spreadsheet
            // they vary across studies
            int row = 13 + (index * miceRows);

            String armTesting = getValue(sheet.getRow(row).getCell(1));
            if (!NULL_CELL.equals(armTesting) && !armTesting.isBlank()) {
                String arm = getValue(sheet.getRow(row).getCell(0));
                try{
                    arm = arm.split("\\(")[1].replace(")","");
                }catch(Exception e){
                    System.out.println("can't fix treatment arm to single letter group for "+arm+" at row "+row+ " in file "+fileName);
                    
                }
                
                String dose = getValue(sheet.getRow(row + 1).getCell(1));
                String schedule1 = getValue(sheet.getRow(row + 2).getCell(1));
                String schedule2 = getValue(sheet.getRow(row + 3).getCell(1));
                String n = getValue(sheet.getRow(row + 4).getCell(1));
                String thyLym = getValue(sheet.getRow(row + 6).getCell(1));
                String toxic = getValue(sheet.getRow(row + 7).getCell(1));
                String evaluable = getValue(sheet.getRow(row + 8).getCell(1));

                for (int mouse = 0; mouse < miceRows; mouse++) {

                    String bleedDate, cd45;
                    String mouseName = getValue(sheet.getRow(row + mouse).getCell(2));
                    
                    
                    String codes = getValue(sheet.getRow(row + mouse).getCell(endColumn - 3));
                    String footNote = getValue(sheet.getRow(row + mouse).getCell(endColumn - 2));
                    deathDate = getDateValue(sheet.getRow(row + mouse).getCell(endColumn-1));

                    if (mouseName != null && !mouseName.isBlank()) {
                        loop:
                       
                        for (int i = 3; i < endColumn-3; i++) {
                            bleedDate = getDateValue(sheet.getRow(11).getCell(i));
                            cd45 = getValue(sheet.getRow(row + mouse).getCell(i));
                            try {
                                
                                StringBuilder dataRow = new StringBuilder();
                                double cd45value = Double.parseDouble(cd45);
                
                                dataRow.append(fileName+"-"+rowCount++).append("\t");
                                dataRow.append("Data-ALL").append("\t");
                                dataRow.append(panelCode).append("\t");
                                dataRow.append(model).append("\t");
                                dataRow.append(subType).append("\t");
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
                index++;
            } else {
                moreTreatments = false;
            }

        }

        
    }

}

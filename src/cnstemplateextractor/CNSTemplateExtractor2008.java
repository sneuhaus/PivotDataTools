/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnstemplateextractor;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

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
public class CNSTemplateExtractor2008 {

    static final String NO_VALUE = "NO VALUE";

    static final SimpleDateFormat sdf = new SimpleDateFormat();
    static final String DATE_PATTERN = "MM/dd/yyyy";
    static Date BEFORE, AFTER;

    static final String BEFORE_DATE = "01/01/2025";
    static final String AFTER_DATE = "01/01/2015";

    static final String DELIM = "\t";

//    static final String[] COLUMN_NAMES = {"CNS panel code", "Mouse #", "Strain", "Sex", "DOB", "Model ID", "passage", "Cell number", "Volume (ul)",
//        "site of injection", "date of injection", "Group", "treatment-drug", "treatment scheme","Treatment Concentration", "date_treatment started",
//        "date_treatment ended", "route of adminstration", "date ended", "outcome", "visible tumor", "Unexpected Event", "survival time (days)", "survival counted", "User"};
    
    
    // input file columns
     static final String[] COLUMN_NAMES = {"CNS panel code", "Mouse #", "Strain", "Sex", "DOB", "Model ID", "passage", "Cell number", "Volume (ul)",
        "site of injection", "date of injection", "treatment-drug", "group", "treatment scheme", "date_treatment started",
        "date_treatment ended", "route of adminstration", "date ended", "outcome", "code of survival evaluation", "Unexpected Event", "survival time (days)", "User"};

     // output file columns
    static final String[] HEADER_NAMES = {
        "record_id", "panel_code", "mouse_number", "strain", "sex", "dob",
        "cns_model_id", "passage", "cell_number", "volume", "site_of_injection",
        "date_of_injection", "cns_group", "treatment_drug", "treatment_scheme",
        "treatment_concentration",
        "date_treatment_started", "date_treatment_ended", "route_of_administration",
        "date_ended", "outcome", "visible_tumor", "unexpected_event",
        "survival_time_days", "survival_counted", "user", "cns_data_complete",
        "record_complete", "redcap_event_name", "survival_24hr"

    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

         StringBuilder data = new StringBuilder();
        sdf.applyLocalizedPattern(DATE_PATTERN);
        try {

            BEFORE = sdf.parse(BEFORE_DATE);
            AFTER = sdf.parse(AFTER_DATE);

            File myFile = new File("C://PIVOTData/2007/Lurie");

           

            for (String column : HEADER_NAMES) {
                data.append(column).append(DELIM);
            }

            data.append("\n");

            for (File file : myFile.listFiles()) {
                if (file.getName().contains(".xlsx")) {

                    FileInputStream fis = new FileInputStream(file);

                    Workbook myWorkBook = new XSSFWorkbook(fis);
                    //        System.out.println("Stats for file " + file.getName());
                    Sheet mySheet = myWorkBook.getSheetAt(0);
                    System.out.println(file.getName());
               //     System.out.println(myWorkBook.getSheetName(0));
                    String valid = validateDataSheet(mySheet);
                    
                    loop: if (valid.isBlank()) {
                        for (int i = 1; i < mySheet.getLastRowNum(); i++) {
                            Row row = mySheet.getRow(i);
                            if(row == null || row.getCell(0) == null) break loop;
                            String panelCode = getValue(row.getCell(0));
                            String mouse = (getValue(row.getCell(1)))+"";
                            data.append(panelCode).append("-").append(mouse).append(DELIM);
                            data.append(panelCode).append(DELIM);
                            data.append(mouse).append(DELIM);
                            data.append(getValue(row.getCell(2))).append(DELIM);  //strain
                            data.append(getValue(row.getCell(3))).append(DELIM);  // sex
                            data.append(getDateValue(row.getCell(4))).append(DELIM); // dob
                            data.append(getValue(row.getCell(5))).append(DELIM); //model id

                            data.append(getValue(row.getCell(6))).append(DELIM); //passage
                            data.append(getValue(row.getCell(7))).append(DELIM); // cell number
                            data.append(getValue(row.getCell(8))).append(DELIM); //volume
                            data.append(getValue(row.getCell(9))).append(DELIM); //site of injection

                           data.append(getDateValue(row.getCell(10))).append(DELIM); // date of injection
                          
                            data.append(getValue(row.getCell(12))).append(DELIM); //group
                            //data.append("no group provided").append(DELIM);
                            data.append(getValue(row.getCell(11))).append(DELIM); //treatment-drug
                            data.append(getValue(row.getCell(13))).append(DELIM); //treatment scheme

                            //data.append(getValue(row.getCell(14))).append(DELIM); // treatment concentration
                            data.append("no concentration provided").append(DELIM);
                            data.append(getDateValue(row.getCell(14))).append(DELIM); //treatment started
                            data.append(getDateValue(row.getCell(15))).append(DELIM); // treatment ended
                            data.append(getValue(row.getCell(16))).append(DELIM); // route of admin

                            data.append(getDateValue(row.getCell(17))).append(DELIM); // date ended
                            data.append(getValue(row.getCell(18))).append(DELIM); // outcome
                           // data.append(getValue(row.getCell(20))).append(DELIM); // tumor visible
                            data.append("no tumor visisble value").append(DELIM);
                            data.append(getValue(row.getCell(20))).append(DELIM); // unexpected event
                            data.append(getValue(row.getCell(21))).append(DELIM); // survival time
                            data.append(getValue(row.getCell(19))).append(DELIM); // survival counted
                            data.append(getValue(row.getCell(22))).append(DELIM); // user

                            data.append("CNS_DATA COMPLETE").append(DELIM);
                            data.append("RECORD_COMPLETE").append(DELIM);
                            data.append("REDCAP_EVENT").append(DELIM);
                            data.append("SURVIVAL_24HR").append(DELIM);

                            data.append("\n");
                            
                            
                            if(!getValue(row.getCell(19)).equals("0.0")){
                                System.out.println(file.getName()+" "+getValue(row.getCell(19))+" "+getValue(row.getCell(20)));
                            }
                            
                            
                        }

                    } else {
                        System.out.println(valid);
                    }
                    try {
               //         System.out.println(myWorkBook.getSheetName(1));
               //         String weightSheet = getWeightData(myWorkBook.getSheetAt(1));
                    } catch (Exception e) {
                        System.out.println("no weight sheet for file");
                    }
                }
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(data.toString());
    }

    private static String validateDataSheet(Sheet dataSheet) {
        StringBuilder result = new StringBuilder();
        Row firstRow = dataSheet.getRow(0);
        if (23 == firstRow.getLastCellNum()) {

        } else {
            result.append(firstRow.getLastCellNum() + " is not 23 columns\n");
        }
        Iterator<Cell> it = firstRow.cellIterator();

        for (String column : COLUMN_NAMES) {
            System.out.println(column);
            Cell cell = it.next();
            String columnName = cell.getStringCellValue();
            if (!column.equalsIgnoreCase(columnName)) {
                result.append(" wrong column name " + columnName + " should be " + column + "\n");
            }

        }
        return result.toString();
    }

   

    private static String getValue(Cell cell) {
        String value = " ";
        try{
            value = getValue(cell, cell.getCellType());
        }catch(Exception e){
       // e.printStackTrace();
        }

        return value;
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

            return sdf.format(cell.getDateCellValue());
        } catch (Exception e) {
        }
        try {
            Date d = new Date(getValue(cell));
            return sdf.format(d);
        } catch (Exception e) {
        }

        return dateStr;
    }

    private static Date getTimePoint(Cell cell) {
        String dateStr = getDateValue(cell);
        Date date = null;
        if (!NO_VALUE.equals(dateStr)) {

            try {
                date = sdf.parse(dateStr);
                if (!date.after(AFTER) || !date.before(BEFORE)) {
                    date = null;
                }
            } catch (Exception e) {
            }
        }
        // System.out.println("Timepoint check for "+dateStr);
        return date;
    }

}

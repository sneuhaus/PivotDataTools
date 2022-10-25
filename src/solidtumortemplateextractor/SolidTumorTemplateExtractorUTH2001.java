/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

Hi Steve,

Would the following be possible? --- see getValue() for this

•	Remove trailing spaces from entries
•	Remove quotes from any column
•	Ensure that missing data are recorded with either NULL/empty cell or period
        remove any #'s


Thanks!

-Tim-


 */
package solidtumortemplateextractor;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import modelidentifier.UTHSCSASubtyper;
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
public class SolidTumorTemplateExtractorUTH2001 {

    static final String NO_VALUE = "NO VALUE";
    static final String NO_DATE_VALUE = "";//"NO DATE";
    static final String NPE = "";

    static final SimpleDateFormat sdf = new SimpleDateFormat();
    static final String DATE_PATTERN = "MM/dd/yyyy";
    static Date BEFORE, AFTER;
    static String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G"};
    static final String BEFORE_DATE = null;
    static final String AFTER_DATE = "01/01/2015";
    static final int TIME_POINTS = 25;
    
    static int offset = 2;
    
    static String fileName ="";
    
    static final String[] columnNames = {"record_id", "record_description", "st_template_version", 
        "st_panel_code", "tumor", "dose", "schedule", "st_passage", "transplant_date", 
        "treatment_date", "treatment_end_date", "technician", "study_end_date", "group",
        "dataset", "dataset_date", "day", "mouse","subtype","passage", "diameter_x", "diameter_y", 
        "unexpect_event", "event_comment", "reason", "status", "weight",
        "cage_a_wt", "cage_b_wt", "solid_tumor_data_complete", "record_complete", "redcap_event_name"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        sdf.applyLocalizedPattern(DATE_PATTERN);
        try {

            BEFORE = Calendar.getInstance().getTime();
            AFTER = sdf.parse(AFTER_DATE);

      
              File myFile = new File("C://PIVOTData/2001/UTHSCSA/");  
        
    //        System.out.print("File\tSheet\tExp ID\tStudy End Date\tVersion\tTumor\tTreatment and dose\tStart date\tSchedule\tEnd date\tPassage\tTransplant date\tTech\tComments\t");
   //         System.out.println("Timepoint\tDate\tDay\tMouse\tX (mm)\tY (mm)\tWeight\tUnexpected Event\tComment\tEuthanize Code\tEuthanize");

            for(String column : columnNames){
                System.out.print(column+"\t");
            }
            System.out.println();

            for (File file : myFile.listFiles()) {
                if (file.getName().contains(".xlsx")) {
                    fileName = file.getName();

                    FileInputStream fis = new FileInputStream(file);
                  
                    Workbook myWorkBook = new XSSFWorkbook(fis);
               //             System.out.println("Stats for file " + file.getName());
                    int sheets = getWorkBookStats(myWorkBook);
               
                    for (int s = 0; s < sheets; s++) {
                        Sheet mySheet = myWorkBook.getSheetAt(s);

                         String unexpectedEvent = getValue(mySheet.getRow(0).getCell(11));
                         if(unexpectedEvent == null || !unexpectedEvent.contains("UNEXPECTED")){
                             unexpectedEvent = getValue(mySheet.getRow(0).getCell(13));
                             if(unexpectedEvent != null && unexpectedEvent.contains("UNEXPECTED")){
                                 offset = 2;
                             }
                         }else{
                             offset = 0;
                         }
                        //   System.out.println();
                        //    System.out.println("File "+file.getName()+" Sheet "+letters[s]);
                        String headers = file.getName() + "\t" + "Data-Solid Tumor" + "\t" + getHeaders(mySheet)+"\t";

                        for (int i = 0; i < TIME_POINTS; i++) {
                            int start = 9 + (i * 29);

                            String tpd = getTimepointData(mySheet, start, headers);
                            //     System.out.print(headers);
                            if (!tpd.isBlank()) {
                                System.out.print(tpd);
                            }
                        }
                    }
                    fis.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTimepointData(Sheet sheet, int start, String headers) {
        StringBuffer sheetInfo = new StringBuffer(headers);
        StringBuffer timepoints = new StringBuffer();
        Row row = sheet.getRow(start);
        if(row == null) return "";
        sheetInfo.append(getValue(row.getCell(0)));
        sheetInfo.append("\t").append(getDateValue(row.getCell(2)));  // this is the cut of value for both sheets and timepoints
        row = sheet.getRow(start + 1);
        // sb.append("\t").append(getValue(row.getCell(1)));
        String dayStr = getValue(row.getCell(2));

        for (int i = 4; i < 24; i = i + 2) {
            row = sheet.getRow(i + start);
            if(row == null) return "";
            String mouse = getValue(row.getCell(0));
            String passage ="";
            // code for UTHSCSA 2001
            mouse = mouse.replace("P","p");
            mouse = mouse.replace("p"," p");
            mouse = mouse.replace("  ", " ");
            String[] parts = mouse.split(" ");
            if(parts.length == 2){
                mouse = parts[0];
                passage = parts[1];
                       
            }else if(parts.length == 3){
            mouse = parts[0]+parts[1];
            passage = parts[2];
            }else{
                mouse = parts[0];
            }
            
            if("NCH-51374".equals(mouse)){
                mouse = "NCH-S13-7484";
            }

            String subtype = UTHSCSASubtyper.getSubtype(mouse);
            
            String x = getValue(row.getCell(1));
            String weight = getValue(row.getCell(7));
            
            
            
            // tim would like to extract the numeric code from the code string  (1,2,3)
            // should be first char, test for numeric
            String unexCode = getValue(row.getCell(11+offset));
            if(unexCode != null && !unexCode.isBlank()){
                try{
                    
                    int code = Integer.parseInt(unexCode.trim().charAt(0)+"");
                    unexCode = code+"";
                   
                }catch(Exception e){
                    // this message will appear in the middle of the output
                    System.out.println("can't format unexCode "+unexCode+" to integer code in row "+(i+start));
                }
            }
            String unexComment = getValue(row.getCell(12+offset));
            
            
           
            String euthCode = getValue(row.getCell(14+offset));
            
            String euthComment = getValue(row.getCell(15+offset));
           
            
            
            row = sheet.getRow(i + start + 1);
            String y = getValue(row.getCell(1));

            if (!mouse.isBlank() && !x.isBlank() && !y.isBlank()) {
                try {
                    Double mouseNum = Double.parseDouble(mouse);
                    mouse = mouseNum.intValue() + "";
                } catch (Exception e) {
                }

                Double dayD = 0d;
                try{
                    dayD = Double.parseDouble(dayStr);
                }catch(Exception e){
                    System.out.print("File "+fileName+" row "+(i+start+1)+" Can not convert "+dayStr+" into day");
           //         e.printStackTrace();
                }
                //        System.out.print(sb.toString());
                timepoints.append(sheetInfo);
                timepoints.append("\t").append(dayD.intValue()).append("\t");
                timepoints.append(mouse).append("\t");
                timepoints.append(subtype).append("\t");
                timepoints.append(passage).append("\t");
                
                timepoints.append(x).append("\t");
                timepoints.append(y).append("\t");
                
                
                timepoints.append(unexCode).append("\t").append(unexComment).append("\t");
                timepoints.append(euthCode).append("\t").append(euthComment).append("\t");
                timepoints.append(weight).append("\n");
            }
        }
        return timepoints.toString();
    }

    private static String getHeaders(Sheet sheet) {
        StringBuffer headers = new StringBuffer();
        
        String panelCode = getValue(sheet.getRow(0).getCell(2));
        
        String studyEndDate = getDateValue(sheet.getRow(0).getCell(5));
        
        String version = getValue(sheet.getRow(0).getCell(6));
        
        String tumor = getValue(sheet.getRow(1).getCell(2));
        
    //    String treatmentAndDose = getValue(sheet.getRow(2).getCell(2));
    
        // this includes the comment as some sheets seem to include treatment there
        String treatmentAndDose = getTreatment(sheet);
          
        String treatmentStartDate = getDateValue(sheet.getRow(2).getCell(5));
      
        String schedule = getValue(sheet.getRow(3).getCell(2));
             
        String treatmentEndDate = getDateValue(sheet.getRow(3).getCell(5));
        
        String passage = getValue(sheet.getRow(4).getCell(2));

        try {
            Double passageNum = Double.parseDouble(passage);
            passage = passageNum.intValue() + "";
        } catch (Exception e) {
        }    
        
        String transplantDate = getDateValue(sheet.getRow(5).getCell(2));
        
        String tech = getValue(sheet.getRow(6).getCell(2));
       

        String comments = getValue(sheet.getRow(7).getCell(2));
       
        headers.append(version).append("\t");
        headers.append(panelCode).append("\t");
        headers.append(tumor).append("\t");
        headers.append(treatmentAndDose).append("\t");
        headers.append(schedule).append("\t");
        headers.append(passage).append("\t");
        headers.append(transplantDate).append("\t");
        headers.append(treatmentStartDate).append("\t");
        headers.append(treatmentEndDate).append("\t");
        headers.append(tech).append("\t");
        headers.append(studyEndDate).append("\t");
        if("Control".equals(treatmentAndDose)){
            headers.append("A");
        }else{
            headers.append("B");
        
        }
      
        
        
        
        return headers.toString();

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

    private static String getDateValue(Cell cell) {
        String dateStr = NO_DATE_VALUE;
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

    private static int getWorkBookStats(Workbook workBook) {
        // how many treatments
        // how many timepoints per treatment
        // how many mice
        int sheets = 7;
        int completedSheets = 0;

        for (int i = 0; i < sheets; i++) {

            Sheet sheet = workBook.getSheetAt(i);
            boolean hasData = getTimePoint(sheet.getRow(9).getCell(2)) != null;
            if (hasData) {

                String treatment = getTreatment(sheet);
        //                  System.out.println("Treatment for sheet " + LETTERS[i] + " is " + treatment);
                int timepoints = 0;
                for (int j = 0; j < 25; j++) {
                    int start = 9 + (j * 29);
                    try{
                        if (getTimePoint(sheet.getRow(start).getCell(2)) != null) {
                            timepoints++;
                        }
                    }catch(Exception e){
                      //  e.printStackTrace();
                    }
                }
                completedSheets++;
    //                       System.out.println("Sheet " + LETTERS[i] + " has " + timepoints + " timepoints");
            } else {
   //                    System.out.println("No data for sheet "+LETTERS[i]);
            }
        }

        return completedSheets;
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

    private static String getTreatment(Sheet sheet) {

        String treatmentAndDose = getValue(sheet.getRow(2).getCell(2));
        String comment = getValue(sheet.getRow(7).getCell(2));

        return treatmentAndDose;// + " " + comment;
    }

}

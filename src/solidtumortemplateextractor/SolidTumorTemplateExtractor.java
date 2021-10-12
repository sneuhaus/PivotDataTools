/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solidtumortemplateextractor;

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
public class SolidTumorTemplateExtractor {

    static final String NO_VALUE = "NO VALUE";

    static final SimpleDateFormat sdf = new SimpleDateFormat();
    static final String DATE_PATTERN = "MM/dd/yyyy";
    static Date before, after;
    static String[] letters = {"A", "B", "C", "D", "E", "F", "G"};
    static final String BEFORE_DATE = "01/01/2022";
    static final String AFTER_DATE = "01/01/2015";
    
    
    static final String[] columnNames = {"record_id", "record_description", "st_template_version", 
        "st_panel_code", "tumor", "dose", "schedule", "st_passage", "transplant_date", 
        "treatment_date", "treatment_end_date", "technician", "study_end_date", "group",
        "dataset", "dataset_date", "day", "mouse", "diameter_x", "diameter_y", 
        "unexpect_event", "event_comment", "reason", "status", "weight",
        "cage_a_wt", "cage_b_wt", "solid_tumor_data_complete", "record_complete", "redcap_event_name"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        sdf.applyLocalizedPattern(DATE_PATTERN);
        try {

            before = sdf.parse(BEFORE_DATE);
            after = sdf.parse(AFTER_DATE);

            File myFile = new File("C://1817-Emily");
  //         File myFile = new File("C://1816/raw");

    //        System.out.print("File\tSheet\tExp ID\tStudy End Date\tVersion\tTumor\tTreatment and dose\tStart date\tSchedule\tEnd date\tPassage\tTransplant date\tTech\tComments\t");
   //         System.out.println("Timepoint\tDate\tDay\tMouse\tX (mm)\tY (mm)\tWeight\tUnexpected Event\tComment\tEuthanize Code\tEuthanize");

            for(String column : columnNames){
                System.out.print(column+"\t");
            }
            System.out.println();

            for (File file : myFile.listFiles()) {
                if (file.getName().contains(".xlsx")) {

                    FileInputStream fis = new FileInputStream(file);
                  
                    Workbook myWorkBook = new XSSFWorkbook(fis);
                    //        System.out.println("Stats for file " + file.getName());
                    int sheets = getWorkBookStats(myWorkBook);
                    for (int s = 0; s < sheets; s++) {
                        Sheet mySheet = myWorkBook.getSheetAt(s);

                        //   System.out.println();
                        //    System.out.println("File "+file.getName()+" Sheet "+letters[s]);
                        String headers = file.getName() + "\t" + "Data-Solid Tumor" + "\t" + getHeaders(mySheet)+"\t"+letters[s]+"\t";

                        for (int i = 0; i < 25; i++) {
                            int start = 9 + (i * 29);

                            String tpd = getTimepointData(mySheet, start, headers);
                            //     System.out.print(headers);
                            if (!tpd.isBlank()) {
                                System.out.print(tpd);
                            }
                        }
                    }
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
        sheetInfo.append(getValue(row.getCell(0)));
        sheetInfo.append("\t").append(getDateValue(row.getCell(2)));  // this is the cut of value for both sheets and timepoints
        row = sheet.getRow(start + 1);
        // sb.append("\t").append(getValue(row.getCell(1)));
        String dayStr = getValue(row.getCell(2));

        for (int i = 4; i < 24; i = i + 2) {
            row = sheet.getRow(i + start);
            String mouse = getValue(row.getCell(0));

            String x = getValue(row.getCell(1));
            String weight = getValue(row.getCell(7));
            
            String unexCode = getValue(row.getCell(11));
            String unexComment = getValue(row.getCell(12));
            
            String euthCode = getValue(row.getCell(14));
            String euthComment = getValue(row.getCell(15));
           
            
            
            row = sheet.getRow(i + start + 1);
            String y = getValue(row.getCell(1));

            if (!mouse.isBlank() && !x.isBlank() && !y.isBlank()) {
                try {
                    Double mouseNum = Double.parseDouble(mouse);
                    mouse = mouseNum.intValue() + "";
                } catch (Exception e) {
                }

                Double dayD = Double.parseDouble(dayStr);
                //        System.out.print(sb.toString());
                timepoints.append(sheetInfo);
                timepoints.append("\t").append(dayD.intValue()).append("\t");
                timepoints.append(mouse).append("\t").append(x).append("\t");
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
        headers.append(studyEndDate);
      
        
        
        
        return headers.toString();

    }

    private static String getValue(Cell cell) {
        return getValue(cell, cell.getCellType());

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
                //          System.out.println("Treatment for sheet " + letters[i] + " is " + treatment);
                int timepoints = 0;
                for (int j = 0; j < 25; j++) {
                    int start = 9 + (j * 29);
                    if (getTimePoint(sheet.getRow(start).getCell(2)) != null) {
                        timepoints++;
                    }
                }
                completedSheets++;
                //           System.out.println("Sheet " + letters[i] + " has " + timepoints + " timepoints");
            } else {
                //       System.out.println("No data for sheet "+letters[i]);
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
                if (!date.after(after) || !date.before(before)) {
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

        return treatmentAndDose + " " + comment;
    }

}

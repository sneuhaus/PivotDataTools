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
import java.util.HashMap;
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
public class SolidTumorTemplateExtractorUTH2001and1713 {

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
    
    static String tumor="";
    
    static final String[] columnNames = {"record_id", "record_description", "st_template_version", 
        "st_panel_code", "dose", "schedule", "st_passage", "transplant_date", 
        "treatment_date", "treatment_end_date", "technician", "study_end_date", "group",
        "dataset", "dataset_date", "day", "mouse","tumor","subtype","st_passage", "diameter_x", "diameter_y", 
        "unexpect_event", "event_comment", "reason", "status", "weight",
        "cage_a_wt", "cage_b_wt", "solid_tumor_data_complete", "record_complete", "redcap_event_name"};

    static HashMap<String,String> mapper = new HashMap<>();
    
    static StringBuilder unknowns = new StringBuilder();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
    //special code for UTHSCASA 1713
    
    
        mapper.put("RH-30","Rh30");
        mapper.put("RH_82","Rh82");
        mapper.put("RH-82","Rh82");
         mapper.put("RH87","Rh87");
        mapper.put("SJRHB010927_X1","SJRhB010927");
        mapper.put("SJRHBO10927_X","SJRhB010927");
        mapper.put("SjRhB010927","SJRhB010927");
        mapper.put("SJRHB000026_X1","SjRhB00026x1");
        mapper.put("SjRhB00026x1","SjRhB00026x1");

     
        mapper.put("RH-12","Rh12");
        mapper.put("RH-36","Rh36");
        mapper.put("RH-85","Rh85");
        mapper.put("RH73","Rh73");
        mapper.put("RH-12","Rh12");
        mapper.put("SKNE","SKNEP1");
       // mapper.put("IRS-68","IRS68");
        mapper.put("RH-66","Rh66");
        mapper.put("RH-84","Rh84");
        mapper.put("EW-13","EW13");
        mapper.put("UHS","UHS0589");
        mapper.put("NCH-S1384","NCH-S13-7484");
        mapper.put("WT-16","WT16");
        mapper.put("RH-65","Rh65");
        mapper.put("RH87","Rh87");
        mapper.put("KT-13","KT13");
        mapper.put("WT-10", "WT10");
        
        mapper.put("IRS68","IRS-68");
        
        //1820
                 //SJRHB927X
        mapper.put("SJRHB927X","SJRhB010927");
        mapper.put("SjRhB00026x1","SJRhB00026x1");


        mapper.put("CCA(ARMS)","CCA");
        mapper.put("SMSCTRCTR", "SMSCTR");
        mapper.put("SMS", "SMSCTR");
        mapper.put("JR(UK)","JR-1");
        mapper.put("ES8", "ES-8");
        mapper.put("ES4", "ES-4");
        mapper.put("G401", "G-401");
        mapper.put("TC71", "TC-71");
        mapper.put("NCH-S13-7484","NCH-S13_7484");

        
        sdf.applyLocalizedPattern(DATE_PATTERN);
        try {

            BEFORE = Calendar.getInstance().getTime();
            AFTER = sdf.parse(AFTER_DATE);

      
              File myFile = new File("C://PIVOTData/1818/UTHSCSA/new");  
        
    //        System.out.print("File\tSheet\tExp ID\tStudy End Date\tVersion\tTumor\tTreatment and dose\tStart date\tSchedule\tEnd date\tPassage\tTransplant date\tTech\tComments\t");
   //         System.out.println("Timepoint\tDate\tDay\tMouse\tX (mm)\tY (mm)\tWeight\tUnexpected Event\tComment\tEuthanize Code\tEuthanize");

            for(String column : columnNames){
                System.out.print(column+"\t");
            }
            System.out.println();

            for (File file : myFile.listFiles()) {
                if (file.getName().contains(".xlsx")) {
                    fileName = file.getName();
                    
                    tumor ="";

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
                        String headers = file.getName() + "\t" + "Data-Solid Tumor" + "\t" + getHeaders(mySheet) +LETTERS[s]+"\t" ;
                        
                        

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
        
        System.out.println(unknowns.toString());
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
            
     //       String t = getValue(sheet.getRow(1).getCell(2));
            
            row = sheet.getRow(i + start);
            if(row == null) return "";
            String mouse = getValue(row.getCell(0));
            
//            if(t != null && t.trim().length()!=0){
//                mouse = t;
//            }

           
            
            String passage ="";
            // code for UTHSCSA 2001
            mouse = mouse.replace("-p","p");
            mouse = mouse.replace("P","p");
            mouse = mouse.replace("p"," p");
            mouse = mouse.replace("  ", " ");
            String[] parts = mouse.split(" ");
            if(parts.length == 2){
                mouse = map(parts[0]);
                passage = parts[1];
                       
            }else if(parts.length == 3){
            mouse = map(parts[0])+parts[1];
            passage = parts[2];
            }else{
                mouse = map(parts[0]);
            }
            
            
            
            if(mouse.startsWith("UHS")){
                mouse = "UHS0589";
            }
            
            if(mouse.equals("0498-FT0921")){
                mouse = "0498.FT0921 ";
            }

            if(mapper.get(mouse) != null){
                mouse = mapper.get(mouse);
            }
            
            String x = getValue(row.getCell(1));
            String weight = getValue(row.getCell(7));
            
            String localTumor = tumor;
            
            
            
            if(localTumor.equals("0498-FT0921")){
                localTumor = "0498.FT0921";
            }
            
            if(tumor == null || tumor.trim().length() == 0){
                localTumor = mouse;
            }
            
            if(mapper.get(localTumor) != null){
                localTumor = mapper.get(localTumor);
            }
            
            
             String subtype = UTHSCSASubtyper.getSubtype(localTumor);
             
            
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
                
                if(passage.trim().length()>0)
                    mouse = mouse+"-"+passage;
                
               // mouse should include passage?
               timepoints.append(mouse).append("\t");
               // model/tumor should not include passsage?
               timepoints.append(localTumor).append("\t");
               timepoints.append(subtype).append("\t");
               timepoints.append(passage).append("\t");
                
                timepoints.append(x).append("\t");
                timepoints.append(y).append("\t");
                
                
                timepoints.append(unexCode).append("\t").append(unexComment).append("\t");
                timepoints.append(euthCode).append("\t").append(euthComment).append("\t");
                timepoints.append(weight).append("\n");
                
                
               if(subtype.startsWith("Unknown")){
                unknowns.append(localTumor).append("\n");
             }
                
            }
        }
        return timepoints.toString();
    }
    
    private static String map(String mouse){
      //  if(mouse.startsWith("U"))System.out.println("\n\n\n\n"+mouse);
        if(mapper.containsKey(mouse))
            return mapper.get(mouse);
        return mouse;
    }

    private static String getHeaders(Sheet sheet) {
        StringBuffer headers = new StringBuffer();
        
        String panelCode = getValue(sheet.getRow(0).getCell(2));
        
        String studyEndDate = getDateValue(sheet.getRow(0).getCell(5));
        
        String version = getValue(sheet.getRow(0).getCell(6));
        
         tumor = getValue(sheet.getRow(1).getCell(2));
        
    //    String treatmentAndDose = getValue(sheet.getRow(2).getCell(2));
    
        // this includes the comment as some sheets seem to include treatment there
        String treatmentAndDose = getTreatment(sheet);
          
        String treatmentStartDate = getDateValue(sheet.getRow(2).getCell(5));
        String treatmentEndDate = getDateValue(sheet.getRow(3).getCell(5));
      
     // for 1818
     //   treatmentStartDate = getDateValue(sheet.getRow(9).getCell(2));
     //   treatmentEndDate = getDateValue(sheet.getRow(96).getCell(2));
      
        String schedule = getValue(sheet.getRow(3).getCell(2));
             
   
        
        if(treatmentAndDose != null && treatmentAndDose.contains("Control")){
            treatmentStartDate = "";
            treatmentEndDate = "";
        }
        
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
        headers.append(treatmentAndDose).append("\t");
        headers.append(schedule).append("\t");
        headers.append(passage).append("\t");
        headers.append(transplantDate).append("\t");
        headers.append(treatmentStartDate).append("\t");
        headers.append(treatmentEndDate).append("\t");
        headers.append(tech).append("\t");
        headers.append(studyEndDate).append("\t");
        
      
        
        
        
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
        int sheets = 5;
        int completedSheets = 0;

        for (int i = 0; i < sheets; i++) {

            Sheet sheet = workBook.getSheetAt(i);
            boolean hasData = false;
            try{
                hasData= getTimePoint(sheet.getRow(9).getCell(2)) != null;
            }catch(Exception e){}
            
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

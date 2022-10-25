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
import java.util.Scanner;

/**
 *
 * @author sbn
 */
public class UTSCSAFixer1816 {

   
    
    static final String[] columnNames = {"record_id", "record_description", "st_template_version", 
        "st_panel_code", "tumor", "dose", "schedule", "st_passage", "transplant_date", 
        "treatment_date", "treatment_end_date", "technician", "study_end_date", "group",
        "dataset", "dataset_date", "day", "mouse", "diameter_x", "diameter_y", 
        "unexpect_event", "event_comment", "reason", "status", "weight",
        "cage_a_wt", "cage_b_wt", "solid_tumor_data_complete", "record_complete", "redcap_event_name"};

    /**
     *  read in extracted uthscsa data
     *  for each row check model name if ends in A group is B if ends in B group is A
     *  group A control group B is treated with 1816
     *  remove A and B from tumor name and mouse name
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        Scanner s = new Scanner(new File("C://PIVOTData/1816/UTHSCSA/extracted/UTHSCSA1816.tsv"));
        s.useDelimiter("\r");
        System.out.print(s.next());
        while(s.hasNext()){
            String line = s.next();
           
            if(!line.isBlank()){
   //             System.out.print(line);
                fixLine(line);
            }
        }
        
        s.close();
        
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
      
        
        
       
    }
    
    private static void fixLine(String line)
    {
        String[] cols = line.split("\t");
        if(cols[4].endsWith("A")){
          
            cols[13] ="B";
            cols[5] = "1816A 6 mg/kg, IP";
            
        }else{
            cols[13] = "A";
            cols[5] = "1816B 6 mg/kg, IP";
        }
        cols[4] = cols[4].substring(0, cols[4].length()-2);
        cols[17] = cols[4];
        
        
        for(int i = 0; i < cols.length; i++){
          //  System.out.print(cols[i]);
            String item = cols[i];
            System.out.print(item);
            System.out.print("\t");
            
        }
        //System.out.println("");
        
    
        
        
    }
}

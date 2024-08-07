/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelidentifier;

import java.util.HashMap;

/**
 *
 * @author sbn
 * ERMS = embryonal rhabdomyosarcoma
 */
public class UTHSCSASubtyper {
    
    static final String UNK = "Unknown subtype";
    
   
    
    static HashMap<String,String> officialMap = new HashMap<>();
    
    static{
     
        
        
        officialMap.put("A-673","Ewing sarcoma cell line");
        officialMap.put("BT29","Rhabdoid tumor");
        officialMap.put("CHLA258","Ewing sarcoma");
        officialMap.put("COG891173","Hepatoblastoma");
        officialMap.put("ERB-TC71","");
        officialMap.put("ES","Ewing sarcoma");
        officialMap.put("ES-1","Ewing sarcoma");
        officialMap.put("ES-2","Ewing sarcoma");
        officialMap.put("ES-3","Ewing sarcoma");
        officialMap.put("ES-4","Ewing sarcoma");
        officialMap.put("ES-6","Ewing sarcoma");
        officialMap.put("ES-7","Ewing sarcoma");
        officialMap.put("ES-8","Ewing sarcoma");
        officialMap.put("EW-12","Ewing sarcoma");
        officialMap.put("EW-13","Ewing sarcoma");
        officialMap.put("EW-5","Ewing sarcoma");
        officialMap.put("G-401","Rhabdoid tumor cell line");
        officialMap.put("IRS-68","Rhabdomyosarcoma");
        officialMap.put("IRS56","Embryonal Rhabdomyosarcoma");
        officialMap.put("JR-1","Rhabdomyosarcoma");
        officialMap.put("KT13","Wilms tumor");
        officialMap.put("KT14","Rhabdoid tumor");
        officialMap.put("KT16","Rhabdoid tumor");
        officialMap.put("NCH-ARMS-2"," Alveolar rhabdomyosarcoma");
        officialMap.put("NCH-EWS","Ewing sarcoma");
        officialMap.put("NCH-EWS-1","Ewing sarcoma");
        officialMap.put("NCH-S13_7484","Rhabdomyosarcoma");
        officialMap.put("RBD1","Rhabdoid tumor");
        officialMap.put("RBD2","Rhabdoid tumor");
        officialMap.put("RD","Rhabdomyosarcoma");
        officialMap.put("Rh10","Rhabdomyosarcoma");
        officialMap.put("Rh18","Rhabdoid tumor");
        officialMap.put("Rh28","Rhabdomyosarcoma");
        officialMap.put("Rh30","Rhabdomyosarcoma");
        officialMap.put("Rh30R","Rhabdomyosarcoma");
        officialMap.put("Rh36","Rhabdomyosarcoma");
        officialMap.put("Rh41","Rhabdomyosarcoma");
        officialMap.put("Rh65","Rhabdomyosarcoma");
        officialMap.put("Rh66","Rhabdomyosarcoma");
        officialMap.put("Rh73","Rhabdomyosarcoma");
        officialMap.put("Rh75","Rhabdomyosarcoma");
        officialMap.put("Rh80","Rhabdomyosarcoma");
        officialMap.put("Rh81","Rhabdomyosarcoma");
        officialMap.put("Rh82","Rhabdomyosarcoma");
        officialMap.put("Rh83","Rhabdomyosarcoma");
        officialMap.put("Rh84","Rhabdomyosarcoma");
        officialMap.put("Rh85","Rhabdomyosarcoma");
        officialMap.put("Rh87","Rhabdomyosarcoma");
        officialMap.put("SJRhB00026x1","Rhabdomyosarcoma");
        officialMap.put("SJRhB010927","Rhabdomyosarcoma");
        officialMap.put("SJRHB011_X","Rhabdomyosarcoma");
        officialMap.put("SJRHB013_X","Rhabdomyosarcoma");
        officialMap.put("SKNEP1","Ewing sarcoma");
        officialMap.put("SMSCTR","Rhabodomyosarcoma");
        officialMap.put("SMT-ED","");
        officialMap.put("TC-71","Ewing sarcoma");
        officialMap.put("KT10","Wilms tumor");
        officialMap.put("WT11","Wilms tumor");
        officialMap.put("WT12","Rhabdoid tumor");
        officialMap.put("WT13","Wilms tumor");
        officialMap.put("KT5","Wilms tumor");
        officialMap.put("KT16","Rhabdoid tumor");
        officialMap.put("KT14","Rhabdoid tumor");
        officialMap.put("0498.FT0921","Hepatoblastoma");
        officialMap.put("0511-000","Hepatoblastoma");
        officialMap.put("0543-000","Hepatoblastoma");
        
        // updates follow
         officialMap.put("Rh88","ERMS");
         officialMap.put("UHS0589","Ewing sarcoma");
         officialMap.put("Rh12","ERMS");
         officialMap.put("EW-10","Ewing sarcoma");
         
         //1820
         officialMap.put("Rh76","Rhabdomyosarcoma");
         officialMap.put("CCA","Rhabdomyosarcoma");
         officialMap.put("CHALA258","Ewuing sarcoma");
         
         
    }
    
   
    
    public static String getSubtype(String mouse){
        String subtype = UNK;
        if(officialMap.containsKey(mouse)){
            subtype = officialMap.get(mouse);
        }
        return subtype;
    }
}

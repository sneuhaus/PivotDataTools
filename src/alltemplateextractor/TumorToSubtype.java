/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alltemplateextractor;

import java.util.HashMap;

/**
 *
 * @author sbn
 */
public class TumorToSubtype {
    
    public static HashMap<String,String> tumorToSubtype = new HashMap<>();
    
    static{

        tumorToSubtype.put("ALL-2","BCP");
        tumorToSubtype.put("ALL-7","BCP");
        tumorToSubtype.put("ALL-11","BCP");
        tumorToSubtype.put("ALL-17","BCP");
        tumorToSubtype.put("ALL-19","BCP");
        tumorToSubtype.put("ALL-25","BCP");
        tumorToSubtype.put("ALL-26","BCP");
        tumorToSubtype.put("ALL-28","BCP");
        tumorToSubtype.put("ALL-50","BCP");
        tumorToSubtype.put("ALL-51","BCP");
        tumorToSubtype.put("ALL-52","BCP");
        tumorToSubtype.put("ALL-53","BCP");
        tumorToSubtype.put("ALL-54","BCP");
        tumorToSubtype.put("ALL-57","BCP");
        tumorToSubtype.put("ALL-58","BCP");
        tumorToSubtype.put("ALL-59","BCP");
        tumorToSubtype.put("ALL-60","BCP");
        tumorToSubtype.put("ALL-61","BCP");
        tumorToSubtype.put("ALL-63","BCP");
        tumorToSubtype.put("ALL-75","BCP");
        tumorToSubtype.put("ALL-82","BCP");
        tumorToSubtype.put("ALL-83","BCP");
        tumorToSubtype.put("ALL-84","BCP");
        tumorToSubtype.put("ALL-87","BCP");
        tumorToSubtype.put("ALL-88","BCP");
        tumorToSubtype.put("ALL-91","BCP");
        tumorToSubtype.put("ALL-93","BCP");
        tumorToSubtype.put("ALL-94","BCP");
        tumorToSubtype.put("ALL-95","BCP");
        tumorToSubtype.put("ALL-122","BCP");
        tumorToSubtype.put("ALL-123","BCP");
        tumorToSubtype.put("PALNTB","BCP");
        tumorToSubtype.put("PALTWS","BCP");
        tumorToSubtype.put("ALL-4","Ph+");
        tumorToSubtype.put("ALL-55","Ph+");
        tumorToSubtype.put("ALL-56","Ph+");
        tumorToSubtype.put("ALL-10","Ph-like");
        tumorToSubtype.put("ALL-102","Ph-like");
        tumorToSubtype.put("ALL-105","Ph-like");
        tumorToSubtype.put("ALL-106","Ph-like");
        tumorToSubtype.put("ALL-107","Ph-like");
        tumorToSubtype.put("ALL-108","Ph-like");
        tumorToSubtype.put("ALL-115","Ph-like");
        tumorToSubtype.put("ALL-116","Ph-like");
        tumorToSubtype.put("ALL-117","Ph-like");
        tumorToSubtype.put("ALL-118","Ph-like");
        tumorToSubtype.put("PAKHZT","Ph-like");
        tumorToSubtype.put("PAKRSL","Ph-like");
        tumorToSubtype.put("PAKSWW","Ph-like");
        tumorToSubtype.put("PAKVKK","Ph-like");
        tumorToSubtype.put("PAKYEP","Ph-like");
        tumorToSubtype.put("PALJDL","Ph-like");
        tumorToSubtype.put("PALKTY","Ph-like");
        tumorToSubtype.put("PALLSD","Ph-like");
        tumorToSubtype.put("PAMDRM","Ph-like");
        tumorToSubtype.put("ALL-3","MLL");
        tumorToSubtype.put("MLL-1","MLL");
        tumorToSubtype.put("MLL-2","MLL");
        tumorToSubtype.put("MLL-3","MLL");
        tumorToSubtype.put("MLL-5","MLL");
        tumorToSubtype.put("MLL-6","MLL");
        tumorToSubtype.put("MLL-7","MLL");
        tumorToSubtype.put("MLL-8","MLL");
        tumorToSubtype.put("MLL-14","MLL");
        tumorToSubtype.put("MLL-86","MLL");
        tumorToSubtype.put("ALL-8","T");
        tumorToSubtype.put("ALL-16","T");
        tumorToSubtype.put("ALL-27","T");
        tumorToSubtype.put("ALL-29","T");
        tumorToSubtype.put("ALL-30","T");
        tumorToSubtype.put("ALL-31","T");
        tumorToSubtype.put("ALL-32","T");
        tumorToSubtype.put("ALL-33","T");
        tumorToSubtype.put("ALL-39","T");
        tumorToSubtype.put("ALL-42","T");
        tumorToSubtype.put("ALL-43","T");
        tumorToSubtype.put("ALL-46","T");
        tumorToSubtype.put("ALL-47","T");
        tumorToSubtype.put("ALL-80","T");
        tumorToSubtype.put("ALL-81","T");
        tumorToSubtype.put("ALL-85","T");
        tumorToSubtype.put("ALL-90","T");
        tumorToSubtype.put("ALL-97","T");
        tumorToSubtype.put("ALL-121","T");
        tumorToSubtype.put("ETP-1","ETP");
        tumorToSubtype.put("ETP-2","ETP");
        tumorToSubtype.put("ETP-3","ETP");
        tumorToSubtype.put("ETP-4","ETP");
        tumorToSubtype.put("ETP-5","ETP");
        tumorToSubtype.put("ETP-6","ETP");
        
        tumorToSubtype.put("TGT-174","BCP");
        tumorToSubtype.put("TGT-196","BCP");
        tumorToSubtype.put("TGT-020","Ph-like");
        tumorToSubtype.put("TGT-047","Ph-like");
        tumorToSubtype.put("TGT-052","Ph-like");
        tumorToSubtype.put("TGT-065","Ph-like");
        tumorToSubtype.put("TGT-084","Ph-like");
        tumorToSubtype.put("TGT-145","Ph-like");
        tumorToSubtype.put("TGT-151","Ph-like");
        tumorToSubtype.put("TGT-161","Ph-like");
        tumorToSubtype.put("TGT-258","Ph-like");
        
        
    }
    
    public static String getSubType(String tumor){
        return tumorToSubtype.get(tumor) == null ? "unknown subtype" : tumorToSubtype.get(tumor);
    }
    
    
}
    


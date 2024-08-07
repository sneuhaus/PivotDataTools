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
 */
public class IDChecker {
    
    static HashMap<String,String> model2Subtype = new HashMap<>();
    static{
        
        
        model2Subtype.put("ALL-10","ALL");
        model2Subtype.put("ALL-102","ALL");
        model2Subtype.put("ALL-105","ALL");
        model2Subtype.put("ALL-106","ALL");
        model2Subtype.put("ALL-107","ALL");
        model2Subtype.put("ALL-108","ALL");
        model2Subtype.put("ALL-11","ALL");
        model2Subtype.put("ALL-115","ALL");
        model2Subtype.put("ALL-116","ALL");
        model2Subtype.put("ALL-117","ALL");
        model2Subtype.put("ALL-118","ALL");
        model2Subtype.put("ALL-11-AKR1C3","ALL");
        model2Subtype.put("ALL-11-EV","ALL");
        model2Subtype.put("ALL-121","ALL");
        model2Subtype.put("ALL-122","ALL");
        model2Subtype.put("ALL-123","ALL");
        model2Subtype.put("ALL-16","ALL");
        model2Subtype.put("ALL-17","ALL");
        model2Subtype.put("ALL-19","ALL");
        model2Subtype.put("ALL-2","ALL");
        model2Subtype.put("ALL-25","ALL");
        model2Subtype.put("ALL-26","ALL");
        model2Subtype.put("ALL-27","ALL");
        model2Subtype.put("ALL-28","ALL");
        model2Subtype.put("ALL-29","ALL");
        model2Subtype.put("ALL-3","ALL");
        model2Subtype.put("ALL-30","ALL");
        model2Subtype.put("ALL-31","ALL");
        model2Subtype.put("ALL-32","ALL");
        model2Subtype.put("ALL-33","ALL");
        model2Subtype.put("ALL-39","ALL");
        model2Subtype.put("ALL-4","ALL");
        model2Subtype.put("ALL-42","ALL");
        model2Subtype.put("ALL-43","ALL");
        model2Subtype.put("ALL-46","ALL");
        model2Subtype.put("ALL-47","ALL");
        model2Subtype.put("ALL-4-GL-2","ALL");
        model2Subtype.put("ALL-50","ALL");
        model2Subtype.put("ALL-51","ALL");
        model2Subtype.put("ALL-52","ALL");
        model2Subtype.put("ALL-53","ALL");
        model2Subtype.put("ALL-54","ALL");
        model2Subtype.put("ALL-55","ALL");
        model2Subtype.put("ALL-56","ALL");
        model2Subtype.put("ALL-57","ALL");
        model2Subtype.put("ALL-58","ALL");
        model2Subtype.put("ALL-59","ALL");
        model2Subtype.put("ALL-60","ALL");
        model2Subtype.put("ALL-61","ALL");
        model2Subtype.put("ALL-63","ALL");
        model2Subtype.put("ALL-7","ALL");
        model2Subtype.put("ALL-75","ALL");
        model2Subtype.put("ALL-8","ALL");
        model2Subtype.put("ALL-80","ALL");
        model2Subtype.put("ALL-81","ALL");
        model2Subtype.put("ALL-82","ALL");
        model2Subtype.put("ALL-83","ALL");
        model2Subtype.put("ALL-84","ALL");
        model2Subtype.put("ALL-85","ALL");
        model2Subtype.put("ALL-87","ALL");
        model2Subtype.put("ALL-88","ALL");
        model2Subtype.put("ALL-90","ALL");
        model2Subtype.put("ALL-91","ALL");
        model2Subtype.put("ALL-93","ALL");
        model2Subtype.put("ALL-94","ALL");
        model2Subtype.put("ALL-95","ALL");
        model2Subtype.put("ALL-97","ALL");
        model2Subtype.put("ETP-1","ALL");
        model2Subtype.put("ETP-2","ALL");
        model2Subtype.put("ETP-3","ALL");
        model2Subtype.put("ETP-4","ALL");
        model2Subtype.put("ETP-5","ALL");
        model2Subtype.put("ETP-6","ALL");
        model2Subtype.put("MLL-1","ALL");
        model2Subtype.put("MLL-14","ALL");
        model2Subtype.put("MLL-2","ALL");
        model2Subtype.put("MLL-3","ALL");
        model2Subtype.put("MLL-5","ALL");
        model2Subtype.put("MLL-6","ALL");
        model2Subtype.put("MLL-7","ALL");
        model2Subtype.put("MLL-8","ALL");
        model2Subtype.put("MLL-86","ALL");
        model2Subtype.put("PAKHZT","ALL");
        model2Subtype.put("PAKRSL","ALL");
        model2Subtype.put("PAKSWW","ALL");
        model2Subtype.put("PAKVKK","ALL");
        model2Subtype.put("PAKYEP","ALL");
        model2Subtype.put("PALJDL","ALL");
        model2Subtype.put("PALKTY","ALL");
        model2Subtype.put("PALLSD","ALL");
        model2Subtype.put("PALNTB","ALL");
        model2Subtype.put("PALTWS","ALL");
        model2Subtype.put("PAMDRM","ALL");
        model2Subtype.put("Adam-Fx","Neuroblastoma");
        model2Subtype.put("Adam-Lx","Neuroblastoma");
        model2Subtype.put("Adam-Rx","Neuroblastoma");
        model2Subtype.put("Brooke-PDX","Neuroblastoma");
        model2Subtype.put("CHLA-79","Neuroblastoma");
        model2Subtype.put("COG-440x","Neuroblastoma");
        model2Subtype.put("COG-N-415x","Neuroblastoma");
        model2Subtype.put("COG-N-421x","Neuroblastoma");
        model2Subtype.put("COG-N-424x","Neuroblastoma");
        model2Subtype.put("COG-N-452x","Neuroblastoma");
        model2Subtype.put("COG-N-453x","Neuroblastoma");
        model2Subtype.put("COG-N-470x","Neuroblastoma");
        model2Subtype.put("COG-N-471x","Neuroblastoma");
        model2Subtype.put("COG-N-480x","Neuroblastoma");
        model2Subtype.put("COG-N-496x","Neuroblastoma");
        model2Subtype.put("COG-N-519x","Neuroblastoma");
        model2Subtype.put("COG-N-557x","Neuroblastoma");
        model2Subtype.put("COG-N-560x","Neuroblastoma");
        model2Subtype.put("COG-N-561x","Neuroblastoma");
        model2Subtype.put("COG-N-564x","Neuroblastoma");
        model2Subtype.put("COG-N-573x","Neuroblastoma");
        model2Subtype.put("COG-N-589x","Neuroblastoma");
        model2Subtype.put("COG-N-590x","Neuroblastoma");
        model2Subtype.put("COG-N-603x","Neuroblastoma");
        model2Subtype.put("COG-N-618x","Neuroblastoma");
        model2Subtype.put("COG-N-619x","Neuroblastoma");
        model2Subtype.put("COG-N-620x","Neuroblastoma");
        model2Subtype.put("COG-N-623x","Neuroblastoma");
        model2Subtype.put("COG-N-624x","Neuroblastoma");
        model2Subtype.put("COG-N-625x","Neuroblastoma");
        model2Subtype.put("COG-N-628x","Neuroblastoma");
        model2Subtype.put("Felix-PDX","Neuroblastoma");
        model2Subtype.put("HG-Lx","Neuroblastoma");
        model2Subtype.put("HG-Rx","Neuroblastoma");
        model2Subtype.put("JG-Fx","Neuroblastoma");
        model2Subtype.put("JG-Rx","Neuroblastoma");
        model2Subtype.put("KWK-6062x","Neuroblastoma");
        model2Subtype.put("N0619x","Neuroblastoma");
        model2Subtype.put("NB-1","Neuroblastoma");
        model2Subtype.put("NB-1382","Neuroblastoma");
        model2Subtype.put("NB-1643","Neuroblastoma");
        model2Subtype.put("NB-1691","Neuroblastoma");
        model2Subtype.put("NB-1771","Neuroblastoma");
        model2Subtype.put("NB-EBc1","Neuroblastoma");
        model2Subtype.put("NB-Fly-623m","Neuroblastoma");
        model2Subtype.put("NB-SD","Neuroblastoma");
        model2Subtype.put("NCI-H82","Lung cancer cell line");
        model2Subtype.put("NP012x","Neuroblastoma");
        model2Subtype.put("NP015x","Neuroblastoma");
        model2Subtype.put("NP016x","Neuroblastoma");
        model2Subtype.put("NP030x","Neuroblastoma");
        model2Subtype.put("NP031x","Neuroblastoma");
        model2Subtype.put("NP036x","Neuroblastoma");
        model2Subtype.put("NP037x","Neuroblastoma");
        model2Subtype.put("NP039x","Neuroblastoma");
        model2Subtype.put("NP040x","Neuroblastoma");
        model2Subtype.put("NP043x","Neuroblastoma");
        model2Subtype.put("NP045x","Neuroblastoma");
        model2Subtype.put("NP049x","Neuroblastoma");
        model2Subtype.put("NP053x","Neuroblastoma");
        model2Subtype.put("Obelix-2x","Neuroblastoma");
        model2Subtype.put("Obelix-Fx","Neuroblastoma");
        model2Subtype.put("Obelix-Rx","Neuroblastoma");
        model2Subtype.put("RH07-1x","Neuroblastoma");
        model2Subtype.put("RH07-2x","Neuroblastoma");
        model2Subtype.put("RH07-3x","Neuroblastoma");
        model2Subtype.put("RH07-4x","Neuroblastoma");
        model2Subtype.put("RH07-5x","Neuroblastoma");
        model2Subtype.put("SD","Neuroblastoma");
        model2Subtype.put("SK-N-AS","Neuroblastoma");
        model2Subtype.put("YDLFx ","Neuroblastoma");
        model2Subtype.put("YDLRx","Neuroblastoma");
        model2Subtype.put("IC-1128GBM","Glioblastoma");
        model2Subtype.put("IC-1406GBM","Glioblastoma");
        model2Subtype.put("IC-1425EPN","Ependymoma");
        model2Subtype.put("IC-1425EPN","Ependymoma");
        model2Subtype.put("IC-1621GBM","Glioblastoma");
        model2Subtype.put("IC-2305GBM","Glioblastoma");
        model2Subtype.put("IC-3704GBM","Glioblastoma");
        model2Subtype.put("IC-3752GBM","Glioblastoma");
        model2Subtype.put("IC-3752GBM","Glioblastoma");
        model2Subtype.put("IC-6634GBM","Glioblastoma");
        model2Subtype.put("IC-6634GBM","Glioblastoma");
        model2Subtype.put("ICb-0614EPN","Ependymoma");
        model2Subtype.put("ICb-1078MB","Medulloblastoma");
        model2Subtype.put("ICb-1227AA","Anaplastic astrocytoma");
        model2Subtype.put("ICb-1299MB","Medulloblastoma");
        model2Subtype.put("ICb-1387MB","Medulloblastoma");
        model2Subtype.put("ICb-1494MB","Medulloblastoma");
        model2Subtype.put("ICb-1572MB","Medulloblastoma");
        model2Subtype.put("ICb-1595MB","Medulloblastoma");
        model2Subtype.put("ICb-4423EPN","Ependymoma");
        model2Subtype.put("ICb-4423EPN","Ependymoma");
        model2Subtype.put("ICb-5108ATRT","Atypical Teratoid Rhabdoid Tumor");
        model2Subtype.put("ICb-5610MB","Medulloblastoma");
        model2Subtype.put("ICb-9508MB","Medulloblastoma");
        model2Subtype.put("ICb-S0906EPN","Ependymoma");
        model2Subtype.put("IC-L1115ATRT","Atypical Teratoid Rhabdoid Tumor");
        model2Subtype.put("IC-N0605ATRT","Atypical Teratoid Rhabdoid Tumor");
        model2Subtype.put("IC-R0315GBM","Glioblastoma");
        model2Subtype.put("OS1","Osteosarcoma");
        model2Subtype.put("OS17","Osteosarcoma");
        model2Subtype.put("OS2","Osteosarcoma");
        model2Subtype.put("OS29","Osteosarcoma");
        model2Subtype.put("OS31","Osteosarcoma");
        model2Subtype.put("OS33","Osteosarcoma");
        model2Subtype.put("OS34","Osteosarcoma");
        model2Subtype.put("OS36","Osteosarcoma");
        model2Subtype.put("OS39R","Osteosarcoma");
        model2Subtype.put("OS42","Osteosarcoma");
        model2Subtype.put("OS43","Osteosarcoma");
        model2Subtype.put("OS46","Osteosarcoma");
        model2Subtype.put("OS49","Osteosarcoma");
        model2Subtype.put("OS51","Osteosarcoma");
        model2Subtype.put("OS55","Osteosarcoma");
        model2Subtype.put("OS56","Osteosarcoma");
        model2Subtype.put("OS60","Osteosarcoma");
        model2Subtype.put("OS9","Osteosarcoma");
        model2Subtype.put("SaOS2","Osteosarcoma cell line");
        model2Subtype.put("A-673","Ewing sarcoma cell line");
        model2Subtype.put("BT29","Rhabdoid tumor");
        model2Subtype.put("CHLA258","Ewing sarcoma");
        model2Subtype.put("COG891173","Hepatoblastoma");
        model2Subtype.put("ERB-TC71","");
        model2Subtype.put("ES","Ewing sarcoma");
        model2Subtype.put("ES-1","Ewing sarcoma");
        model2Subtype.put("ES-2","Ewing sarcoma");
        model2Subtype.put("ES-3","Ewing sarcoma");
        model2Subtype.put("ES-4","Ewing sarcoma");
        model2Subtype.put("ES-6","Ewing sarcoma");
        model2Subtype.put("ES-7","Ewing sarcoma");
        model2Subtype.put("ES-8","Ewing sarcoma");
        model2Subtype.put("EW-12","Ewing sarcoma");
        model2Subtype.put("EW-13","Ewing sarcoma");
        model2Subtype.put("EW-5","Ewing sarcoma");
        model2Subtype.put("G-401","Rhabdoid tumor cell line");
        model2Subtype.put("IRS-68","Rhabdomyosarcoma");
        model2Subtype.put("JR-1","Rhabdomyosarcoma");
        model2Subtype.put("KT13","Wilms tumor");
        model2Subtype.put("KT14","Rhabdoid tumor");
        model2Subtype.put("KT16","Rhabdoid tumor");
        model2Subtype.put("NCH-ARMS-2","Alveolar rhabdomyosarcoma");
        model2Subtype.put("NCH-EWS","Ewing sarcoma");
        model2Subtype.put("NCH-EWS-1","Ewing sarcoma");
        model2Subtype.put("NCH-S13_7484","Rhabdomyosarcoma");
        model2Subtype.put("RBD1","Rhabdoid tumor");
        model2Subtype.put("RBD2","Rhabdoid tumor");
        model2Subtype.put("RD","Rhabdomyosarcoma");
        model2Subtype.put("Rh10","Rhabdomyosarcoma");
        model2Subtype.put("Rh18 ","Rhabdoid tumor");
        model2Subtype.put("Rh28","Rhabdomyosarcoma");
        model2Subtype.put("Rh30","Rhabdomyosarcoma");
        model2Subtype.put("Rh30R","Rhabdomyosarcoma");
        model2Subtype.put("Rh36","Rhabdomyosarcoma");
        model2Subtype.put("Rh41","Rhabdomyosarcoma");
        model2Subtype.put("Rh65","Rhabdomyosarcoma");
        model2Subtype.put("Rh66","Rhabdomyosarcoma");
        model2Subtype.put("Rh73","Rhabdomyosarcoma");
        model2Subtype.put("Rh75","Rhabdomyosarcoma");
        model2Subtype.put("Rh76","Rhabdomyosarcoma");
        model2Subtype.put("Rh80","Rhabdomyosarcoma");
        model2Subtype.put("Rh81","Rhabdomyosarcoma");
        model2Subtype.put("Rh82","Rhabdomyosarcoma");
        model2Subtype.put("Rh83","Rhabdomyosarcoma");
        model2Subtype.put("Rh84","Rhabdomyosarcoma");
        model2Subtype.put("Rh85","Rhabdomyosarcoma");
        model2Subtype.put("Rh87","Rhabdomyosarcoma");
        model2Subtype.put("SJRhB00026x1","Rhabdomyosarcoma");
        model2Subtype.put("SJRhB010927","Rhabdomyosarcoma");
        model2Subtype.put("SJRHB011_X","Rhabdomyosarcoma");
        model2Subtype.put("SJRHB013_X","Rhabdomyosarcoma");
        model2Subtype.put("SKNEP1","Ewing sarcoma");
        model2Subtype.put("SMSCTR","Rhabodomyosarcoma");
        model2Subtype.put("SMT-ED","");
        model2Subtype.put("TC-71","Ewing sarcoma");
        model2Subtype.put("KT10","Wilms tumor");
        model2Subtype.put("WT11","Wilms tumor");
        model2Subtype.put("WT12","Rhabdoid tumor");
        model2Subtype.put("WT13","Wilms tumor");
        model2Subtype.put("KT6","Wilms tumor");
        model2Subtype.put("KT5","Wilms tumor");
        model2Subtype.put("KT16","Rhabdoid tumor");
        model2Subtype.put("KT14","Rhabdoid tumor");
        model2Subtype.put("0498.FT0921","Hepatoblastoma");
        model2Subtype.put("0511-000","Hepatoblastoma");
        model2Subtype.put("0543-000","Hepatoblastoma");
        model2Subtype.put("Rh88","Embryonal rhabdomyosarcoma");
        model2Subtype.put("UHS0589","Ewing sarcoma");
        model2Subtype.put("Rh12","Embryonal rhabdomyosarcoma");
        model2Subtype.put("EW-10","Ewing sarcoma");
        model2Subtype.put("IRS56","embryonal rhabdomyosarcoma");
        
        
        
    }
    
 
    public static String checkID(String id)
    {
        
        if(model2Subtype.get(id) != null){
            return id;
        }
        
        return "Unknown Model";
    }
    
    public static String getSubtype(String id)
    {
        String subtype = "UnknownSubtype";
        if(model2Subtype.get(id) != null){
            subtype = model2Subtype.get(id);
        }
        
        return subtype;
    }
}
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelidentifier;

/**
 *
 * @author sbn
 */
public class IDChecker {
    
    static String[] CHOP_IDs = {"CHLA-79",
                "COG-424x",
                "COG-440x",
                "COG-N-415x",
                "COG-N-415x DLL3 BITE",
                "COG-N-421x",
                "COG-N-424x",
                "COG-N-452x",
                "COG-N-453x",
                "COG-N-453x-1",
                "COG-N-471x",
                "COG-N-480x",
                "COG-N-496x",
                "COG-N-519x",
                "COG-N-557x",
                "COG-N-561x",
                "COG-N-619x",
                "COG-N-Felix-x",
                "Ebc1",
                "Ebc1-1a",
                "FelixPDX",
                "Felix-PDX",
                "Felix-PDX  DLL3 BiTE",
                "FelixPDX DLL3 BITE",
                "Fly-623m",
                "H82  DLL3 BiTE",
                "KWK-6062x",
                "NB-1643",
                "NB-1691",
                "NB-EBc1",
                "NB-Fly-623m",
                "NBSD",
                "NB-SD",
                "SD",
                "SK-N-AS"};
    
    static String[] UTHSCSA_IDs = {"A673",
                "BT29",
                "CHLA258",
                "ERB-TC71",
                "ES",
                "ES1",
                "ES1 ",
                "ES2",
                "ES-2",
                "ES3",
                "ES4",
                "ES6",
                "ES-6",
                "ES7",
                "ES-7",
                "ES8",
                "EW12",
                "EW-12",
                "EW13",
                "EW5",
                "EW-5",
                "EW8 ",
                "G401",
                "IRS-68",
                "KT13",
                "KT-13",
                "KT13p44",
                "NCH-EWS",
                "NCH-EWS1",
                "NCH-EWS-1",
                "RBD1",
                "RBD2",
                "RD",
                "Rh10",
                "Rh10 ",
                "RH18",
                "Rh18 ",
                "Rh28",
                "Rh28 ",
                "Rh30",
                "Rh30R",
                "Rh36",
                "Rh41",
                "RH65",
                "RH66",
                "Rh73",
                "Rh75",
                "Rh80",
                "Rh81",
                "Rh82",
                "Rh83",
                "Rh84",
                "Rh85",
                "Rh87",
                "SJRhB00026x1",
                "SJRhB010927",
                "SJRHB011_X",
                "SJRHB013_X",
                "SKNEP1",
                "SKNEP1  ",
                "SMT-ED",
                "TC71",
                "TC-71",
                "WT10",
                "WT-10",
                "WT11",
                "WT12",
                "WT-13",
                "WT14",
                "WT16",
                "WT-16",
                "WT-5"};
    
    static String[] MDA_IDs = {"ES1",
                "OS1",
                "OS17",
                "OS2",
                "OS29",
                "OS31",
                "OS-31",
                "OS33",
                "OS34",
                "OS36",
                "OS36-SJ",
                "OS39",
                "OS42",
                "OS46",
                "OS55",
                "OS56",
                "OS60",
                "OS60-SJ",
                "OS9",
                "OS9 ",
                "SaOS2",
                "TC7"};
    
    
    static  String[] LURIE_IDs = {"IC-1128GBM",
                        "IC-1406GBM",
                        "IC-1425EPN",
                        "IC-1425EPN-rIII",
                        "IC-1621GBM",
                        "IC-2305GBM",
                        "IC-3704GBM",
                        "IC-3752GBM",
                        "IC-3752GBM-rVIII",
                        "IC-6634GBM",
                        "IC-6634GBM-rIV-TC2",
                        "ICb-0614EPN",
                        "ICb-1078MB",
                        "ICb-1227AA",
                        "ICb-1299MB",
                        "ICb-1387MB",
                        "ICb-1494MB",
                        "ICb-1572MB",
                        "ICb-1595MB",
                        "ICb-4423EPN",
                        "Icb-4423EPN-rVI",
                        "Icb-5108ATRT-rV",
                        "ICb-5610MB",
                        "ICb-9508MB",
                        "Icb-S0906EPN-rIV",
                        "IC-L1115ATRT",
                        "IC-L1115ATRT-rVII",
                        "IC-N0605ATRT-rVI",
                        "IC-R0315GBM",
                        "IC-R0315GBM-rV"};
    
    static boolean checkMDA(String id){
        for(String mdaID : MDA_IDs){
            if(mdaID.equals(id)) return true;
        }
        return false;
    }

    static boolean checkLURIE(String id){
        for(String mdaID : LURIE_IDs){
            if(mdaID.equals(id)) return true;
        }
        return false;
    }
    static boolean checkCHOP(String id){
        for(String mdaID : CHOP_IDs){
            if(mdaID.equals(id)) return true;
        }
        return false;
    }
    
    static boolean checkUTHSCSA(String id){
        for(String mdaID : UTHSCSA_IDs){
            if(mdaID.equals(id)) return true;
        }
        return false;
    }

}
    


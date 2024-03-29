/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelidentifier;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sbn
 * ERMS = embryonal rhabdomyosarcoma
 */
public class UTHSCSASubtyper {
    
    static HashMap<String,String> map = new HashMap<>();
    static ArrayList<String> list = new ArrayList<>();
    
    static{
        map.put("A673","");
        map.put("BT29","");
        map.put("CHLA258","Ewing sarcoma");
        map.put("ERB-TC71","");
        map.put("ES","Ewing sarcoma");
        map.put("ES1","Ewing sarcoma");
        map.put("ES1","Ewing sarcoma");
        map.put("ES2","Ewing sarcoma");
        map.put("ES-2","Ewing sarcoma");
        map.put("ES3","Ewing sarcoma");
        map.put("ES4","Ewing sarcoma");
        map.put("ES6","Ewing sarcoma");
        map.put("ES-6","Ewing sarcoma");
        map.put("ES7","Ewing sarcoma");
        map.put("ES-7","Ewing sarcoma");
        map.put("ES8","Ewing sarcoma");
        map.put("EW12","Ewing sarcoma");
        map.put("EW-12","Ewing sarcoma");
        map.put("EW13","Ewing sarcoma");
        map.put("EW5","Ewing sarcoma");
        map.put("EW-5","Ewing sarcoma");
        map.put("EW10","Ewing sarcoma");
        map.put("EW8","Ewing sarcoma");
        map.put("G401","Rhabdoid tumor");
        map.put("IRS68","Rhabdomyosarcoma");
        map.put("KT13","Wilms Tumor");
        map.put("KT-13","Wilms Tumor");
        map.put("NCH-EWS","Ewing sarcoma");
        map.put("NCH-EWS1","Ewing sarcoma");
        map.put("NCH-EWS-1","Ewing sarcoma");
        map.put("RBD1","Rhabdomyosarcoma");
        map.put("RBD2","Rhabdomyosarcoma");
        map.put("RD","Rhabdomyosarcoma");
        map.put("Rh10","Rhabdomyosarcoma");
        map.put("Rh10","Rhabdomyosarcoma");
        map.put("Rh12","ERMS");
        map.put("Rh18","Rhabdomyosarcoma");
        map.put("Rh18 ","Rhabdomyosarcoma");
        map.put("Rh28","Rhabdomyosarcoma");
        map.put("Rh28 ","Rhabdomyosarcoma");
        map.put("Rh30","Rhabdomyosarcoma");
        map.put("Rh30R","Rhabdomyosarcoma");
        map.put("Rh36","Rhabdomyosarcoma");
        map.put("Rh41","Rhabdomyosarcoma");
        map.put("RH41","Rhabdomyosarcoma");
        map.put("Rh65","Rhabdomyosarcoma");
        map.put("Rh66","Rhabdomyosarcoma");
        map.put("Rh73","Rhabdomyosarcoma");
        map.put("Rh75","Rhabdomyosarcoma");
        map.put("Rh80","Rhabdomyosarcoma");
        map.put("Rh81","Rhabdomyosarcoma");
        map.put("Rh82","Rhabdomyosarcoma");
        map.put("Rh83","Rhabdomyosarcoma");
        map.put("Rh84","Rhabdomyosarcoma");
        map.put("Rh85","Rhabdomyosarcoma");
        map.put("Rh87","Rhabdomyosarcoma");
        map.put("Rh88","ERMS");
        map.put("SJRhB00026","Rhabdomyosarcoma");
        map.put("SJRhB010927","Rhabdomyosarcoma");
        map.put("SJRHB011_X","");
        map.put("SJRHB013_X","ERMS");
        map.put("SKNEP1","Ewing sarcoma");
        map.put("SMT-ED","");
        map.put("SMT-CTR","ERMS");
        map.put("TC71","Ewing sarcoma");
        map.put("TC-71","Ewing sarcoma");
        map.put("WT10","Wilms Tumor");
        map.put("WT-10","Wilms Tumor");
        map.put("WT11","Wilms Tumor");
        map.put("WT12","Wilms Tumor");
        map.put("WT-13","Wilms Tumor");
        map.put("WT14","Wilms Tumor");
        map.put("WT16","Rhabdoid (extracranial)");
        map.put("WT-16","Rhabdoid (extracranial)");
        map.put("WT-5","Wilms Tumor");
        map.put("UHS0589","Ewing sarcoma");
        map.put("NCH-S13-7484","ARMS");
        map.put("SJRhB013","ERMS");
        map.put("SMS-CTR", "ERMS");
        map.put("SMSCTR", "ERMS");
        map.put("SJRhB000026","ERMS");
        map.put("JR1(UK)", "Embryonal rhabdomyosarcoma");
        
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT16");
//        list.add("RD");
//        list.add("G401");
//        list.add("CHLA258");
//        list.add("Rh18");
//        list.add("Rh75");
//        list.add("Rh41");
//        list.add("Rh83");
//        list.add("KT13");
//        list.add("EW13");
//        list.add("WT16");
//        list.add("RD");
//        list.add("G401");
//        list.add("CHLA258");
//        list.add("Rh18");
//        list.add("Rh75");
//        list.add("Rh41");
//        list.add("Rh83");
//        list.add("KT13");
//        list.add("EW13");
//        list.add("WT16");
//        list.add("RD");
//        list.add("G401");
//        list.add("CHLA258");
//        list.add("Rh18");
//        list.add("Rh75");
//        list.add("Rh41");
//        list.add("Rh83");
//        list.add("KT13");
//        list.add("EW13");
//        list.add("WT16");
//        list.add("RD");
//        list.add("G401");
//        list.add("Rh75");
//        list.add("Rh41");
//        list.add("Rh83");
//        list.add("KT13");
//        list.add("EW13");
//        list.add("WT16");
//        list.add("G401");
//        list.add("Rh83");
//        list.add("KT13");
//        list.add("EW13");
//        list.add("WT16");
//        list.add("KT13");
//        list.add("EW13");
//        list.add("WT16");
//        list.add("KT13");
//        list.add("EW13");
//        list.add("WT16");
//        list.add("EW13");
//        list.add("WT16");
//        list.add("WT16");
//        list.add("WT16");
//        list.add("ES8");
//        list.add("Rh73");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("ES7");
//        list.add("SJRhB010927");
//        list.add("NCH-S13-7484");
//        list.add("SMS-CTR");
//        list.add("ES8");
//        list.add("Rh73");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("ES7");
//        list.add("SJRhB010927");
//        list.add("NCH-S13-7484");
//        list.add("SMS-CTR");
//        list.add("ES8");
//        list.add("Rh73");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("ES7");
//        list.add("SJRhB010927");
//        list.add("NCH-S13-7484");
//        list.add("SMS-CTR");
//        list.add("ES8");
//        list.add("Rh73");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("ES7");
//        list.add("SJRhB010927");
//        list.add("NCH-S13-7484");
//        list.add("SMS-CTR");
//        list.add("ES8");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("ES7");
//        list.add("SJRhB010927");
//        list.add("SMS-CTR");
//        list.add("ES8");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("SJRhB010927");
//        list.add("SMS-CTR");
//        list.add("ES8");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("SJRhB010927");
//        list.add("SMS-CTR");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("SJRhB010927");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("Rh81");
//        list.add("Rh82");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("Rh81");
//        list.add("ES1");
//        list.add("ES4");
//        list.add("Rh12");
//        list.add("Rh36");
//        list.add("Rh85");
//        list.add("ES1");
//        list.add("ES4");
//        list.add("Rh12");
//        list.add("Rh36");
//        list.add("Rh85");
//        list.add("ES1");
//        list.add("ES4");
//        list.add("Rh12");
//        list.add("Rh36");
//        list.add("Rh85");
//        list.add("Rh12");
//        list.add("Rh36");
//        list.add("Rh85");
//        list.add("Rh12");
//        list.add("Rh36");
//        list.add("Rh12");
//        list.add("EW5");
//        list.add("EW12");
//        list.add("ES2");
//        list.add("SKNEP1");
//        list.add("EW5");
//        list.add("EW12");
//        list.add("ES2");
//        list.add("SKNEP1");
//        list.add("EW12");
//        list.add("ES2");
//        list.add("SKNEP1");
//        list.add("EW12");
//        list.add("ES2");
//        list.add("SKNEP1");
//        list.add("EW12");
//        list.add("ES2");
//        list.add("ES2");
//        list.add("ES2");
//        list.add("EW5");
//        list.add("Rh88");
//        list.add("Rh87");
//        list.add("SJRhB013");
//        list.add("NCH-EWS1");
//        list.add("EW5");
//        list.add("Rh88");
//        list.add("Rh87");
//        list.add("SJRhB013");
//        list.add("NCH-EWS1");
//        list.add("EW5");
//        list.add("Rh88");
//        list.add("Rh87");
//        list.add("SJRhB013");
//        list.add("NCH-EWS1");
//        list.add("EW5");
//        list.add("Rh88");
//        list.add("Rh87");
//        list.add("SJRhB013");
//        list.add("NCH-EWS1");
//        list.add("EW5");
//        list.add("Rh88");
//        list.add("SJRhB013");
//        list.add("NCH-EWS1");
//        list.add("Rh88");
//        list.add("NCH-EWS1");
//        list.add("Rh88");
//        list.add("NCH-EWS1");
//        list.add("SJRhB000026");
//        list.add("SJRhB000026");
//        list.add("SJRhB000026");
//        list.add("SJRhB000026");
//        list.add("SJRhB000026");
//        list.add("SJRhB000026");
//        list.add("Rh41");
//        list.add("KT13");
//        list.add("Rh41");
//        list.add("KT13");
//        list.add("Rh41");
//        list.add("KT13");
//        list.add("Rh41");
//        list.add("KT13");
//        list.add("KT13");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("Rh30");
//        list.add("WT10");
//        list.add("IRS68");
//        list.add("Rh66");
//        list.add("Rh84");
//        list.add("EW13");
//        list.add("IRS68");
//        list.add("Rh66");
//        list.add("Rh84");
//        list.add("EW13");
//        list.add("IRS68");
//        list.add("Rh66");
//        list.add("Rh84");
//        list.add("EW13");
//        list.add("IRS68");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("IRS68");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("IRS68");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("Rh66");
//        list.add("EW13");
//        list.add("Rh66");
//        list.add("Rh66");
//        list.add("Rh66");
//        list.add("EW10");
//        list.add("UHS0589");
//        list.add("EW10");
//        list.add("UHS0589");
//        list.add("EW10");
//        list.add("UHS0589");
//        list.add("EW10");
//        list.add("UHS0589");
//        list.add("EW10");
//        list.add("KT13");
//        list.add("WT16");
//        list.add("IRS68");
//        list.add("G401");
//        list.add("EW10");
//        list.add("NCH-S13-7484");
//        list.add("SJRhB010927");
//        list.add("Rh65");
//        list.add("UHS0589");
//        list.add("KT13");
//        list.add("WT16");
//        list.add("IRS68");
//        list.add("G401");
//        list.add("EW10");
//        list.add("NCH-S13-7484");
//        list.add("SJRhB010927");
//        list.add("Rh65");
//        list.add("UHS0589");
//        list.add("KT13");
//        list.add("WT16");
//        list.add("IRS68");
//        list.add("G401");
//        list.add("EW10");
//        list.add("NCH-S13-7484");
//        list.add("SJRhB010927");
//        list.add("Rh65");
//        list.add("UHS0589");
//        list.add("KT13");
//        list.add("WT16");
//        list.add("IRS68");
//        list.add("G401");
//        list.add("EW10");
//        list.add("NCH-S13-7484");
//        list.add("SJRhB010927");
//        list.add("Rh65");
//        list.add("UHS0589");
//        list.add("KT13");
//        list.add("WT16");
//        list.add("IRS68");
//        list.add("G401");
//        list.add("EW10");
//        list.add("NCH-S13-7484");
//        list.add("SJRhB010927");
//        list.add("Rh65");
//        list.add("UHS0589");
//        list.add("KT13");
//        list.add("WT16");
//        list.add("IRS68");
//        list.add("G401");
//        list.add("EW10");
//        list.add("SJRhB010927");
//        list.add("Rh65");
//        list.add("WT16");
//        list.add("IRS68");
//        list.add("G401");
//        list.add("EW10");
//        list.add("SJRhB010927");
//        list.add("Rh65");
//        list.add("WT16");
//        list.add("EW10");
//        list.add("WT16");
//        list.add("EW10");
//        list.add("WT16");
//        list.add("EW10");
//        list.add("ES7");
//        list.add("ES7");
//        list.add("ES7");
//        list.add("ES7");
//        list.add("ES7");
//        list.add("Rh65");
//        list.add("Rh65");
//        list.add("Rh65");
//        list.add("Rh65");
//        list.add("Rh65");
//        list.add("Rh65");
//        list.add("Rh65");
//        list.add("Rh65");

        list.add("KT13");
        list.add("ES2");
        list.add("SMS-CTR");
        list.add("Rh75");
        list.add("ES1");
        list.add("Rh41");
        list.add("CHLA258");
        list.add("EW5");
        list.add("JR1 (UK)");
        list.add("Rh73");
        list.add("Rh75");
        list.add("RBD2");
        list.add("NCH-EWS-1");
        list.add("Rh36");
        list.add("RBD1");
        list.add("WT12");
        list.add("Rh85");
        list.add("Rh87");
        list.add("TC71");
        list.add("Rh30");
        list.add("RD");
        list.add("WT10");
        list.add("EW12");
        list.add("WT10");
        list.add("Rh66");
        list.add("Rh84");
        list.add("EW10");
        list.add("NCH-51374");
        list.add("Rh30R");
        list.add("Rh30");
        list.add("Rh65");
        list.add("RD");
        list.add("Rh30R");
        list.add("SMS-CTR");
        
    }
    
    public static void main(String[] args){
        
        HashMap<String,String> unknowns = new HashMap<>();
        
        for(String mouse : list){
            String subtype = map.get(mouse);
            if(subtype == null || subtype.trim().length() == 0){
                subtype = "unknown subtype";
                unknowns.put(mouse,mouse);
            }
            System.out.println(mouse+"\t"+subtype);
        }
        
        for(String mouse: unknowns.keySet()){
            System.out.println(mouse+" has no corresponding subtype");
        }
        
    }
    
    public static String getSubtype(String mouse){
        String subtype = "unknow subtype";
        if(map.containsKey(mouse)){
            subtype = map.get(mouse);
        }
        return subtype;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solidtumortemplateextractor;

import java.util.HashMap;

/**
 *
 * @author sbn
 */
public class EventProcessor {
    
    // column indexes
    final static int RECORD_ID  = 0;
    final static int GROUP = 13;
    final static int MOUSE = 17;
    final static int U_EVENT = 20;
    final static int COMMENT = 21;
 
    public static void main(String[] args){
        
    }
    
    
    public static String process(String data){
        StringBuilder result = new StringBuilder();
        HashMap<String,String> eventMap = new HashMap<>();
        String[] lines = data.split("\n");
        
        // sanity check
        String[] columns = lines[0].split("\t");
        System.out.println("Record ID:"+columns[RECORD_ID]);
        System.out.println("Group:"+columns[GROUP]);
        System.out.println("Mouse:"+columns[MOUSE]);
        System.out.println("U Event:"+columns[U_EVENT]);
        System.out.println("Comment:"+columns[COMMENT]);
        
        // column headers
        result.append(lines[0]);
        
        // find event codes;
        for(int i = 1; i < lines.length; i++){
            
            String[] values = lines[i].split("\t");
            if(values[U_EVENT].trim().length() > 0){
                String key = values[RECORD_ID]+values[GROUP]+values[MOUSE];
                String value = values[U_EVENT]+"\t"+values[COMMENT];
                eventMap.put(key,value);
            }
        }
        
        for(String key : eventMap.keySet()){
            System.out.println(key+" "+eventMap.get(key));
        }
        
        
        for(int i = 1; i < lines.length; i++){
            
            String[] values = lines[i].split("\t");
            String key = values[RECORD_ID]+values[GROUP]+values[MOUSE];
            if(eventMap.containsKey(key)){
                String[] event = eventMap.get(key).split("\t");
            
                values[U_EVENT] = event[0];
                values[COMMENT] = event[1];
            }
            result.append(String.join("\t", values)).append("\n");
        }
        
        return result.toString();
    }
}
    


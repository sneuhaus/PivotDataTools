/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solidtumortemplateextractor;

/**
 *
 * @author sbn
 */
public class STTRow {
    String record_id;
    String record_description;
    String st_template_version;
    String st_panel_code;
    String tumor;
    String dose;
    String schedule;
    String st_passage;
    String transplant_date;
    String treatment_date;
    String treatment_end_date;
    String technician;
    String study_end_date;
    String group;
    String dataset;
    String dataset_date;
    String day;
    String mouse;
    String diameter_x;
    String diameter_y;
    String unexpect_event;
    String event_comment;
    String reason;
    String status;
    String weight;
    String cage_a_wt;
    String cage_b_wt;
    String solid_tumor_data_complete;
    String record_complete;
    String redcap_event_name;
    
    public String toString(String delim){
        StringBuilder sb = new StringBuilder();
        sb.append(record_id).append(delim);
        sb.append(record_description).append(delim);
        sb.append(st_template_version).append(delim);
        sb.append(st_panel_code).append(delim);
        sb.append(tumor).append(delim);
        sb.append(dose).append(delim);
        sb.append(schedule).append(delim);
        sb.append(st_passage).append(delim);
        sb.append(transplant_date).append(delim);
        sb.append(treatment_date).append(delim);
        sb.append(treatment_end_date).append(delim);
        sb.append(technician).append(delim);
        sb.append(study_end_date).append(delim);
        sb.append(group).append(delim);
        sb.append(dataset).append(delim);
        sb.append(dataset_date).append(delim);
        sb.append(day).append(delim);
        sb.append(mouse).append(delim);
        sb.append(diameter_x).append(delim);
        sb.append(diameter_y).append(delim);
        sb.append(unexpect_event).append(delim);
        sb.append(event_comment).append(delim);
        sb.append(reason).append(delim);
        sb.append(status).append(delim);
        sb.append(weight).append(delim);
        sb.append(cage_a_wt).append(delim);
        sb.append(cage_b_wt).append(delim);
        sb.append(solid_tumor_data_complete).append(delim);
        sb.append(record_complete).append(delim);
        sb.append(redcap_event_name).append("\n");
        return sb.toString();
        
    }
}

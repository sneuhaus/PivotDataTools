/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huddleDashboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author sbn
 */
public class HuddleAPIInterface {

    static String accessToken;
    static String refreshToken;

    HashMap<Integer, String> workspaceNames = new HashMap<>();

    public static void main(String[] args) {
        HuddleAPIInterface test = new HuddleAPIInterface();
        test.searchDocuments();
    //    System.out.println(test.getHTML(false));
    }
    
    
    public void searchDocuments(){
        // C:/PIVOTData/ find -printf "%TY-%Tm-%Td %TT %p\n" | sort -n | grep Extracted

         try {
            String endpoint ="files/search/documents?query=Extracted&from=2022-09-01";
            JSONObject job = new JSONObject(getToken(null, false));
            accessToken = job.getString("access_token");
            refreshToken = job.getString("expires_in");

            String apiResponse = getJSON(endpoint, accessToken);
            JSONObject joe = new JSONObject(apiResponse);
            JSONArray results = joe.getJSONArray("results");
            for(int i = 0; i < results.length(); i++){
                JSONObject document = results.getJSONObject(i).getJSONObject("document");
                System.out.println(document.getString("title"));
            }
 //           System.out.println(joe.toString(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHTML(boolean showAdditional) {

        StringBuilder buffer = new StringBuilder();

        try {

            HashMap<String, ArrayList<Task>> workspaceTasks = new HashMap<>();

            String endpoint = null;// = request.getParameter("endpoint");
            if (endpoint == null || endpoint.isEmpty()) {
                endpoint = "tasks?types=Todo,FileRequest,Approval";
                // for now just todos
                endpoint = "tasks?types=Todo";
            }

            if (accessToken == null) {
                JSONObject job = new JSONObject(getToken(null, false));
                accessToken = job.getString("access_token");
                refreshToken = job.getString("expires_in");
            }

            String apiResponse = getJSON(endpoint, accessToken);
            JSONObject joe = new JSONObject(apiResponse);

            if (endpoint.contains("tasks?")) {
                JSONArray tasks = joe.getJSONArray("tasks");
                for (int i = 0; i < tasks.length(); i++) {
                    Task task = (buildTask(tasks.getJSONObject(i)));

                    if (workspaceTasks.containsKey(task.getWorkspace())) {
                        workspaceTasks.get(task.getWorkspace()).add(task);
                    } else {
                        ArrayList<Task> theTasks = new ArrayList<>();
                        theTasks.add(task);
                        workspaceTasks.put(task.getWorkspace(), theTasks);
                    }
                }

            }
            JSONObject b = new JSONObject(getJSON("/users/1124774", accessToken));
            b = b.getJSONObject("membership");
            JSONArray workspaces = b.getJSONArray("workspaces");
            for (int i = 0; i < workspaces.length(); i++) {
                JSONObject workspace = workspaces.getJSONObject(i);
                JSONArray links = workspace.getJSONArray("links");
                String workspaceID = links.getJSONObject(0).getString("href").split("workspaces/")[1];

                buffer.append("<tr><td><a target=\"_blank\" href=\"https://pivot.huddle.com/workspace/" + workspaceID + "\"><b>" + workspace.getString("title") + "</a></b></td>");

                //    buffer.append(workspaceID);
                JSONObject c = new JSONObject(getJSON("/tasks/workspace/" + workspaceID + "?types=todo", accessToken));
                JSONArray jsonArray = c.getJSONArray("tasks");
                ArrayList<Task> tasks = new ArrayList<>();
                ArrayList<Task> additionalTasks = new ArrayList<>();
                for (int j = 0; j < jsonArray.length(); j++) {
                    Task t = (buildTask(jsonArray.getJSONObject(j)));
                    try {
                        int taskNum = Integer.parseInt(t.title.split("_")[0]);
                        if (taskNum < 13) {
                            tasks.add(t);
                        } else {
                            additionalTasks.add(t);
                        }
                    } catch (Exception e) {
                        // not a numbered task
                        additionalTasks.add(t);
                    }
                }
                if (jsonArray.length() == 0) {
                    buffer.append("<td>No Tasks</td>");
                }
                Collections.sort(tasks, new SortTaskByTitle());
                Collections.sort(additionalTasks, new SortTaskByTitle());

                for (Task t : tasks) {
                    buffer.append("<td style=\"background-color:" + t.getColor() + "\">" + t.getDisplay());
                    buffer.append("</td>");
                }

                if (showAdditional) {
                    for (Task t : additionalTasks) {
                        buffer.append("<td style=\"background-color:" + t.getColor() + "\">" + t.getDisplay());
                        buffer.append("</td>");

                    }
                }

                buffer.append("</tr>\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //  buffer.append(footer);
        return buffer.toString();

    }

    public class Task {

        String title;
        String description;
        String type;
        String status;
        ArrayList<String> assignees = new ArrayList<>();
        Date createDate;
        String updateDate;
        String owner;
        String completedBy;
        String link;
        int workspaceID;
        String workspace;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Title:" + title).append("\n");
            sb.append("Desc:" + description).append("\n");
            sb.append("Type:" + type).append("\n");
            sb.append("Status:" + status).append("\n");
            sb.append("Owner:" + owner).append("\n");
            sb.append("Workspace:" + workspace).append("\n");
            sb.append("Assignees:");
            boolean comma = false;
            for (String assignee : assignees) {
                if (comma) {
                    sb.append(",");
                }
                comma = true;
                sb.append(assignee);
            }

            return sb.toString();

        }

        public String getDisplay() {
            StringBuilder sb = new StringBuilder();
            sb.append("<a href=\"").append(link).append("\" target=\"_blank\">");
            sb.append(title).append("</a><br>");
            // sb.append("Desc:"+description).append("\n");
            //   sb.append("Type:"+type).append("<br>");
            //   sb.append("Status:"+status).append("<br>");
            //   sb.append("Owner:"+owner).append("<br>");
            //  sb.append("Workspace:"+workspace).append("\n");
            //   sb.append("Assignees:");
//           boolean comma = false;
//           for(String assignee : assignees){
//               if(comma){
//                   sb.append(",");
//               }
//               comma = true;
//               sb.append(assignee);
//            }
            sb.append(updateDate);
            return sb.toString();
        }

        public String getColor() {
            if (status == null || status.isEmpty()) {
                return "";
            }
            if (status.startsWith("In")) {
                return "yellow";
            }
            if (status.startsWith("Not")) {
                return "grey";
            }
            if (status.startsWith("Com")) {
                return "green";
            }

            return "";
        }

        public String getWorkspace() {
            return workspace;
        }

        public String getLink() {
            return link;
        }
    }

    private Task buildTask(JSONObject taskData) {

        Task task = new Task();

        try {

            task.title = taskData.getString("title");
            task.description = taskData.getString("description");
            task.type = taskData.getString("type");
            task.status = taskData.getString("status");
            task.updateDate = taskData.getString("updated");
            JSONArray actors = taskData.getJSONArray("actors");
            for (int i = 0; i < actors.length(); i++) {
                JSONObject actor = actors.getJSONObject(i);
                if ("owner".equals(actor.getString("rel"))) {
                    task.owner = actor.getString("name");
                }
            }
            // find any assingees
            JSONArray links = taskData.getJSONArray("links");
            for (int i = 0; i < links.length(); i++) {
                JSONObject link = links.getJSONObject(i);
                if ("assignments".equals(link.getString("rel"))) {
                    if (link.getInt("count") > 0) {
                        task.assignees = getAssignees(link.getString("href"));
                    }
                }
                if ("parent".equals(link.getString("rel"))) {
                    task.workspaceID = Integer.parseInt(link.getString("href").substring(34));

                    if (workspaceNames.containsKey(task.workspaceID)) {
                        task.workspace = workspaceNames.get(task.workspaceID);
                    } else {
                        task.workspace = getWorkspaceName(task.workspaceID);
                    }
                }
                if ("alternate".equals(link.getString("rel"))) {
                    task.link = link.getString("href").replace("/us.h", "/pivot.h");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    private String getWorkspaceName(Integer id) throws Exception {
        String apiResponse = getJSON("workspaces/" + id, accessToken);
        JSONObject joe = new JSONObject(apiResponse);
        String title = joe.getString("title");
        workspaceNames.put(id, title);
        //     buffer.append("Workspace id:"+id+" workspace name:"+title);
        return title;

    }

    private ArrayList<String> getAssignees(String link) throws Exception {
        ArrayList<String> assignees = new ArrayList<>();

        String apiResponse = getJSON(link, accessToken);
        JSONObject joe = new JSONObject(apiResponse);
        JSONArray asses = joe.getJSONArray("assignments");
        for (int j = 0; j < asses.length(); j++) {
            JSONObject ass = asses.getJSONObject(j);
            JSONArray actors = ass.getJSONArray("actors");
            for (int i = 0; i < actors.length(); i++) {
                JSONObject actor = actors.getJSONObject(i);
                if ("assignee".equals(actor.getString("rel"))) {
                    assignees.add(actor.getString("name"));
                }
            }
        }
        return assignees;

    }

    private String getToken(String code, boolean refresh) {

        String uri = "https://login.huddle.com/token";
        String responseSingle;
        StringBuilder response = new StringBuilder();

        HttpURLConnection connection = null;
        try {
            URL url = new URL(uri);
            connection
                    = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true); // sending stuff

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoInput(true); //we want a response
            connection.setUseCaches(false);

            OutputStream out = connection.getOutputStream();

            OutputStreamWriter wr = new OutputStreamWriter(out);
            if (!refresh) {
                wr.write("grant_type=client_credentials&client_id=JAXPivot&client_secret=bK6ru3BNnWdbtgHPL3eKZe3Gv");
            } else {
                wr.write("grant_type=refresh_token&&client_id=JacksonLab_PIVOT_Dashboard&refresh_token=" + code);
            }
            wr.flush();
            wr.close();
            out.close();

            // Open a stream which can read the server response
            InputStream in = connection.getInputStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                while ((responseSingle = rd.readLine()) != null) {
                    response.append(responseSingle);
                }
                rd.close(); //close the reader

            } catch (Exception e) {

                e.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();

    }

    private String getJSON(String apiCall, String token) {

        String apiBase = "https://api.huddle.com/";

        if (apiCall.startsWith("http")) {
            apiBase = "";
        }

        String responseSingle = "";
        StringBuffer response = new StringBuffer();

        HttpURLConnection connection = null;
        try {
            URL url = new URL(apiBase + apiCall);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            connection.setDoInput(true); //we want a response
            connection.setUseCaches(false);

            // Open a stream which can read the server response
            InputStream in = connection.getInputStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                while ((responseSingle = rd.readLine()) != null) {
                    response.append(responseSingle);
                }
                rd.close(); //close the reader

            } catch (IOException e) {

                e.printStackTrace();

                this.accessToken = null;

            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();

    }

    public class SortTaskByTitle implements Comparator<Task> {

        public int compare(Task a, Task b) {
            return a.title.compareTo(b.title);
        }
    }

}

package selab.threetier.presentation;

import org.json.JSONObject;
import selab.threetier.logic.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AddTaskPresentation extends JSONPresentation {

    @Override
    public JSONObject getData(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject request = new JSONObject(new BufferedReader(new InputStreamReader(body)).lines().collect(Collectors.joining("\n")));

        Task newTask = new Task();
        newTask.setTitle(request.getString("title"));
        try {
            Date date1 = new SimpleDateFormat("y-M-d H:m:s").parse(request.getString("start"));
            Date date2 = new SimpleDateFormat("y-M-d H:m:s").parse(request.getString("end"));

            newTask.setStart(date1);
            newTask.setEnd(date2);

//            New change
            if (date2.compareTo(date1) <= 0){
                throw new IOException("End of the task before the start");
            }

            ArrayList<Task> allTasks= Task.getAll();
            for(Task t: allTasks){
                Date date1_o = t.getStart();
                Date date2_o = t.getEnd();
                if ((date1.compareTo(date2_o) <= 0 && date1_o.compareTo(date1) <= 0 ) || (date2.compareTo(date2_o) <= 0 && date1_o.compareTo(date2) <= 0 )) {
                    throw new IOException("Overlap with previous");
                }
            }

            // New changes
        } catch (ParseException ex) {
            throw new IOException("Invalid date/time format");
        }
        newTask.save();

        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return new JSONObject(result);
    }
}

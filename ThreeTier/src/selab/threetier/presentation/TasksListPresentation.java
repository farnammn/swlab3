package selab.threetier.presentation;

import org.json.JSONArray;
import org.json.JSONObject;
import selab.threetier.logic.Task;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class CustomComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getStart().compareTo(o2.getStart());
    }
}

public class TasksListPresentation extends JSONPresentation {
    @Override
    public JSONObject getData(String method, InputStream body) {
        JSONObject result = new JSONObject();
        // Changed Here
        ArrayList<Task> allTasks = Task.getAll();
        Collections.sort(allTasks, new CustomComparator());

        result.put("tasks", new JSONArray(allTasks));
        return result;
    }
}

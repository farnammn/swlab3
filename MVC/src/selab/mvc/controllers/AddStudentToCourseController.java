package selab.mvc.controllers;

import org.json.JSONObject;
import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.entities.Course;
import selab.mvc.models.entities.Student;
import selab.mvc.models.entities.StudentCourse;
import selab.mvc.views.JsonView;
import selab.mvc.views.View;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddStudentToCourseController extends Controller {

    private DataSet<StudentCourse> T = new DataSet<>();

    public AddStudentToCourseController(DataContext dataContext) {
        super(dataContext);
        this.T = dataContext.getStudentCourse();
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String studentNo = input.getString("studentNo");
        String courseNo = input.getString("courseNo");
        String points = input.getString("points");

        // TODO: Add required codes to associate the student with course
        Student student = super.dataContext.getStudents().get(studentNo);
        Course course = super.dataContext.getCourses().get(courseNo);

        StudentCourse sc = new StudentCourse(studentNo, courseNo, points);

        T.add(sc);
        student.addCourse(sc);
        course.addStudent(sc);

        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return new JsonView(new JSONObject(result));
    }
}

package selab.mvc.models.entities;

import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;

    private DataSet<StudentCourse> courses = new DataSet<>();

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void addCourse(StudentCourse sc){
        courses.add(sc);
    }

    public void removeCourse(String courseNo){
        if(courses.containsKey(courseNo + " - " + this.studentNo)) {
            StudentCourse sc = courses.get(courseNo + " - " + this.studentNo);
            courses.remove(sc);
        }
    }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
        // TODO: Calculate and return the average of the points
        int sum = 0;
        int i = 0;
        for (StudentCourse c: courses.getAll()){
            sum += Integer.parseInt(c.getPoints());
            i += 1;
        }
        if (i == 0)
            return 0;
        return (float) sum / (float) i;
    }

    public String getCourses() {
        String temp = "";
        boolean flag = false;
        for (StudentCourse c: courses.getAll()){
            if(flag)
                temp = temp + " - " + c.getCourseNo();
            else
                temp = c.getStudentNo();
            flag = true;
        }
        return temp;
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }
}

package selab.mvc.models.entities;

import selab.mvc.models.Model;

public class StudentCourse implements Model {
    private String studentNo;
    private String courseNo;
    private String points;

    @Override
    public String getPrimaryKey() {
        return this.courseNo + " - " + this.studentNo;
    }


    public StudentCourse(String studentNo, String courseNo, String points) {
        this.studentNo = studentNo;
        this.courseNo = courseNo;
        this.points = points;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public String getPoints() {
        return points;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}

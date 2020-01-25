package edu.aucegypt.learningcentershub.data;

import java.io.Serializable;


public class Course implements Serializable {
   String CourseName;
   String CourseImage;

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseImage() {
        return CourseImage;
    }

    public void setCourseImage(String courseImage) {
        CourseImage = courseImage;
    }

    public Course(String courseName) {
        CourseName = courseName;
    }

    public Course() {
    }

    public Course(String courseName, String courseImage) {
        CourseName = courseName;
        CourseImage = courseImage;
    }
}

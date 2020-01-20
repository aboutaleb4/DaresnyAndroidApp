package edu.aucegypt.learningcentershub.data;

import java.io.Serializable;


public class Course implements Serializable {
    private String courseName;
  //  private LearningCenter learningCenter;
    private int image;


    public Course(String courseName, int image) {
        this.courseName = courseName;
        //this.learningCenter = learningCenter;
        this.image = image;
    }

    public Course(){
    }



    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

//    public LearningCenter getLearningCenter() {
//        return learningCenter;
//    }
//
//    public void setLearningCenter(LearningCenter learningCenter) {
//        this.learningCenter = learningCenter;
//    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

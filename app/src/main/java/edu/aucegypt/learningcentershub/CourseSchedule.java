package edu.aucegypt.learningcentershub;

public class CourseSchedule {
    String Day, StTime, EndTime;

    public CourseSchedule() {
    }

    public CourseSchedule(String day, String stTime, String endTime) {
        Day = day;
        StTime = stTime;
        EndTime = endTime;
    }

    public void setDay(String day) {
        Day = day;
    }

    public void setStTime(String stTime) {
        StTime = stTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getDay() {
        return Day;
    }

    public String getStTime() {
        return StTime;
    }

    public String getEndTime() {
        return EndTime;
    }
}
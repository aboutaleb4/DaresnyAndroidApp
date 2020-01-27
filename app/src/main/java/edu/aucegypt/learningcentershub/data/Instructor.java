package edu.aucegypt.learningcentershub.data;

public class Instructor {

    int INSTID;
    String Fname;
    String Lname;
    String Bio;

    public int getINSTID() {
        return INSTID;
    }

    public void setINSTID(int INSTID) {
        this.INSTID = INSTID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public Instructor() {
    }

    public Instructor(int INSTID, String fname, String lname, String bio) {
        this.INSTID = INSTID;
        Fname = fname;
        Lname = lname;
        Bio = bio;
    }
}

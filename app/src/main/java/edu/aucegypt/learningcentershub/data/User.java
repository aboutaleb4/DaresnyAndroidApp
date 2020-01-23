package edu.aucegypt.learningcentershub.data;

public class User {
    String UID, Fname, Lname, Email, Pass, PhoneNo;
    String Categories[];
    Boolean IsAdmin;
    public User() {
    }

    public String getUID() {
        return UID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getEmail() {
        return Email;
    }

    public String getPass() {
        return Pass;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public void setCategories(String[] categories) {
        Categories = categories;
    }

    public void setAdmin(Boolean admin) {
        IsAdmin = admin;
    }

    public String[] getCategories() {
        return Categories;
    }

    public Boolean getAdmin() {
        return IsAdmin;
    }

    public User(String UID, String fname, String lname, String email, String pass, String phoneNo, String[] categories, Boolean isAdmin) {
        this.UID = UID;
        Fname = fname;
        Lname = lname;
        Email = email;
        Pass = pass;
        PhoneNo = phoneNo;
        Categories = categories;
        IsAdmin = isAdmin;
    }
}

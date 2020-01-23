package edu.aucegypt.learningcentershub.data;

public class User {
    public User() {
    }

    public User(String UID, String fname, String lname, String email, String pass, String phoneNo, Boolean isAdmin) {
        this.UID = UID;
        Fname = fname;
        Lname = lname;
        Email = email;
        Pass = pass;
        PhoneNo = phoneNo;
        IsAdmin = isAdmin;
    }

    String UID, Fname, Lname, Email, Pass, PhoneNo;
    Boolean IsAdmin;

    void setUID(String UID){
        this.UID = UID;
    }
    void setFname(String Fname){
        this.Fname = Fname;
    }
    void setLname(String Lname){
        this.Lname = Lname;
    }
    void setEmail(String Email){
        this.Email = Email;
    }
    void setPass(String UID){
        this.Pass = UID;
    }
    void setPhoneNo(String PhoneNo){
        this.PhoneNo = PhoneNo;
    }
    void setIsAdmin(Boolean IsAdmin){
        this.IsAdmin = IsAdmin;
    }
    String getUID(){
        return UID;
    }
    String getFname(){
        return Fname;
    }
    String getLname(){
        return Lname;
    }
    String getEmail(){
        return Email;
    }
    String getPass(){
        return UID;
    }
    String getPhoneNo(){
        return PhoneNo;
    }
    Boolean getIsAdmin() {
        return IsAdmin;
    }
}

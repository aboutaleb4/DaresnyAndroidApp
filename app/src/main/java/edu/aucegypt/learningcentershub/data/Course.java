package edu.aucegypt.learningcentershub.data;

import java.io.Serializable;
import java.util.Date;


public class Course implements Serializable {

    int CID;
    int LCID;

   String CourseName;
   String CourseImage;
   Double Price;
   String LCname;
   Double RegFees;
   Date StDate;
   Date EndDate;
   String Description;
   String CatName;
    String LClogo;
    String PhoneNo;
    String Email;

    String Street;
    String BuildingNo;
    String City;
    String Area;

    public String getLClogo() {
        return LClogo;
    }

    public void setLClogo(String LClogo) {
        this.LClogo = LClogo;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getBuildingNo() {
        return BuildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        BuildingNo = buildingNo;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public Double getRegFees() {
        return RegFees;
    }

    public void setRegFees(Double regFees) {
        RegFees = regFees;
    }

    public Date getStDate() {
        return StDate;
    }

    public void setStDate(Date stDate) {
        StDate = stDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getLCname() {
        return LCname;
    }

    public void setLCname(String LCname) {
        this.LCname = LCname;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getLCID() {
        return LCID;
    }

    public void setLCID(int LCID) {
        this.LCID = LCID;
    }

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

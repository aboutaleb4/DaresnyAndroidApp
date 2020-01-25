package edu.aucegypt.learningcentershub.data;

import java.io.Serializable;

public class LearningCenter implements Serializable {
    private int LCID;
    private String LCname;
    private String Logo;

    public void setLCID(int LCID) {
        this.LCID = LCID;
    }

    public void setLCname(String LCname) {
        this.LCname = LCname;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public int getLCID() {
        return LCID;
    }

    public String getLCname() {
        return LCname;
    }

    public String getLogo() {
        return Logo;
    }

    public String getCatName() {
        return CatName;
    }

    public LearningCenter(int LCID, String LCname, String logo, String catName) {
        this.LCID = LCID;
        this.LCname = LCname;
        Logo = logo;
        CatName = catName;
    }

    private String CatName;
}

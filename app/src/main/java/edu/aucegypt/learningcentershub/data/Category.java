package edu.aucegypt.learningcentershub.data;

public class Category {

    String CatName;
    int image;

    public Category() {
    }

    public Category(String catName, int image) {
        CatName = catName;
        this.image = image;
    }
    public String getCatName() {
        return CatName;
    }

    public int getImage() {
        return image;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

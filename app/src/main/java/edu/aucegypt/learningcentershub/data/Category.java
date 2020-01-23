package edu.aucegypt.learningcentershub.data;

public class Category {

    String CatName;

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public Category() {
    }

    public Category(String catName) {
        CatName = catName;
    }

    public Category(String catName, int image) {
        CatName = catName;
        Image = image;
    }

    int Image;
}

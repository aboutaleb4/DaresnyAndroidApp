package edu.aucegypt.learningcentershub.service.data;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Category {

    String CatName;

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public String getCatImage() {
        return CatImage;
    }

    public void setCatImage(String image) {
        CatImage = image;
    }

    public Category() {
    }

    public Category(String catName) {
        CatName = catName;
    }

    public Category(String catName, String catImage) {
        CatName = catName;
        CatImage = catImage;
    }

    String CatImage;


}

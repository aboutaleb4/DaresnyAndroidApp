package edu.aucegypt.learningcentershub.Network;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIcall {



    public APIcall() {
        client = new OkHttpClient();
    }

    OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public final static String url ="http://10.40.37.122:3000/";


    public String post(String APIendpoint, String json) throws IOException {

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url+APIendpoint)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }


    }

    public String get(String APIendpoint) throws IOException {
        Request request = new Request.Builder()
                .url(url+APIendpoint)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}

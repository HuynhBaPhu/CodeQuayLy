package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface BaseAPI {
    String baseURL = "http://192.168.0.104:8081/";// đổi ip của máy chạy server
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy HH:mm:ss")
            .create();
}

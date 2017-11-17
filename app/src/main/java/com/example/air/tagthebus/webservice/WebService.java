package com.example.air.tagthebus.webservice;

import com.example.air.tagthebus.model.Station;
import com.example.air.tagthebus.utils.HttpHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Air on 15/06/2017.
 */

public class WebService {

    private final String url = "http://barcelonaapi.marcpous.com/bus/nearstation/latlon/41.3985182/2.1917991/1.json";
    Gson gson;

    public WebService(){
        gson = new Gson();
    }


    public List<Station> getStations() {
        String response = null;
        try {
            response = HttpHelper.downloadUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject)jsonParser.parse(response);
        JsonObject jsonObject2 = jsonObject.getAsJsonObject("data");
        JsonArray jsonArray = jsonObject2.getAsJsonArray("nearstations"); //JsonArr containt list of nearstations size = 211
        Type listType = new TypeToken<List<Station>>() {}.getType();

        return new Gson().fromJson(jsonArray, listType);
    }
}

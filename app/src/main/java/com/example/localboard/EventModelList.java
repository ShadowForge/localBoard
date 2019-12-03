package com.example.localboard;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventModelList {

    private List<EventModel> eventModelList;
    EventModelList() {}

    EventModelList(JsonArray jsonArray) {
        eventModelList = new ArrayList<EventModel>();
        Gson g = new Gson();
        Log.d("word", "dead yet?");
        for (int i=0; i < jsonArray.size(); i++) {
            EventModel myModel = g.fromJson(jsonArray.get(i), EventModel.class);
            Log.d("word", "Its alive");
            eventModelList.add(myModel);
        }
    }

    public List<EventModel> getEvents() {
        return eventModelList;
    }
    public int getSize() {
        return eventModelList.size();
    }
}

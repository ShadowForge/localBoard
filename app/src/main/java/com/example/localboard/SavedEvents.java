package com.example.localboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.lang.System.in;

public class SavedEvents extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_events);

        url = getString(R.string.baseUrl);
        final RequestQueue queue = Volley.newRequestQueue(SavedEvents.this);
        recyclerView = findViewById(R.id.recyclerViewSaved);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final OnItemClickRecyclerListener listener = new OnItemClickRecyclerListener() {
            @Override
            public void onRecyclerItemClick(EventModel model) {
                Log.d("word", url+"?id=eq."+ model.getId());
                volleyRequestData(url+"?id=eq."+ model.getId(), queue);

                //do some stuff after item click
                return;
            }
        };

        String fileName = "myFile.txt";
        String whichIds = "";

        try {
            FileInputStream fileIn=openFileInput(fileName);
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            char[] inputBuffer= new char[100];
            String s="";
            int charRead;
            Boolean first = true;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer,0,charRead);
                Log.d("Watchamacallit", readstring);
                Scanner scanner = new Scanner(readstring);
                while(scanner.hasNext()) {
                    if (first == false) {
                        whichIds += ",id.eq." + scanner.nextInt();
                    } else {
                        whichIds += "id.eq." + scanner.nextInt();
                        first = false;
                    }
                }
            }
            InputRead.close();
            volleyRequest(url+"?select=id,image,title" +"&or=(" + whichIds+")", queue, listener);


        } catch (Exception e) {
            e.printStackTrace();
        }





        volleyRequest(url+"?select=id,image,title" +"&or=(" + whichIds+")", queue, listener);

    }

    void volleyRequest(final String url, RequestQueue queue, final OnItemClickRecyclerListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //Log.d("word", response);
                        Log.d("word", url);
                        Gson g = new Gson();
                        JsonArray jsonArray = g.fromJson(response, JsonArray.class);
                        EventModelList myList = new EventModelList(jsonArray);
                        myAdapter newAdapter = new myAdapter(myList.getEvents(), listener);
                        recyclerView.setAdapter(newAdapter);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Oh no", "Rip");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void volleyRequestData(String url, RequestQueue queue) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("word", "It is happening");
                        //Log.d("word", response);
                        Gson g = new Gson();
                        JsonArray jsonArray = g.fromJson(response, JsonArray.class);
                        EventModelList myList = new EventModelList(jsonArray);
                        Intent intent = new Intent(SavedEvents.this, EventView.class);
                        intent.putExtra("serializable_data", myList.getEvents().get(0));
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Oh no", "Rip");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}


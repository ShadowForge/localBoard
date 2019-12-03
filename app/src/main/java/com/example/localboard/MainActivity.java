package com.example.localboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.localboard.myAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner spinner = findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sortChoices, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        recyclerView = findViewById(R.id.recyclerView);



        final OnItemClickRecyclerListener listener = new OnItemClickRecyclerListener() {
            @Override
            public void onRecyclerItemClick(EventModel model) {
                Intent intent = new Intent(MainActivity.this, EventView.class);
                intent.putExtra("serializable_data", model);
                startActivity(intent);
                //do some stuff after item click
                return;
            }
        };

        //final myAdapter mAdapter = new myAdapter(generateMyEvents(), listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(mAdapter);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

// Request a string response from the provided URL.
        String url = "http://10.0.0.37:3000/myevents?select=title";
        url = "http://www.mocky.io/v2/5de5a8c12e00006e0031fe2a";
        //url = "http://www.mocky.io/v2/5de5c4082e00002b0031fe72";
        //url = "http://www.mocky.io/v2/5de5c4982e0000880031fe74";
        //url = "http://www.mocky.io/v2/5de5c6032e00005d0031fe78";
        url = "http://98.248.20.28:3000/myevents";


        // Request a string response from the provided URL.
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




        Button button = findViewById(R.id.AddEventButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventViewAdd.class));
            }
        });

        Button savedbutton = findViewById(R.id.SavedEventsButton);

        savedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SavedEvents.class));
            }
        });


    }
    
}

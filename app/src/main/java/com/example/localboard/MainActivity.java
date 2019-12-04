package com.example.localboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.bumptech.glide.load.engine.Resource;
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
    private String url;
    private String order;

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

        url = getString(R.string.baseUrl);
        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        final OnItemClickRecyclerListener listener = new OnItemClickRecyclerListener() {
            @Override
            public void onRecyclerItemClick(EventModel model) {
                Log.d("word", url+"?id=eq."+ model.getId());
                volleyRequestData(url+"?id=eq."+ model.getId(), queue);

                //do some stuff after item click
                return;
            }
        };

        order = getString(R.string.order);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            //parent.getItemAtPosition(pos);
            String[] arr = getResources().getStringArray(R.array.sortOrder);
            order = arr[pos];
            volleyRequest(url+"?select=id,title,image" + order, queue, listener);
        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
            }
        });



        //Recycler View Stuff
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



//Add Event Button Stuff
        Button button = findViewById(R.id.AddEventButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventViewAdd.class));
            }
        });


//Save Button Stuff
        Button savedbutton = findViewById(R.id.SavedEventsButton);
        savedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SavedEvents.class));
            }
        });


    }


    void volleyRequest(String url, RequestQueue queue, final OnItemClickRecyclerListener listener) {
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
                        Intent intent = new Intent(MainActivity.this, EventView.class);
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

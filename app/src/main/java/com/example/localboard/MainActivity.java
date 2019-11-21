package com.example.localboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.localboard.myAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAdapter adapter = new myAdapter(generateMyList());
        recyclerView = findViewById(R.id.recyclerView);

        textView = findViewById(R.id.testView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

// Request a string response from the provided URL.
        String url = "http://www.mocky.io/v2/5dd5d3873300006b00f3832b";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        textView.setText("That did not work");
                    }
                });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    private List<myViewModel> generateMyList() {
        List<myViewModel> myViewModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            myViewModelList.add(new myViewModel(String.format(Locale.US, "This is item %d", i)));
        }

        return myViewModelList;
    }
}

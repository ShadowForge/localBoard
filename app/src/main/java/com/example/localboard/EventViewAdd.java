package com.example.localboard;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EventViewAdd extends AppCompatActivity {

    private EditText description;
    private EditText link;
    private EditText title;
    private EditText image;
    private EditText cost;
    private EditText time;
    private Calendar myCalendar;
    private EditText address;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view_add);

        addButton = findViewById(R.id.AddYourEventButton);
        description = findViewById(R.id.EditTextViewAdd);
        link = findViewById(R.id.linkTextViewAdd);
        title = findViewById(R.id.titleAdd);
        cost = findViewById(R.id.costAdd);
        time = findViewById(R.id.timeAdd);
        address = findViewById(R.id.addressAdd);
        image = findViewById(R.id.imageAdd);


        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EventViewAdd.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String d = "20" + time.getText().toString().substring(6) +"-" + time.getText().toString().substring(0,2) + "-" + time.getText().toString().substring(3,5);
                Log.d("word", d);

                RequestQueue queue = Volley.newRequestQueue(EventViewAdd.this);
                String url = "http://98.248.20.28:3000/myevents";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("word", "Model added");

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Oh no", "It wasn't added");
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("title", title.getText().toString());
                        params.put("link", link.getText().toString());
                        params.put("image", image.getText().toString());
                        params.put("description", description.getText().toString());
                        params.put("address", address.getText().toString());
                        params.put("time", d);
                        params.put("price", cost.getText().toString());
                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(stringRequest);


                startActivity(new Intent(EventViewAdd.this, MainActivity.class));
            }
        });

    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        time.setText(sdf.format(myCalendar.getTime()));
    }
}


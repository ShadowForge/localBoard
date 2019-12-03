package com.example.localboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventViewAdd extends AppCompatActivity {

    private EditText description;
    private TextView link;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view_add);

        Button addbutton = findViewById(R.id.AddYourEventButton);
        final EditText description = findViewById(R.id.EditViewAddDescription);
        final String st = description.getText().toString();

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventModel model = new EventModel(st,link.getText().toString());
                startActivity(new Intent(EventViewAdd.this, MainActivity.class));
            }
        });

    }
}


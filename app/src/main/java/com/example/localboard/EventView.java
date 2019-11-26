package com.example.localboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EventView extends AppCompatActivity {

    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        //Intent intent = getIntent();
        EventModel receivedModel = (EventModel) getIntent().getSerializableExtra("serializable_data");

        description = findViewById(R.id.descriptionTextView);
        description.setText(receivedModel.getDescription());
    }
}


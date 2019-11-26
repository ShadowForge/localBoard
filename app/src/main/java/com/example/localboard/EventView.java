package com.example.localboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class EventView extends AppCompatActivity {

    private TextView description;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        //Intent intent = getIntent();
        EventModel receivedModel = (EventModel) getIntent().getSerializableExtra("serializable_data");

        description = findViewById(R.id.descriptionTextView);
        image = findViewById(R.id.eventImageView);


        description.setText(receivedModel.getDescription());
        Glide.with(this)
                .load(receivedModel.getImage())
                .into(image);
    }
}


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
    private TextView cost, time, title, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        EventModel receivedModel = (EventModel) getIntent().getSerializableExtra("serializable_data");

        description = findViewById(R.id.descriptionTextView);
        cost = findViewById(R.id.costBox);
        time = findViewById(R.id.timeBox);
        title = findViewById(R.id.titleBox);
        link = findViewById(R.id.linkTextView);
        image = findViewById(R.id.eventImageView);

        title.setText(receivedModel.getTitle());
        cost.setText(String.valueOf(receivedModel.getCost()));
        time.setText(receivedModel.getTime());
        link.setText(receivedModel.getLink());
        description.setText(receivedModel.getDescription());
        Glide.with(this)
                .load(receivedModel.getImage())
                .into(image);
    }
}


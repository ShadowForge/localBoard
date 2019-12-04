package com.example.localboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class EventView extends AppCompatActivity {

    private TextView description;
    private ImageView image;
    private TextView cost, time, title, link, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        final EventModel receivedModel = (EventModel) getIntent().getSerializableExtra("serializable_data");

        description = findViewById(R.id.descriptionTextView);
        cost = findViewById(R.id.costBox);
        time = findViewById(R.id.timeBox);
        title = findViewById(R.id.titleBox);
        link = findViewById(R.id.linkTextView);
        image = findViewById(R.id.eventImageView);
        address = findViewById(R.id.addressBox);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fileName = "myFile.txt";
                String whichIds = "";

                try {
                    FileInputStream fileIn= openFileInput(fileName);
                    InputStreamReader InputRead= new InputStreamReader(fileIn);
                    char[] inputBuffer= new char[100];
                    String s="";
                    int charRead;
                    while ((charRead=InputRead.read(inputBuffer))>0) {
                        // char to string conversion
                        String readstring = String.copyValueOf(inputBuffer,0,charRead);
                        Log.d("Watchamacallit", readstring);
                            whichIds += readstring;
                    }
                    InputRead.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                String data= String.valueOf(receivedModel.getId());
                try {
                    FileOutputStream fOut = openFileOutput(fileName, Context.MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fOut);
                    //outputWriter.write(data);
                    outputWriter.write(whichIds + " " + data);
                    outputWriter.close();
                    Toast.makeText(getBaseContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        title.setText(receivedModel.getTitle());
        cost.setText(String.valueOf(receivedModel.getCost()));
        time.setText(receivedModel.getTime());
        link.setText(receivedModel.getLink());
        description.setText(receivedModel.getDescription());
        address.setText(receivedModel.getAddress());
        Glide.with(this)
                .load(receivedModel.getImage())
                .into(image);
    }
}


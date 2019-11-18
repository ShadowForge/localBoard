package com.example.localboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.localboard.model.EventModel;


public class EventViewAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private String TAG = "EventViewAdapter";

    private List<EventModel> eventList;

    public EventViewAdapter(List<EventModel> collection) {
        eventList = collection;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);
        return new EventViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        EventModel model = eventList.get(position);
        eventViewHolder.bindView(model);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}

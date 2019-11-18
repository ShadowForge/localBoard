package com.example.localboard;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.localboard.model.EventModel;

public class EventViewHolder extends RecyclerView.ViewHolder {
    private TextView eventName;
    private TextView eventDesc;

    public EventViewHolder(View itemView) {
        super(itemView);
        eventName = (TextView) itemView.findViewById(R.id.event_name);
        eventDesc = (TextView) itemView.findViewById(R.id.event_description);
    }

    public void bindView(EventModel model) {
        eventName.setText(model.getEventName());
        eventDesc.setText(model.getEventDescription());
    }

}

package com.example.localboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class myViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout myLinearLayout;
    private TextView myTextView;
    private ImageView myImageView;

    public myViewHolder(final View itemView) {
        super(itemView);
        myLinearLayout = itemView.findViewById(R.id.layoutRecycler);
        myImageView = itemView.findViewById(R.id.imageRecycler);
        myTextView = itemView.findViewById(R.id.simple_text);

    }

    public void bindData(final EventModel model, final OnItemClickRecyclerListener listener) {
        Glide.with(this.itemView)
                .load(model.getImage())
                .into(myImageView);
        myTextView.setText(model.getTitle());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecyclerItemClick(model);
            }
        });
    }

}
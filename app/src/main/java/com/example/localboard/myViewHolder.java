package com.example.localboard;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {
    private TextView myTextView;

    public myViewHolder(final View itemView) {
        super(itemView);
        myTextView = itemView.findViewById(R.id.simple_text);
    }

    public void bindData(final myViewModel viewModel) {
        myTextView.setText(viewModel.getMyText());
    }

}
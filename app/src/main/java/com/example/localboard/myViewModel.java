package com.example.localboard;

import androidx.annotation.NonNull;

public class myViewModel {
    private String myText;

    public myViewModel(@NonNull final String myText) {
        setMyText(myText);
    }

    @NonNull
    public String getMyText() {
        return myText;
    }

    public void setMyText(@NonNull final String myText) {
        this.myText = myText;
    }
}

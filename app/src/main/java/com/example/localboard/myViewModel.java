package com.example.localboard;

import androidx.annotation.NonNull;

public class myViewModel {
    private String image;
    private String myText;

    public myViewModel(@NonNull final String myText, @NonNull final String image) {
        setMyText(myText);
        setMyImage(image);
    }

    @NonNull
    public String getMyText() {
        return myText;
    }

    @NonNull
    public String getMyImage() { return image; }

    public void setMyText(@NonNull final String myText) {
        this.myText = myText;
    }

    public void setMyImage(@NonNull final String image) { this.image = image; }
}

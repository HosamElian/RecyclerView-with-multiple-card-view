package com.example.recyclerviewwithdifferentcardview.models;

public class Facebook {
    private String FaceBookTitle;

    public int getImage() {
        return Image;
    }

    private int Image;
    public String getFaceBookTitle() {
        return FaceBookTitle;
    }

    public Facebook(String faceBookTitle, int image) {
        FaceBookTitle = faceBookTitle;
        Image = image;
    }
}

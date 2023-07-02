package com.example.recyclerviewwithdifferentcardview.models;

public class Item {
    public int getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }

    public Item(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    private int type;
    private Object object;
}

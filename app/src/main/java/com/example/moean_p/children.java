package com.example.moean_p;

import android.widget.DatePicker;

public class children {
    String id;
    String name;
    //DatePicker date;

    public children(){

    }

    public children(String id, String name) {
        this.id = id;
        this.name = name;
        // the date
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    //dont forget the date
}
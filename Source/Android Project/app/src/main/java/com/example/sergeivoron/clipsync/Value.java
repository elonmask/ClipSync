package com.example.sergeivoron.clipsync;


import java.io.Serializable;

public class Value implements Serializable {

   public String data;

    public Value() {}

    public Value(String data) {

        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Value(Value val) {

        this.data = val.getData();
    }
}

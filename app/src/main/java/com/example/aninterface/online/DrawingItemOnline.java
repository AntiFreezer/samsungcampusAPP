package com.example.aninterface.online;

import com.google.gson.annotations.SerializedName;

public class DrawingItemOnline {

    @SerializedName("type")
    private final String type;

    @SerializedName("color")
    private final int color;

    @SerializedName("width")
    private final int width;

    @SerializedName("fog")
    private final boolean fog;

    @SerializedName("lx")
    private final float lx;

    @SerializedName("ly")
    private final float ly;

    @SerializedName("mx")
    private final float mx;

    @SerializedName("my")
    private final float my;

    public DrawingItemOnline(int color, boolean fog, String type, int width, float mx, float my, float lx, float ly){
        this.color = color;
        this.fog = fog;
        this.type = type;
        this.width = width;
        this.mx = mx;
        this.my = my;
        this.lx = lx;
        this.ly = ly;
    }

    //GETTERS
    public float getMx() {return mx;}
    public float getMy() {return my;}
    public float getLx() {return lx;}
    public float getLy() {return ly;}

    public String getType() { return type; }
    public int getColor() { return color; }
    public int getWidth() { return width; }
    public boolean hasFog() { return fog; }
}

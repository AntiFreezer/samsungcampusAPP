package com.example.aninterface.offline;

import android.graphics.Path;

public class DrawingItemOffline {
    private boolean isPath;
    private String type;
    private int color;
    private int width;
    private boolean fog;
    private Path path;
    private float x;
    private float y;

    public DrawingItemOffline(float x, float y, String type, int color, int width, boolean fog) {
        this.isPath = false;
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
        this.fog = fog;
        this.type = type;
    }

    public DrawingItemOffline(Path path, int color, int width, boolean fog) {
        this.isPath = true;
        this.path = path;
        this.color = color;
        this.width = width;
        this.fog = fog;
    }

    public boolean isPath() { return isPath; }
    public String getType() { return type; }
    public int getColor() { return color; }
    public int getWidth() { return width; }
    public boolean hasFog() { return fog; }
    public Path getPath() { return path; }
    public float getX() { return x; }
    public float getY() { return y; }
} 
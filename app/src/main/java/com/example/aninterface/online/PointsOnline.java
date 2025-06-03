package com.example.aninterface.online;

import android.graphics.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointsOnline {
    //paths
    private final List<Path> paths;
    private final List<Integer> pathColors;
    private final List<Integer> pathWidths;
    private final List<Boolean> pathFogs;
    //shapes
    private final List<float[]> points;
    private final List<Integer> shapeColors;
    private final List<Integer> shapeWidths;
    private final List<Boolean> shapeFogs;
    private final List<DrawingItemOnline> drawingItems;
    public PointsOnline() {
        //paths
        paths = Collections.synchronizedList(new ArrayList<>());
        pathColors = Collections.synchronizedList(new ArrayList<>());
        pathWidths = Collections.synchronizedList(new ArrayList<>());
        pathFogs = Collections.synchronizedList(new ArrayList<>());
        //shapes
        points = Collections.synchronizedList(new ArrayList<>());
        shapeColors = Collections.synchronizedList(new ArrayList<>());
        shapeWidths = Collections.synchronizedList(new ArrayList<>());
        shapeFogs = Collections.synchronizedList(new ArrayList<>());
        //Easter egg
        drawingItems = new ArrayList<>();
    }
    public void clear() {
        //paths
        paths.clear();
        pathColors.clear();
        pathFogs.clear();
        pathWidths.clear();
        //shapes
        points.clear();
        shapeColors.clear();
        shapeFogs.clear();
        shapeWidths.clear();
        //for egg
        drawingItems.clear();
    }
    //shapes
    public void add(int color, boolean fog, String type, int width, float xMoveTo, float yMoveTo, float xLineTo, float yLineTo) {
        drawingItems.add(new DrawingItemOnline (color, fog, type, width, xMoveTo, yMoveTo, xLineTo, yLineTo));
    }
    //One more egg??
    public List<DrawingItemOnline> getAllItems() {
        return new ArrayList<>(drawingItems);
    }

    public DrawingItemOnline getItem(){
        return drawingItems.get(drawingItems.size() - 1);
    }

}

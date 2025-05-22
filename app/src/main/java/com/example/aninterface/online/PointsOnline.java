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
    private final List<String> shapeTypes;
    private List<DrawingItemOnline> drawingItems;
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
        shapeTypes = new ArrayList<>();
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
        shapeTypes.clear();
    }
    //shapes
    public void addShape(float[] point, String type, int color, int width, boolean fog) {
        drawingItems.add(new DrawingItemOnline (point[0], point[1], type, color, width, fog));
    }
    //paths

    public void addPath(Path path, int color, int width, boolean fog) {
        drawingItems.add(new DrawingItemOnline(path, color, width, fog));
    }
    //One more egg??
    public List<DrawingItemOnline> getAllItems() {
        return new ArrayList<>(drawingItems);
    }

}

package com.example.aninterface.offline;

import android.graphics.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointsOffline {
    //paths
    private final List<Path> paths;
    private final List<Integer> pathColors;
    private final List<Integer> pathWidths;
    private final List<Boolean> pathFogs;
    private final List<String> pathTypes;
    //shapes
    private final List<float[]> points;
    private final List<Integer> shapeColors;
    private final List<Integer> shapeWidths;
    private final List<Boolean> shapeFogs;
    private final List<String> shapeTypes;
    public PointsOffline() {
        //paths
        paths = Collections.synchronizedList(new ArrayList<>());
        pathColors = Collections.synchronizedList(new ArrayList<>());
        pathWidths = Collections.synchronizedList(new ArrayList<>());
        pathFogs = Collections.synchronizedList(new ArrayList<>());
        pathTypes = Collections.synchronizedList(new ArrayList<>());
        //shapes
        points = Collections.synchronizedList(new ArrayList<>());
        shapeColors = Collections.synchronizedList(new ArrayList<>());
        shapeWidths = Collections.synchronizedList(new ArrayList<>());
        shapeFogs = Collections.synchronizedList(new ArrayList<>());
        shapeTypes = Collections.synchronizedList(new ArrayList<>());
    }
    public void clear() {
        //paths
        paths.clear();
        pathColors.clear();
        pathFogs.clear();
        pathWidths.clear();
        pathTypes.clear();
        //shapes
        points.clear();
        shapeColors.clear();
        shapeFogs.clear();
        shapeWidths.clear();
        shapeTypes.clear();
    }
    //squares, circles
    public synchronized void addPoints(float x, float y, int color, int width, boolean fog, String shapeType) {
        points.add(new float[] {x, y});
        shapeColors.add(color);
        shapeWidths.add(width);
        shapeFogs.add(fog);
        shapeTypes.add(shapeType);
    }
    public List<float[]> getPoints() {
        return points;
    }

    public List<Integer> getShapeColors() {
        return shapeColors;
    }

    public List<Boolean> getShapeFogs() {
        return shapeFogs;
    }

    public List<Integer> getShapeWidths() {
        return shapeWidths;
    }

    public List<String> getShapeTypes() {
        return shapeTypes;
    }

    //paths
    public synchronized void addPath(Path path, int color, int width, boolean fog, String pathType) {
        paths.add(path);
        pathColors.add(color);
        pathWidths.add(width);
        pathFogs.add(fog);
        pathTypes.add(pathType);
    }

    public List<Path> getPaths() {
        return paths;
    }

    public List<Integer> getPathColors() {
        return pathColors;
    }

    public List<Integer> getPathWidths() {
        return pathWidths;
    }

    public List<Boolean> getPathFogs() {
        return pathFogs;
    }

    public List<String> getPathTypes() {
        return pathTypes;
    }
}

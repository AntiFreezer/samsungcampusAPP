package com.example.aninterface.offline;

import android.graphics.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Points {
    private final List<Path> paths;
    private final List<float[]> points;
    public Points() {
        paths = Collections.synchronizedList(new ArrayList<>());
        points = Collections.synchronizedList(new ArrayList<>());
    }
    public synchronized void addPoints(float x, float y){
        points.add(new float[] {x, y});
    }
    public synchronized void addPath(Path path){
        paths.add(path);
    }
    public List<Path> getPaths(){
        return paths;
    }
    public List<float []> getPoints(){
        return points;
    }
    public void clear(){
        paths.clear();
        points.clear();
    }
}

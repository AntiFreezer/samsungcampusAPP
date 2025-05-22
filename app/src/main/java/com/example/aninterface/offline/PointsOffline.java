package com.example.aninterface.offline;

import android.graphics.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointsOffline {
    private final List<DrawingItemOffline> drawingItems;

    public PointsOffline() {
        drawingItems = Collections.synchronizedList(new ArrayList<>());
    }

    public void clear() {
        drawingItems.clear();
    }

    public void addShape(float[] point, String type, int color, int width, boolean fog) {
        drawingItems.add(new DrawingItemOffline(point[0], point[1], type, color, width, fog));
    }

    public void addPath(Path path, int color, int width, boolean fog) {
        drawingItems.add(new DrawingItemOffline(path, color, width, fog));
    }

    public List<DrawingItemOffline> getAllItems() {
        return new ArrayList<>(drawingItems);
    }
}

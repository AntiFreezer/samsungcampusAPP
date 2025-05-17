package com.example.aninterface.offline;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.SurfaceHolder;

import java.util.List;
import java.util.Objects;

public class DrawingThreadOffline extends Thread{
    //VARIABLES
    private boolean running;
    private final Paint paint;
    private int currentColor;
    private String currentShape;
    private int currentWidth;
    private boolean currentFog;
    private final SurfaceHolder surfaceHolder;
    private float x;
    private float y;
    private final PointsOffline points;
    public DrawingThreadOffline(SurfaceHolder surfaceHolder, PointsOffline points){
        running = true;
        this.points = points;
        points.clear();
        this.surfaceHolder = surfaceHolder;

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        currentColor = Color.RED;
        currentShape = "LINE";
        currentFog = false;
        currentWidth = 50;
        x = 0;
        y = 0;
    }


    //SETTERS
    public synchronized void setCurrentColor(int currentColor){
        this.currentColor = currentColor;
    }
    public synchronized void setCurrentShape(String currentShape) {
        this.currentShape = currentShape;
    }
    public synchronized void setCurrentWidth(int currentWidth){
        this.currentWidth = currentWidth;
    }
    public synchronized void setCurrentFog(boolean currentFog){
        this.currentFog = currentFog;
    }
    public synchronized void setStopRequest(){
        running = false;
    }
    private synchronized void setPoints(){
        if(!points.getPoints().isEmpty()){
            this.x = points.getPoints().get(points.getPoints().size() - 1)[0];
            this.y = points.getPoints().get(points.getPoints().size() - 1)[1];
        }
    }
    //GETTERS
    public String getCurrentShape(){
        return this.currentShape;
    }
    public int getCurrentWidth(){
        return currentWidth;
    }
    public int getCurrentColor(){
        return currentColor;
    }

    public boolean getCurrentFog() {
        return currentFog;
    }

    //THREAD
    @Override
    public void run(){
        while (running){
            Canvas canvas = surfaceHolder.lockCanvas();
            if(canvas != null){
                try{
                    setPoints();
                    // Draw all paths first
                    List<Path> paths = points.getPaths();
                    List<Integer> pathColors = points.getPathColors();
                    List<Integer> pathWidths = points.getPathWidths();
                    List<Boolean> pathFogs = points.getPathFogs();
                    List<String> pathTypes = points.getPathTypes();
                    
                    synchronized (paths) {
                        if (!paths.isEmpty()) {
                            for (int i = 0; i < paths.size(); i++) {
                                if (Objects.equals(pathTypes.get(i), "ERASER")) {
                                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                                } else {
                                    paint.setColor(pathColors.get(i));
                                    paint.setXfermode(null);
                                }
                                paint.setStrokeWidth(pathWidths.get(i));
                                if (pathFogs.get(i)) {
                                    paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
                                } else {
                                    paint.setMaskFilter(null);
                                }
                                canvas.drawPath(paths.get(i), paint);
                            }
                        }
                    }

                    // Then draw all shapes
                    List<float[]> shapeLists = points.getPoints();
                    List<Integer> shapeColors = points.getShapeColors();
                    List<Integer> shapeWidths = points.getShapeWidths();
                    List<Boolean> shapeFogs = points.getShapeFogs();
                    List<String> shapeTypes = points.getShapeTypes();
                    
                    synchronized (shapeLists){
                        for (int i = 0; i < shapeLists.size(); i++) {
                            float[] point = shapeLists.get(i);
                            if (Objects.equals(shapeTypes.get(i), "ERASER")) {
                                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                                paint.setStyle(Paint.Style.FILL);
                            } else {
                                paint.setColor(shapeColors.get(i));
                                paint.setXfermode(null);
                                paint.setStyle(Paint.Style.STROKE);
                            }
                            paint.setStrokeWidth(shapeWidths.get(i));
                            if (shapeFogs.get(i)) {
                                paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
                            } else {
                                paint.setMaskFilter(null);
                            }

                            switch (shapeTypes.get(i)) {
                                case "SQUARE":
                                    float halfWidth = shapeWidths.get(i) / 2f;
                                    canvas.drawRect(point[0] - halfWidth, point[1] - halfWidth,
                                            point[0] + halfWidth, point[1] + halfWidth, paint);
                                    break;
                                case "CIRCLE":
                                    float radius = shapeWidths.get(i) / 2f;
                                    canvas.drawCircle(point[0], point[1], radius, paint);
                                    break;
                            }
                        }
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            try {
                Thread.sleep(16);
            } catch (InterruptedException ignore) {
            }
        }
    }
}
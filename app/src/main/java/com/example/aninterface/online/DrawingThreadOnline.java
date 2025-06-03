package com.example.aninterface.online;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawingThreadOnline extends Thread{
    //VARIABLES
    private boolean running;
    private final Paint paint;
    private int currentColor;
    private String currentShape;
    private int currentWidth;
    private boolean currentFog;
    private final SurfaceHolder surfaceHolder;
    private final PointsOnline points;

    public DrawingThreadOnline(SurfaceHolder surfaceHolder, PointsOnline points){
        running = true;
        this.points = points;
        points.clear();
        this.surfaceHolder = surfaceHolder;

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new CornerPathEffect(1));
        paint.setAntiAlias(true);
        paint.setDither(true);
        currentColor = Color.RED;
        currentShape = "LINE";
        currentFog = false;
        currentWidth = 50;


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

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    synchronized (points) {
                        synchronized (this) {
                            ApiService apiService = ApiClient.getApiService();
                            Call<List<DrawingItemOnline>> call = apiService.getDataFromServer();

                            call.enqueue(new Callback<>() {
                                @Override
                                public void onResponse(@NonNull Call<List<DrawingItemOnline>> call, @NonNull Response<List<DrawingItemOnline>> response) {
                                    if (response.isSuccessful()) {
                                        List<DrawingItemOnline> drawings = response.body();
                                        if (drawings != null && !drawings.isEmpty()) {
                                            DrawingItemOnline drawing = drawings.get(drawings.size() - 1);
                                            points.add(
                                                    drawing.getColor(), drawing.hasFog(),
                                                    drawing.getType(), drawing.getWidth(),
                                                    drawing.getMx(), drawing.getMy(),
                                                    drawing.getLx(), drawing.getLy()
                                            );
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<List<DrawingItemOnline>> call, @NonNull Throwable t) {
                                    System.out.println("=== Ошибка сети при получении ===");
                                    System.out.println("Сообщение: " + t.getMessage());
                                    System.out.println("Причина: " + t.getCause());
                                    //Toast.makeText(DrawingThreadOnline.this.getContext(), "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    System.err.println(t.getMessage());
                                }
                            });
                        }

                        // Get all the items
                        List<DrawingItemOnline> allItems = points.getAllItems();

                        for (DrawingItemOnline item : allItems) {
                            float mX = item.getMx();
                            float mY = item.getMy();
                            float lX = item.getLx();
                            float lY = item.getLy();
                            float half = item.getWidth() / 2f;
                            paint.setColor(item.getColor());
                            paint.setStrokeWidth(item.getWidth());
                            paint.setMaskFilter(item.hasFog() ?
                                    new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL) : null);

                            switch (item.getType()) {
                                case "SQUARE":
                                    canvas.drawRect(
                                            mX - half, mY - half,
                                            mX + half, mY + half,
                                            paint
                                    );
                                    break;
                                case "CIRCLE":
                                    canvas.drawCircle(
                                            mX, mY, half,
                                            paint
                                    );
                                    break;
                                case "LINE":
                                case "ERASER":
                                    Path path = new Path();
                                    path.moveTo(mX, mY);
                                    path.lineTo(lX, lY);
                                    canvas.drawPath(path, paint);
                                    break;
                            }
                        }
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
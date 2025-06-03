package com.example.aninterface.online;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class DrawingOnline extends SurfaceView {
    private DrawingThreadOnline drawingThreadOnline;
    private final PointsOnline points;
    float mX;
    float mY;
    float lX;
    float lY;
    int width;
    String type;
    int color;
    boolean fog;

    public DrawingOnline(Context context){
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        points = new PointsOnline();
        points.clear();
        drawingThreadOnline = new DrawingThreadOnline(surfaceHolder, points);
        updateDrawingParameters();
        init();
    }

    public DrawingOnline(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        points = new PointsOnline();
        init();
    }

    private void updateDrawingParameters() {
        type = drawingThreadOnline.getCurrentShape();
        width = drawingThreadOnline.getCurrentWidth();
        color = Objects.equals(type, "ERASER") ? Color.BLACK : drawingThreadOnline.getCurrentColor();
        fog = !Objects.equals(type, "ERASER") && drawingThreadOnline.getCurrentFog();
    }

    public DrawingThreadOnline getDrawingThread(){
        return drawingThreadOnline;
    }

    public void init(){
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                drawingThreadOnline = new DrawingThreadOnline(holder, points);
                drawingThreadOnline.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                //bruh
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                //Уничтожаем потзок
                drawingThreadOnline = new DrawingThreadOnline(holder, points);
                drawingThreadOnline.setStopRequest();
                boolean retry = true;
                while (retry) {
                    try {
                        drawingThreadOnline.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
                synchronized (this) {
        // Добавляем запрос на очистку данных
        ApiService apiService = ApiClient.getApiService();
        Call<Void> call = apiService.clearAllData();
        call.enqueue(new Callback<>() {
             @Override
             public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {}

             @Override
             public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {}
                    });
                }
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        updateDrawingParameters();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                mY = event.getY();
                lX = mX;
                lY = mY;
                points.clear();
                if (Objects.equals(type, "CIRCLE") || Objects.equals(type, "SQUARE")) {
                    points.add(
                            color, fog,
                            type, width,
                            mX, mY,
                            lX, lY
                    );
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(Objects.equals(type, "LINE") || Objects.equals(type, "ERASER")) {
                    lX = event.getX();
                    lY = event.getY();
                    points.add(
                            color, fog,
                            type, width,
                            mX, mY,
                            lX, lY
                    );
                    mX = lX;
                    mY = lY;
                }
                break;
            case MotionEvent.ACTION_UP:
                // Отправляем на сервер данные

                if(!points.getAllItems().isEmpty()) {
                    List<DrawingItemOnline> items = points.getAllItems();

                    for (DrawingItemOnline item : items) {

                        synchronized (this) {

                            ApiService apiService = ApiClient.getApiService();
                            Call<Void> call = apiService.addDataToServer(item);
                            call.enqueue(new Callback<>() {
                                @Override
                                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {}

                                @Override
                                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {}
                            });
                        }
                    }
                }
                break;
            }
        return true;
    }
}

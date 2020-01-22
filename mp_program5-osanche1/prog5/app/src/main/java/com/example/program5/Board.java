package com.example.program5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class Board extends View {

    private static final int thickness = 5;
    private static final int margin = 20;
    private static final int  symbol_girth = 15;
    private int width, height, universalW, universalH;
    private Paint gridPaint, oPaint, xPaint;
    private TTTEngine myEngine;
    private MainActivity activity;

    public void setMainActivity(MainActivity a) {
        activity = a;
    }

    public void setEngine(TTTEngine e) {
        myEngine = e;
    }

    public Board(Context context){
        super(context);
    }

    public Board(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        gridPaint = new Paint();
        oPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oPaint.setColor(Color.RED);
        oPaint.setStyle(Paint.Style.STROKE);
        oPaint.setStrokeWidth(symbol_girth);
        xPaint = new Paint(oPaint);
        xPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        universalW = (width - thickness) / 3;
        universalH = (height - thickness) / 3;
        setMeasuredDimension(width, height);
    }

    private void drawSymbol(Canvas canvas, char c, int x, int y) {
        if (c == 'O') {
            float cx = (universalW * x) + universalW / 2;
            float cy = (universalH * y) + universalH / 2;
            canvas.drawCircle(cx, cy, Math.min(universalW, universalH) / 2 - margin * 2, oPaint);
        }
        else if (c == 'X') {
            float startX = (universalW * x) + margin;
            float startY = (universalH * y) + margin;
            float endX = startX + universalW - margin * 2;
            float endY = startY + universalH - margin;
            canvas.drawLine(startX, startY, endX, endY, xPaint);

            float startX2 = (universalW * (x + 1)) - margin;
            float startY2 = (universalH * y) + margin;
            float endX2 = startX2 - universalW + margin * 2;
            float endY2 = startY2 + universalH - margin;
            canvas.drawLine(startX2, startY2, endX2, endY2, xPaint);
        }
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 2; i++) {
            // for vertical grid marks
            float left = universalW * (i + 1);
            float right = left + thickness;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, gridPaint);

            // for horizontal grid marks
            float left2 = 0;
            float right2 = width;
            float top2 = universalH * (i + 1);
            float bottom2 = top2 + thickness;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }
    }

    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                drawSymbol(canvas, myEngine.getMoves(i, j), i, j);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        drawBoard(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!myEngine.isFinished() && event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX() / universalW);
            int y = (int) (event.getY() / universalH);
            char win = myEngine.makeMove(x, y);
            invalidate();

            if (win != ' ') {
                activity.gameEnded(win);
            }
        }
        return super.onTouchEvent(event);
    }






}

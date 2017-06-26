package com.cory.projects.familytree;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by unouser on 6/26/2017.
 */

public class CanvasView extends View implements GestureDetector.OnGestureListener {
    private Paint paint;
    private float AXIS_X_MIN = getPaddingLeft();
    private float AXIS_Y_MIN = getPaddingBottom();
    private float AXIS_X_MAX = getWidth() - getPaddingRight();
    private float AXIS_Y_MAX = getHeight() - getPaddingTop();
    private RectF container = new RectF(AXIS_X_MIN, AXIS_Y_MIN, AXIS_X_MAX, AXIS_Y_MAX);
    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();

    }
    @Override
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStrokeWidth(10);
        canvas.drawLine(getPaddingLeft(),getPaddingBottom()/2,getWidth() - getPaddingRight(),getPaddingBottom()/2, paint);
        canvas.drawLine(getWidth()/2, getPaddingBottom(), getWidth()/2, getHeight() - getPaddingTop(), paint);
        System.out.printf("%d %d %d %d %d %d", getWidth(), getHeight(),getPaddingBottom(), getPaddingTop(), getPaddingLeft(), getPaddingRight());
    }
    @TargetApi(16)
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        // Scrolling uses math based on the viewport (as opposed to math using pixels).

        // Pixel offset is the offset in screen pixels, while viewport offset is the
        // offset within the current viewport.
        float X = (distanceX * container.width()
                / container.width());
        float Y = (-distanceY * container.height()
                / container.height());

        // Updates the viewport, refreshes the display.


        float curWidth = container.width();
        float curHeight = container.height();
        X = Math.max(AXIS_X_MIN, Math.min(X, AXIS_X_MAX - curWidth));
        Y = Math.max(AXIS_Y_MIN + curHeight, Math.min(Y, AXIS_Y_MAX));
        container.set(X, Y - curHeight, X + curWidth, Y);

        // Invalidates the View to update the display.
        postInvalidateOnAnimation();
        return true;
    }
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float x, float y) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }


}

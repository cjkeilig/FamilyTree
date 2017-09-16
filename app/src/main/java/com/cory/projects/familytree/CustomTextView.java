package com.cory.projects.familytree;

import org.w3c.dom.Text;

/**
 * Created by unouser on 9/16/2017.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class CustomTextView extends View {

    private Paint paint;
    public TextView node;

    GestureDetectorCompat gestureDetector;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {

                Log.println(Log.INFO, "msg", String.format("On Scroll: %f %f %f %f %d %d", distanceX, distanceY, getX(), getY(), getScrollX(), getScrollY()));

                int X = (int) (getScrollX() + distanceX);
                int Y = (int) (getScrollY() + distanceY);
                scrollTo(X, Y);
                setScrollX(getScrollX() + (int) (distanceX));
                setScrollY(getScrollY() + (int) (distanceY));

                // Invalidates the View to update the display.
                invalidate();
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
    }

        @Override
        public void onDraw (Canvas canvas){

            paint.setColor(Color.rgb(0, 0, 0));
            paint.setStrokeWidth(10);
            float height = getLayoutParams().height;
            float width = getLayoutParams().width;
            canvas.drawLine(getPaddingLeft(), height / 2, width - getPaddingRight(), height / 2, paint);
            canvas.drawLine(width / 2, getPaddingTop(), width / 2, height - getPaddingTop(), paint);
            Log.println(Log.INFO, "msg", String.format("On Draw: %d %d %d %d %d %d %f %f", getWidth(), getHeight(), getPaddingBottom(), getPaddingTop(), getPaddingLeft(), getPaddingRight(), width, height));
        }

        // The ‘active pointer’ is the one currently moving our object.
        private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;
        private float mLastTouchX;
        private float mLastTouchY;
        private float mPosX, mPosY;

        @Override
        public boolean onTouchEvent (MotionEvent ev){
            Log.println(Log.INFO, "msg", String.format("On Touch: %d %d %d %d %d %d", getWidth(), getHeight(), getPaddingBottom(), getPaddingTop(), getPaddingLeft(), getPaddingRight()));

            gestureDetector.onTouchEvent(ev);
            return true;

        }
    }
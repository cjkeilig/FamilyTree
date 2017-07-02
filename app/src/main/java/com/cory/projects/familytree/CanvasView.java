package com.cory.projects.familytree;

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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
/**
 * Created by unouser on 6/26/2017.
 */

public class CanvasView extends View {

    private Paint paint;
    private TextViewCompat node;

    GestureDetectorCompat gestureDetector;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        gestureDetector = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {
                // Scrolling uses math based on the viewport (as opposed to math using pixels).

                // Pixel offset is the offset in screen pixels, while viewport offset is the
                // offset within the current viewport.
                Log.println(Log.INFO, "msg", String.format("On Scroll: %f %f %f %f %d %d", distanceX, distanceY, getX(), getY(), getScrollX(), getScrollY()));
                //if (distanceX + getX())

                int X = (int)(getScrollX() + distanceX);
                int Y = (int)(getScrollY() + distanceY);
                scrollTo(X,Y);
                setScrollX(getScrollX() + (int)(distanceX));
                setScrollY(getScrollY() + (int)(distanceY));
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
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStrokeWidth(10);
        float height = getLayoutParams().height;
        float width = getLayoutParams().width;
        canvas.drawLine(getPaddingLeft(),height/2,width - getPaddingRight(),height/2, paint);
        canvas.drawLine(width/2, getPaddingTop(), width/2, height - getPaddingTop(), paint);
        Log.println(Log.INFO, "msg", String.format("On Draw: %d %d %d %d %d %d %f %f" , getWidth(), getHeight(),getPaddingBottom(), getPaddingTop(), getPaddingLeft(), getPaddingRight(), width, height));
    }

    // The ‘active pointer’ is the one currently moving our object.
    private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX, mPosY;
    //private ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener());

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        Log.println(Log.INFO, "msg", String.format("On Touch: %d %d %d %d %d %d", getWidth(), getHeight(), getPaddingBottom(), getPaddingTop(), getPaddingLeft(), getPaddingRight()));

        gestureDetector.onTouchEvent(ev);
        return true;

    }
    /* stuff below used to be a part of onTouchEvent()

        final int action = MotionEventCompat.getActionMasked(ev);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);

                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;
                // Save the ID of this pointer (for dragging)
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex =
                        MotionEventCompat.findPointerIndex(ev, mActivePointerId);

                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                mPosX += dx;
                mPosY += dy;

                invalidate();

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);

                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = MotionEventCompat.getX(ev, newPointerIndex);
                    mLastTouchY = MotionEventCompat.getY(ev, newPointerIndex);
                    mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
                }
                break;
            }
        }
        return true;
    } */
    //@Override
    //public boolean onDown(MotionEvent motionEvent) {
        //return true;
    //}

    //@Override
    //public void onShowPress(MotionEvent motionEvent) {
//
    //}

    //@Override
    //public boolean onSingleTapUp(MotionEvent motionEvent) {
        //return true;
    //}

    //@Override
    //public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float x, float y) {
        //return true;
    //}

    //@Override
    //public void onLongPress(MotionEvent motionEvent) {

   //}


}

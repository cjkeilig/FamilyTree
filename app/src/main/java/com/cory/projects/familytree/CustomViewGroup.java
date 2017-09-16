package com.cory.projects.familytree;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by unouser on 6/26/2017.
 */

public class CustomViewGroup extends ViewGroup {

    private Paint paint;
    public TextView node;

    GestureDetectorCompat gestureDetector;

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.addView(new CustomTextView(context, attrs));
        //paint = new Paint();
        //node = new TextView(this.getContext());
        //node.setText("First textbox");
        //this.addView(node);
    }

    public void onLayout(boolean changed, int l, int r, int t, int b) {
        //ViewGroup method
        int children = this.getChildCount();
        for(int i = 0; i < children; i++) {
            this.getChildAt(i).layout(300,300,300,300);
        }
    }
}

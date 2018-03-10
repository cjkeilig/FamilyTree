package com.example.unouser.customcomponents;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by unouser on 2/25/2018.
 */

public class LengthPicker extends LinearLayout {
    private View mPlusButton;
    private TextView mTextView;
    private View mMinusButton;
    private int mNumInches = 0;
    private String KEY_SUPER_STATE = "superState";
    private String KEY_NUM_INCHES = "numInches";

    private OnChangeListener mListener = null;

    public int getNumInches() {
        return mNumInches;
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mNumInches = bundle.getInt(KEY_NUM_INCHES);
            super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER_STATE));
        } else {
            super.onRestoreInstanceState(state);
        }
        updateControls();
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState());
        bundle.putInt(KEY_NUM_INCHES, mNumInches);
        return bundle;
    }

    public LengthPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.length_picker, this);

        mPlusButton = findViewById(R.id.plus_Button);
        mTextView = (TextView) findViewById(R.id.text2);
        mMinusButton = findViewById(R.id.minus_button);

        updateControls();

        View.OnClickListener listener = (v) -> {
            switch (v.getId()) {
                case R.id.plus_Button:
                    mNumInches++;
                    if (mListener != null) {
                        mListener.onChange(mNumInches);
                    }
                    updateControls();
                    break;
                case R.id.minus_button:
                    if (mNumInches > 0) {
                        mNumInches--;
                        if (mListener != null) {
                            mListener.onChange(mNumInches);
                        }
                        updateControls();
                    }
                    break;
            }

        };
        mPlusButton.setOnClickListener(listener);
        mMinusButton.setOnClickListener(listener);

        //setBackgroundColor(Color.RED);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public LengthPicker(Context context) {

        super(context);
        init();
    }

    private void updateControls() {
        int feet = mNumInches / 12;
        int inches = mNumInches % 12;
        if(feet > 0) {
            mTextView.setText(Integer.toString(feet) + "'" + (inches > 0 ? Integer.toString(inches) + "\"" : ""));
        } else {
            mTextView.setText(Integer.toString(inches) + "\"");
        }
        mMinusButton.setEnabled(mNumInches > 0);
    }

    public void setOnChangeListener(OnChangeListener listener) {
        mListener = listener;
    }
    public interface OnChangeListener {
        public void onChange(int lenght);
    }
}

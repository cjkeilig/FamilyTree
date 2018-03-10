package com.example.unouser.customcomponents;

/**
 * Created by unouser on 3/10/2018.
 */

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.constraint.ConstraintLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class PersonCard extends ConstraintLayout {

    protected ImageView personPic;

    public PersonCard(Context context) {
        super(context);
        init(context);
    }

    public PersonCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PersonCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.person_card, this);

        View.OnLongClickListener longClickListener =  (v) -> {
            // TODO: present popup box to delete person, add parent to person, add sibling to person

            // TODO: based on their input, add or delete a control
            return true;
        };


        personPic = (ImageView)findViewById(R.id.imageView3);
        personPic.setOnLongClickListener(longClickListener);

    }



}

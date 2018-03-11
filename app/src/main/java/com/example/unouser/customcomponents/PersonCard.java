package com.example.unouser.customcomponents;

/**
 * Created by unouser on 3/10/2018.
 */

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.constraint.ConstraintLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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
        // blow-out the person_card.xml into the ConstraintLayout in activity_main.xml
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.person_card, this);

        // protected reference to person pic so that MainActivity can register the LongClick event
        // probably doing this wrong
        personPic = (ImageView)findViewById(R.id.imageView3);

    }

    @Override
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        // This method is called when the context menu is about to show beside one of the registered views
        ContextMenu.ContextMenuInfo menuInfo = super.getContextMenuInfo();
        int myId = getId();

        if (menuInfo == null) {
            return new AdapterView.AdapterContextMenuInfo(this,myId,myId);
        }
        return menuInfo;
    }
}

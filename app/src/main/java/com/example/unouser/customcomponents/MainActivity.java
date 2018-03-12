package com.example.unouser.customcomponents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    //private LengthPicker mHeight;
    //private LengthPicker mWidth;
    //private TextView mArea;
    public static final String TREE_IMAGE_VIEW = "com.example.unouser.customcomponents.TREE_IMAGE_VIEW";

    private List<PersonCard> personCardList = new ArrayList<PersonCard>();
    private int clickedViewId = 0;
    private ConstraintLayout mainLayout;

    // TODO: this could should probably go in a method like setUpImageListener()
    View.OnLongClickListener listener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            // ImageView --> ConstraintLayout --> PersonCard
            View view1 = (View) view.getParent().getParent();
            // Get the view id so we know which ImageView to update in OnActivityResult
            clickedViewId = view1.getId();

            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 0);

            // TODO: process image into small circular icon and put into the image view

            return true;
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (ConstraintLayout)findViewById(R.id.activity_main);

        personCardList.add((PersonCard) findViewById(R.id.personCard));
        personCardList.add((PersonCard) findViewById(R.id.personCard2));
        personCardList.add((PersonCard) findViewById(R.id.personCard3));


        personCardList.forEach(personCard -> personCard.personPic.setOnLongClickListener(listener));
        personCardList.forEach(personCard -> registerForContextMenu(personCard));

        // TODO: If the bundle instance state is not null, re draw the views
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                PersonCard cameFromImageView = (PersonCard)findViewById(clickedViewId);
                cameFromImageView.personPic.setImageBitmap(selectedImage);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Image upload failed", Toast.LENGTH_LONG);

            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.person_menu, menu);
    }
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info  = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_person:
                deletePerson(info.targetView);
                return true;
            case R.id.add_parent:
                addParentToPerson(info.targetView);
                return true;
            case R.id.add_sibling:
                addSiblingToPerson(info.targetView);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deletePerson(View view) {
        mainLayout.removeView(view);
    }

    private void addParentToPerson(View view) {
        // first check if the person already has two parents
        boolean alreadyHasLeftParent = false;
        boolean alreadyHasRightParent = false;
        for(int i = 0; i < mainLayout.getChildCount(); i++) {
            View v = mainLayout.getChildAt(i);
            if(v instanceof PersonCard) {
                ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams)v.getLayoutParams();
                if(lp.endToStart == view.getId()) {
                    alreadyHasLeftParent = true;
                }
                if(lp.startToEnd == view.getId()) {
                    alreadyHasRightParent = true;
                }

            }
        }
        if(alreadyHasLeftParent && alreadyHasRightParent)
        {
            return;
        }

        // might be redundant, not sure if I have to set the Layout params, but just in case we want to make sure it is not MATCH_PARENT
        PersonCard newPerson = new PersonCard(this);
        ConstraintLayout.LayoutParams personLayoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        newPerson.setLayoutParams(personLayoutParams);
        newPerson.setId(View.generateViewId());
        registerForContextMenu(newPerson);
        newPerson.personPic.setOnLongClickListener(listener);

        mainLayout.addView(newPerson);

        // bag to hold ALL the constraints, each add will require 3 connects
        ConstraintSet constraintSet = new ConstraintSet();
        // get all the existing constraints
        constraintSet.clone(mainLayout);

        if(alreadyHasLeftParent) {
            constraintSet.connect(newPerson.getId(),ConstraintSet.BOTTOM,view.getId(),ConstraintSet.TOP);
            // this will be the right parent, flip this will be the left parent
            constraintSet.connect(newPerson.getId(),ConstraintSet.START,view.getId(),ConstraintSet.END);
            constraintSet.connect(newPerson.getId(),ConstraintSet.TOP,view.getId(),ConstraintSet.TOP);

        } else {
            constraintSet.connect(newPerson.getId(),ConstraintSet.BOTTOM,view.getId(),ConstraintSet.TOP);
            constraintSet.connect(newPerson.getId(),ConstraintSet.END,view.getId(),ConstraintSet.START);
            constraintSet.connect(newPerson.getId(),ConstraintSet.TOP,view.getId(),ConstraintSet.TOP);

        }

        //if


        // finally apply the constraint set with the new view we have added
        constraintSet.applyTo(mainLayout);

                //mainLayout.add
    }

    private void addSiblingToPerson(View view) {

    }

}

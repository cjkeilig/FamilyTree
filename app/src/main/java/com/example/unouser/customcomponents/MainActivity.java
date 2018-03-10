package com.example.unouser.customcomponents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    //private LengthPicker mHeight;
    //private LengthPicker mWidth;
    //private TextView mArea;
    public static final String TREE_IMAGE_VIEW = "com.example.unouser.customcomponents.TREE_IMAGE_VIEW";

    private List<PersonCard> personCardList = new ArrayList<PersonCard>();
    private int clickedViewId = 0;
    @Override
    protected void onResume() {
        super.onResume();
        //updateArea();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        personCardList.add((PersonCard) findViewById(R.id.personCard));
        personCardList.add((PersonCard) findViewById(R.id.personCard2));
        personCardList.add((PersonCard) findViewById(R.id.personCard3));
        View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // TODO: fire intent to get an image from the image gallery
                //ImageView currentPersonPic = (ImageView) view;
                //ImageView imgView = (ImageView) view;
                //int anotherid = imgView.getId();
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                clickedViewId = view.getId();

                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 0);

                // TODO: process image into small icon and put into the image view

                return true;
            }
        };
        personCardList.get(0).setOnLongClickListener(listener);
        personCardList.get(1).setOnLongClickListener(listener);
        personCardList.get(2).setOnLongClickListener(listener);

        //personCardList.forEach(personCard->personCard.personPic.setOnLongClickListener(new View.OnLongClickListener() {
        //                                                                              }


        /*mHeight = (LengthPicker) findViewById(R.id.height);
        mWidth = (LengthPicker) findViewById(R.id.width);
        mArea = (TextView) findViewById(R.id.area);

        LengthPicker.OnChangeListener listener = new LengthPicker.OnChangeListener() {
            @Override
            public void onChange(int lenght) {
                updateArea();
            }
        };

        mWidth.setOnChangeListener(listener);
        mHeight.setOnChangeListener(listener);
    }

    private void updateArea() {
        int area = mWidth.getNumInches() * mHeight.getNumInches();
        mArea.setText(area + " sq in");*/
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

}

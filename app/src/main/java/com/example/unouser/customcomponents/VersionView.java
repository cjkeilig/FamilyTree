package com.example.unouser.customcomponents;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import junit.runner.Version;

/**
 * Created by unouser on 2/25/2018.
 */

public class VersionView extends android.support.v7.widget.AppCompatTextView {
    // constructor for Java
    public VersionView(Context context) {
        super(context);
        setVersion();
    }
    // contructors for XML
    public VersionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setVersion();
    }

    public VersionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setVersion();
    }

    private void setVersion() {
        try {
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            this.setText(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {

        }
        //setBackgroundColor(Color.RED);
    }


}

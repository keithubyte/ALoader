package com.demo.dex;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.demo.iface.IActivity;

/**
 * @author keith
 * @since 2016-06-24
 */
public class DynamicActivity implements IActivity {

    Activity activity;
    Resources resources;

    public DynamicActivity(Activity a) {
        activity = a;
        resources = ResourcesLoader.loadResources(a,
                a.getDir("res", Context.MODE_PRIVATE).getAbsolutePath() + "/resources.apk");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Toast.makeText(activity, "onCreate()", Toast.LENGTH_LONG).show();
        View view = LayoutInflater.from(activity).inflate(resources.getLayout(R.layout.activity_dynamic), null);
        activity.setContentView(view);
    }

    @Override
    public void onStart() {
        Toast.makeText(activity, "onStart()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        Toast.makeText(activity, "onResume()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        Toast.makeText(activity, "onPause()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        Toast.makeText(activity, "onStop()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(activity, "onDestroy()", Toast.LENGTH_LONG).show();
    }
}

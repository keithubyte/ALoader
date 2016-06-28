package com.demo.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
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
        resources = ResourcesLoader.loadResources(a, a.getDir("res", Context.MODE_PRIVATE).getAbsolutePath());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Toast.makeText(activity, "DynamicActivity.onCreate()", Toast.LENGTH_LONG).show();

        TextView tv = new TextView(activity);
        tv.setText(resources.getString(R.string.greeting));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 32.0f);
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setBackground(new ColorDrawable(activity.getResources().getColor(android.R.color.holo_purple)));

        activity.setContentView(tv);
    }

    @Override
    public void onStart() {
        Toast.makeText(activity, "DynamicActivity.onStart()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        Toast.makeText(activity, "DynamicActivity.onResume()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        Toast.makeText(activity, "DynamicActivity.onPause()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        Toast.makeText(activity, "DynamicActivity.onStop()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(activity, "DynamicActivity.onDestroy()", Toast.LENGTH_LONG).show();
    }
}

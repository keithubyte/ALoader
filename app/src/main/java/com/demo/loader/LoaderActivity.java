package com.demo.loader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        ClassLoader classLoader = getClassLoader();
        if (classLoader != null) {
            do {
                Log.e("ClassLoader", "classloader = " + classLoader);
            } while ((classLoader = classLoader.getParent()) != null);
        }
    }
}

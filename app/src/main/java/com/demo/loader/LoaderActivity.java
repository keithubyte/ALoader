package com.demo.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import me.kaede.androidjnisample.NativeBlurProcess;

public class LoaderActivity extends AppCompatActivity implements View.OnClickListener {

    Button blurBtn;
    ImageView imageView;

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

        blurBtn = (Button) findViewById(R.id.blur);
        imageView = (ImageView) findViewById(R.id.image);

        blurBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bitmap original = BitmapFactory.decodeResource(getResources(), R.drawable.jupiter);
        NativeBlurProcess.initLibs(this);
        Bitmap bitmap = NativeBlurProcess.blur(original, 4);
        imageView.setImageBitmap(bitmap);
    }
}

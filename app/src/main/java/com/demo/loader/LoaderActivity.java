package com.demo.loader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.demo.iface.IWorker;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import me.kaede.androidjnisample.NativeBlurProcess;

public class LoaderActivity extends AppCompatActivity implements View.OnClickListener {

    Button blurBtn;
    Button reflectionDexBtn;
    Button interfaceDexBtn;
    Button callProxyBtn;
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
        reflectionDexBtn = (Button) findViewById(R.id.execute_dex_in_reflection);
        interfaceDexBtn = (Button) findViewById(R.id.execute_dex_in_interface);
        callProxyBtn = (Button) findViewById(R.id.call_proxy_activity);
        imageView = (ImageView) findViewById(R.id.image);

        blurBtn.setOnClickListener(this);
        reflectionDexBtn.setOnClickListener(this);
        interfaceDexBtn.setOnClickListener(this);
        callProxyBtn.setOnClickListener(this);

        new AppCompatActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blur:
                Bitmap original = BitmapFactory.decodeResource(getResources(), R.drawable.jupiter);
                NativeBlurProcess.initLibs(this);
                Bitmap bitmap = NativeBlurProcess.blur(original, 4);
                imageView.setImageBitmap(bitmap);
                break;
            case R.id.execute_dex_in_reflection:
                Class<?> rWorker = loadClass();
                if (rWorker != null) {
                    try {
                        Method method = rWorker.getDeclaredMethod("doJob");
                        method.setAccessible(true);
                        method.invoke(rWorker.newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.execute_dex_in_interface:
                Class<?> iWorker = loadClass();
                if (iWorker != null) {
                    try {
                        IWorker instance = (IWorker) iWorker.newInstance();
                        instance.doJob();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.call_proxy_activity:
                startActivity(new Intent(this, ProxyActivity.class));
                break;
            default:
                // ignored
        }
    }

    private Class<?> loadClass() {
        String dexPath = getFilesDir() + File.separator + "dynamic.dex";
        String optimizedDirectory = getDir("dex", Context.MODE_PRIVATE).getAbsolutePath();
        DexClassLoader classLoader = new DexClassLoader(dexPath, optimizedDirectory, null, getClassLoader());
        try {
            return classLoader.loadClass("com.demo.dex.Worker");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

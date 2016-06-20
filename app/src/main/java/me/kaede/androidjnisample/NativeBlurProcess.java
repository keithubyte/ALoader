package me.kaede.androidjnisample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;

/**
 * Blur using the NDK and native code.
 * Created by ping on 15/3/16.
 * code from https://github.com/kikoso/android-stackblur
 */
public class NativeBlurProcess {

    private static native void functionToBlur(Bitmap bitmapOut, int radius, int threadCount, int threadIndex, int round);

    @SuppressLint("UnsafeDynamicallyLoadedCode")
    public static void initLibs(Context context) {
        System.load(context.getFilesDir() + File.separator + "libstackblur.so");
    }

    public static Bitmap blur(Bitmap original, float radius) {
        long begin = System.currentTimeMillis();
        Bitmap bitmapOut = original.copy(Bitmap.Config.ARGB_8888, true);
        functionToBlur(bitmapOut, (int) radius, 1, 0, 1);
        functionToBlur(bitmapOut, (int) radius, 1, 0, 2);
        Log.i("NativeBlurProcess", "blur radius:" + radius + " end, cast time  = " + (System.currentTimeMillis() - begin));
        return bitmapOut;
    }
}
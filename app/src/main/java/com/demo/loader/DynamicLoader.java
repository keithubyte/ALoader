package com.demo.loader;

import android.content.Context;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * @author keith
 * @since 2016-06-24
 */
public class DynamicLoader {

    private DynamicLoader() {
    }

    public static Class<?> loadClass(Context context, String clz) {
        String dexPath = context.getFilesDir() + File.separator + "dynamic.dex";
        String optimizedDirectory = context.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath();
        DexClassLoader classLoader = new DexClassLoader(dexPath, optimizedDirectory, null, context.getClassLoader());
        try {
            return classLoader.loadClass(clz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

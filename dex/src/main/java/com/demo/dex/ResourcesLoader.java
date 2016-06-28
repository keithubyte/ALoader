package com.demo.dex;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;

/**
 * @author keith
 * @since 2016-06-28
 */
public class ResourcesLoader {

    private ResourcesLoader() {
    }

    public static Resources loadResources(Context context, String path) {
        Resources dynamicRes = null;

        try {
            AssetManager am = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(am, path);

            Resources apkRes = context.getResources();
            dynamicRes = new Resources(am, apkRes.getDisplayMetrics(), apkRes.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dynamicRes;
    }

}

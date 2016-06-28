package com.demo.loader;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.demo.iface.IActivity;

/**
 * @author keith
 * @since 2016-06-24
 */
public class ProxyActivity extends Activity {

    IActivity mBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Class<?> clz = DynamicLoader.loadClass(this, "com.demo.dex.DynamicActivity");
            if (clz != null) {
                mBase = (IActivity) clz.getDeclaredConstructor(Activity.class).newInstance(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mBase != null) {
            mBase.onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBase != null) {
            mBase.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBase != null) {
            mBase.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBase != null) {
            mBase.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBase != null) {
            mBase.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBase != null) {
            mBase.onDestroy();
        }
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }
}

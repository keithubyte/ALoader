package com.demo.iface;

import android.os.Bundle;

/**
 * @author keith
 * @since 2016-06-24
 */
public interface IActivity {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}

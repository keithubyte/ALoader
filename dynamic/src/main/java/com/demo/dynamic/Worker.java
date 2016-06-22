package com.demo.dynamic;

import android.util.Log;

import com.demo.iface.IWorker;

/**
 * @author keith
 * @since 2016-06-22
 */
public class Worker implements IWorker {

    @Override
    public void doJob() {
        Log.e("Worker", "doJob() invoked by " + this);
    }

}

package test.andranik.apiclientdemo.utils;

import android.util.Log;

import test.andranik.apiclientdemo.BuildConfig;


/**
 * Created by andranik on 3/7/17.
 */

public class Utils {
    public static void log(String tag, String msg) {
        if (BuildConfig.DEBUG && msg != null) {
            Log.d(tag, msg);
        }
    }
}

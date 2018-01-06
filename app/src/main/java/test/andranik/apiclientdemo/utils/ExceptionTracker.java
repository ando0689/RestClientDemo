package test.andranik.apiclientdemo.utils;


import test.andranik.apiclientdemo.BuildConfig;

/**
 * Created by andranik on 3/7/17.
 */

public class ExceptionTracker {
    private static final String TAG = "ExceptionTracker";

    public static void trackException(Throwable t) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace();
        }
        //TODO may we need to log it somewhere too
    }

    public static void logDevError(String error) {
        Utils.log(TAG, error);
    }
}

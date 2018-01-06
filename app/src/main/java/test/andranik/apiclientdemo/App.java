package test.andranik.apiclientdemo;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import test.andranik.apiclientdemo.utils.OAuthHelper;

/**
 * Created by andranik on 1/6/18.
 */

public class App extends Application {

    private static App instance;


    public App() {
        super();
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public String getPlatform() {
        return String.format("Android %s", Build.VERSION.RELEASE);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public boolean hasRefreshToken() {
        return OAuthHelper.restoreRefreshToken() != null;
    }

    public String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }
}

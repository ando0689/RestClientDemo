package test.andranik.apiclientdemo.api.client.default_listeners;

import android.app.Activity;

import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ErrorListener;
import test.andranik.apiclientdemo.utils.DialogUtils;

/**
 * Created by andranik on 3/7/17.
 */
public class DefaultErrorListener implements ErrorListener {

    private Activity activity;
    private boolean closeActivity;

    public DefaultErrorListener(Activity activity) {
        this(activity, false);
    }

    public DefaultErrorListener(Activity activity, boolean closeActivity) {
        this.activity = activity;
        this.closeActivity = closeActivity;
    }

    @Override
    public void onUnknownError(RequestType requestType) {
        DialogUtils.showSomethingWentWrong(activity, closeActivity);
    }

    @Override
    public void onTimeoutError(RequestType requestType) {
        DialogUtils.showTimeOutDialog(activity);
    }

    @Override
    public void onOfflineError(RequestType requestType) {
        DialogUtils.showOfflineDialog(activity,closeActivity);
    }

    @Override
    public void onExceptionError(RequestType requestType, Throwable t) {
        DialogUtils.showSomethingWentWrong(activity, closeActivity);
    }

    @Override
    public void onAppError(RequestType requestType, String message) {
        if (message == null) {
            DialogUtils.showSomethingWentWrong(activity, closeActivity);
            return;
        }

        DialogUtils.showAlertDialog(activity, message);
    }

    @Override
    public ErrorListener closable() {
        closeActivity = true;
        return this;
    }

    @Override
    public ErrorListener simple() {
        closeActivity = false;
        return this;
    }
}

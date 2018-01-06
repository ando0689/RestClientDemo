package test.andranik.apiclientdemo.api.client.dummy_listeners;


import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ErrorListener;
import test.andranik.apiclientdemo.utils.Utils;

/**
 * Created by andranik on 3/7/17.
 */
public class DummyErrorListener implements ErrorListener {
    private static final String TAG = "DummyErrorListener";

    @Override
    public void onUnknownError(RequestType requestType) {
        Utils.log(TAG, requestType + " onUnknownError");
    }

    @Override
    public void onTimeoutError(RequestType requestType) {
        Utils.log(TAG, requestType + " onTimeoutError");
    }

    @Override
    public void onOfflineError(RequestType requestType) {
        Utils.log(TAG, requestType + " onOfflineError");
    }

    @Override
    public void onExceptionError(RequestType requestType, Throwable t) {
        Utils.log(TAG, requestType + " onExceptionError " + t.getMessage());
    }

    @Override
    public void onAppError(RequestType requestType, String message) {
        Utils.log(TAG, requestType + " onAppError " + message);
    }

    @Override
    public ErrorListener closable() {
        // No implementation needed for DummyErrorListener
        return this;
    }

    @Override
    public ErrorListener simple() {
        // No implementation needed for DummyErrorListener
        return this;
    }
}

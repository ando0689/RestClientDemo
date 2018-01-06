package test.andranik.apiclientdemo.api.client.dummy_listeners;

import android.support.annotation.Nullable;

import retrofit2.Response;
import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ResultListener;
import test.andranik.apiclientdemo.api.pojos.ApiResponse;
import test.andranik.apiclientdemo.utils.Utils;

/**
 * Created by andranik on 3/7/17.
 */
public class DummyResultListener implements ResultListener {
    private static final String TAG = "DummyResultListener";

    @Override
    public void onRequestSuccess(RequestType requestType, ApiResponse<?> response) throws Throwable {
        Utils.log(TAG, requestType.toString() + " - " + response.toString());
    }

    @Override
    public void onRequestFailed(RequestType requestType, @Nullable Response response) {
        Utils.log(TAG, requestType.toString() + " - onRequestFailed");
    }

    @Override
    public void onAllRequestsFinished() {
        Utils.log(TAG,  "onAllRequestsFinished");
    }

    @Override
    public void onRequestSuccess(long requestId, RequestType requestType, ApiResponse<?> response) throws Throwable {
        Utils.log(TAG, requestType.toString() + " - " + response.toString());
    }
}

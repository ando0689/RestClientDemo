package test.andranik.apiclientdemo.api.client.listener_interfaces;

import android.support.annotation.Nullable;

import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.pojos.ApiResponse;
import retrofit2.Response;


/**
 * Created by andranik on 3/7/17.
 */
public interface ResultListener {
    void onRequestSuccess(RequestType requestType, ApiResponse<?> response) throws Throwable;

    void onRequestFailed(RequestType requestType, @Nullable Response response);

    void onAllRequestsFinished();

    void onRequestSuccess(long requestId, RequestType requestType, ApiResponse<?> response) throws Throwable;
}

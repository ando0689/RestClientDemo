package test.andranik.apiclientdemo.api.client.custom_listeners;


import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ResultListener;
import test.andranik.apiclientdemo.api.pojos.ApiResponse;

/**
 * Created by andranik on 6/23/17.
 */

public abstract class SimpleResultListener implements ResultListener {

    @Override
    public void onAllRequestsFinished() {

    }

    @Override
    public void onRequestSuccess(long requestId, RequestType requestType, ApiResponse<?> response) throws Throwable {

    }
}

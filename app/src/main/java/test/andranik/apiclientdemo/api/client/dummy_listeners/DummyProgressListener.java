package test.andranik.apiclientdemo.api.client.dummy_listeners;


import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ProgressListener;
import test.andranik.apiclientdemo.utils.Utils;

/**
 * Created by andranik on 3/7/17.
 */
public class DummyProgressListener implements ProgressListener {
    private static final String TAG = "DummyProgressListener";

    @Override
    public void onRequestChainStarted() {
        Utils.log(TAG, "onRequestChainStarted");
    }

    @Override
    public void onRequestStarted(RequestType requestType) {
        Utils.log(TAG, requestType.toString() + " - onRequestStarted");
    }

    @Override
    public void onRequestFinished(RequestType requestType) {
        Utils.log(TAG, requestType.toString() + " - onRequestFinished");
    }

    @Override
    public void onAllRequestsFinished() {
        Utils.log(TAG, "onAllRequestsFinished");
    }
}

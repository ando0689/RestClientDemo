package test.andranik.apiclientdemo.api.client.listener_interfaces;


import test.andranik.apiclientdemo.api.RequestType;

/**
 * Created by andranik on 3/7/17.
 */
public interface ProgressListener {
    void onRequestChainStarted();
    void onRequestStarted(RequestType requestType);
    void onRequestFinished(RequestType requestType);
    void onAllRequestsFinished();
}

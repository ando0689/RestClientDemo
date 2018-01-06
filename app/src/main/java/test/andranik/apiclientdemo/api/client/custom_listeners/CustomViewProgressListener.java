package test.andranik.apiclientdemo.api.client.custom_listeners;

import android.view.View;

import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ProgressListener;


/**
 * Created by andranik on 3/7/17.
 */
public class CustomViewProgressListener implements ProgressListener {

    private View loadingView;

    public CustomViewProgressListener(View view) {
        loadingView = view;
    }

    public CustomViewProgressListener(){}

    @Override
    public void onRequestChainStarted() {
        if(loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestStarted(RequestType requestType) {
    }

    @Override
    public void onRequestFinished(RequestType requestType) {
    }

    @Override
    public void onAllRequestsFinished() {
        if(loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    public CustomViewProgressListener withView(View view){
        this.loadingView = view;
        return this;
    }
}

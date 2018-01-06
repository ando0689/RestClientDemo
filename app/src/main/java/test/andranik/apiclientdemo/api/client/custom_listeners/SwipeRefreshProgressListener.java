package test.andranik.apiclientdemo.api.client.custom_listeners;

import android.support.v4.widget.SwipeRefreshLayout;

import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ProgressListener;


/**
 * Created by andranik on 3/7/17.
 */
public class SwipeRefreshProgressListener implements ProgressListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    public SwipeRefreshProgressListener(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    public void onRequestChainStarted() {
        if (swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(true);
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
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}

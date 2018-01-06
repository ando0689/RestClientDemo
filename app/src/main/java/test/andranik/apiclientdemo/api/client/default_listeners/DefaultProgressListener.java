package test.andranik.apiclientdemo.api.client.default_listeners;

import android.app.Activity;
import android.app.ProgressDialog;

import test.andranik.apiclientdemo.R;
import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ProgressListener;
import test.andranik.apiclientdemo.utils.DialogUtils;

/**
 * Created by andranik on 3/7/17.
 */
public class DefaultProgressListener implements ProgressListener {

    private Activity activity;
    private ProgressDialog progressDialog;

    public DefaultProgressListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onRequestChainStarted() {
        if(progressDialog == null || !progressDialog.isShowing()){
            progressDialog = DialogUtils.showProgressDialog(
                    activity,
                    activity.getString(R.string.please_wait),
                    false);
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
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}

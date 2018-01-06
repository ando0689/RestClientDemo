package test.andranik.apiclientdemo.api.client.custom_listeners;

import android.app.Activity;
import android.widget.Toast;

import io.reactivex.functions.Consumer;
import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.SuccessMessageListener;


/**
 * Created by andranik on 3/23/17.
 */

public class ToastSuccessMessageListener implements SuccessMessageListener {

    private Activity activity;

    public ToastSuccessMessageListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void showSuccessMessage(RequestType requestType, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public SuccessMessageListener setOnOkClickAction(Consumer<RequestType> onOkClickAction) {
        return this;
    }
}

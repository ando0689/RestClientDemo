package test.andranik.apiclientdemo.api.client.dummy_listeners;


import io.reactivex.functions.Consumer;
import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.SuccessMessageListener;
import test.andranik.apiclientdemo.utils.Utils;

/**
 * Created by andranik on 3/23/17.
 */

public class DummySuccessMessageListener implements SuccessMessageListener {
    private static final String TAG = "DummyResultListener";

    @Override
    public void showSuccessMessage(RequestType requestType, String message) {
        Utils.log(TAG, message);
    }

    @Override
    public SuccessMessageListener setOnOkClickAction(Consumer<RequestType> onOkClickAction) {
        return this;
    }
}

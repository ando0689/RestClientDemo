package test.andranik.apiclientdemo.api.client.listener_interfaces;

import io.reactivex.functions.Consumer;
import test.andranik.apiclientdemo.api.RequestType;

/**
 * Created by andranik on 3/23/17.
 */

public interface SuccessMessageListener {
    void showSuccessMessage(RequestType requestType, String message);
    SuccessMessageListener setOnOkClickAction(Consumer<RequestType> onOkClickAction);
}

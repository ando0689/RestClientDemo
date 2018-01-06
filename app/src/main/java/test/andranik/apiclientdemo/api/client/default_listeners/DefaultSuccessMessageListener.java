package test.andranik.apiclientdemo.api.client.default_listeners;

import android.app.Activity;

import io.reactivex.functions.Consumer;
import test.andranik.apiclientdemo.R;
import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.client.listener_interfaces.SuccessMessageListener;
import test.andranik.apiclientdemo.utils.DialogUtils;
import test.andranik.apiclientdemo.utils.ExceptionTracker;


/**
 * Created by andranik on 3/23/17.
 */

public class DefaultSuccessMessageListener implements SuccessMessageListener {

    private Activity activity;

    private Consumer<RequestType> onOkClickAction;

    public DefaultSuccessMessageListener(Activity activity) {
        this(activity, null);
    }

    public DefaultSuccessMessageListener(Activity activity, Consumer<RequestType> onOkClickAction) {
        this.activity = activity;
        this.onOkClickAction = onOkClickAction;
    }

    public SuccessMessageListener setOnOkClickAction(Consumer<RequestType> onOkClickAction) {
        this.onOkClickAction = onOkClickAction;
        return this;
    }

    @Override
    public void showSuccessMessage(RequestType requestType,String message) {
        if (onOkClickAction == null) {
            DialogUtils.showAlertDialog(activity, message);
            return;
        }

        DialogUtils.showAlertDialog(activity, message, R.string.ok, R.string.cancel, (dialogInterface, i) -> {
            try {
                onOkClickAction.accept(requestType);
                dialogInterface.dismiss();
            } catch (Exception e) {
                ExceptionTracker.trackException(e);
            }
        });
    }
}

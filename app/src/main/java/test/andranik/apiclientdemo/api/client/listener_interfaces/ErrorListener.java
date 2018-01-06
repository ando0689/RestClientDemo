package test.andranik.apiclientdemo.api.client.listener_interfaces;


import test.andranik.apiclientdemo.api.RequestType;
/**
 * Created by andranik on 3/7/17.
 */
public interface ErrorListener {
    void onUnknownError(RequestType requestType);

    void onTimeoutError(RequestType requestType);

    void onOfflineError(RequestType requestType);

    void onExceptionError(RequestType requestType, Throwable t);

    void onAppError(RequestType requestType, String message);
    //close activity boolean set to true
    ErrorListener closable();
    //close activity boolean set to false
    ErrorListener simple();
}

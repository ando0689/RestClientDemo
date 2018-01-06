package test.andranik.apiclientdemo.api.client.listener_interfaces;


import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.pojos.ApiResponse;
import retrofit2.Response;

/**
 * Created by andranik on 3/27/17.
 */

public interface SessionExpiredListener {
    void onSessionExpired(RequestType requestType, Response<ApiResponse> response);
}

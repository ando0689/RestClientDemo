package test.andranik.apiclientdemo.api.client;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.andranik.apiclientdemo.App;
import test.andranik.apiclientdemo.api.RequestType;
import test.andranik.apiclientdemo.api.api_instances.BasePrivateApi;
import test.andranik.apiclientdemo.api.client.dummy_listeners.DummyErrorListener;
import test.andranik.apiclientdemo.api.client.dummy_listeners.DummyProgressListener;
import test.andranik.apiclientdemo.api.client.dummy_listeners.DummyResultListener;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ErrorListener;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ProgressListener;
import test.andranik.apiclientdemo.api.client.listener_interfaces.ResultListener;
import test.andranik.apiclientdemo.api.client.listener_interfaces.SessionExpiredListener;
import test.andranik.apiclientdemo.api.client.listener_interfaces.SuccessMessageListener;
import test.andranik.apiclientdemo.api.pojos.ApiResponse;
import test.andranik.apiclientdemo.utils.ExceptionTracker;


/**
 * Created by andranik on 3/7/17.
 */

public class ApiClient {
    private ArrayList<Call> calls;
    private AtomicInteger activeRequests;
    private ProgressListener progressListener;
    private SessionExpiredListener sessionExpiredListener;

    public ApiClient() {
        this(null);
    }

    public ApiClient(SessionExpiredListener sessionExpiredListener) {
        calls = new ArrayList<>();
        activeRequests = new AtomicInteger();
        this.sessionExpiredListener = sessionExpiredListener;
    }

    public void release() {
        if (calls != null) {
            for (Call call : calls) {
                call.cancel();
            }
        }
    }

    /**
     * Just makes request and prints results in log. No callback is called
     *
     * @param call
     * @param requestType
     */
    public void makeRequest(Call<ApiResponse> call, RequestType requestType) {
        makeRequest(
                call,
                requestType,
                0,
                new DummyResultListener(),
                new DummyProgressListener(),
                new DummyErrorListener()
        );
    }

    /**
     * Just makes request and prints results in log. No callback is called
     *
     * @param call
     * @param requestType
     * @param requestId
     */
    public void makeRequest(Call<ApiResponse> call, RequestType requestType, long requestId) {
        makeRequest(
                call,
                requestType,
                requestId,
                new DummyResultListener(),
                new DummyProgressListener(),
                new DummyErrorListener()
        );
    }

    /**
     * Makes request and delivers the result to ResultListener. No progress or error callbacks are called
     *
     * @param call
     * @param requestType
     * @param rl          - the result listener which will receive the result of this call
     */
    public void makeRequest(Call<ApiResponse> call, RequestType requestType, ResultListener rl) {
        makeRequest(
                call,
                requestType,
                0,
                rl,
                new DummyProgressListener(),
                new DummyErrorListener()
        );
    }

    /**
     * Makes request and delivers the result to ResultListener. No progress or error callbacks are called
     *
     * @param call
     * @param requestType
     * @param rl          - the result listener which will receive the result of this call
     * @param requestId
     */
    public void makeRequest(Call<ApiResponse> call, RequestType requestType, long requestId, ResultListener rl) {
        makeRequest(
                call,
                requestType,
                requestId,
                rl,
                new DummyProgressListener(),
                new DummyErrorListener()
        );
    }

    public void makeRequest(Call<ApiResponse> call, RequestType requestType, ResultListener rl, ProgressListener pl, ErrorListener el) {
        makeRequest(call, requestType, 0, rl, pl, el, null, false);
    }

    public void makeRequest(Call<ApiResponse> call, RequestType requestType, long requestId, ResultListener rl, ProgressListener pl, ErrorListener el) {
        makeRequest(call, requestType, requestId, rl, pl, el, null, false);
    }

    public void makeRequest(Call<ApiResponse> call, RequestType requestType, ResultListener rl, ProgressListener pl, ErrorListener el, SuccessMessageListener sml) {
        makeRequest(call, requestType, 0, rl, pl, el, sml, false);
    }

    public void makeRequest(Call<ApiResponse> call, RequestType requestType, long requestId, ResultListener rl, ProgressListener pl, ErrorListener el, SuccessMessageListener sml) {
        makeRequest(call, requestType, requestId, rl, pl, el, sml, false);
    }

    public void makeRequest(Call<ApiResponse> call, RequestType requestType, long requestId, ResultListener rl, ProgressListener pl, ErrorListener el, SuccessMessageListener sml, boolean onCurrentThread) {
        if (activeRequests.getAndIncrement() == 0) {
            progressListener = pl;
            progressListener.onRequestChainStarted();
        }

        pl.onRequestStarted(requestType);

        if (!App.getInstance().isOnline()) {
            el.onOfflineError(requestType);
            rl.onRequestFailed(requestType, null);
            pl.onRequestFinished(requestType);
            if (activeRequests.decrementAndGet() == 0) {
                progressListener.onAllRequestsFinished();
                rl.onAllRequestsFinished();
            }
            return;
        }


        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (hasServerError(requestType, response, el)) {
                    rl.onRequestFailed(requestType, response);
                    onRequestEnded(pl, rl, requestType);
                    return;
                }

                if (hasAppError(requestType, response.body(), el)) {
                    rl.onRequestFailed(requestType, response);
                    onRequestEnded(pl, rl, requestType);
                    return;
                }

                try {

                    rl.onRequestSuccess(requestId, requestType, response.body());
                    rl.onRequestSuccess(requestType, response.body());

                    showSuccessMessageIfNeeded(requestType, sml, response.body().getMessage());
                } catch (Throwable t) {
                    checkException(requestType, t, el);
                    rl.onRequestFailed(requestType, response);
                    ExceptionTracker.trackException(t);
                } finally {
                    onRequestEnded(pl, rl, requestType);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                checkException(requestType, t, el);
                rl.onRequestFailed(requestType, null);
                ExceptionTracker.trackException(t);

                onRequestEnded(pl, rl, requestType);
            }
        });

        calls.add(call);
    }

    private void onRequestEnded(ProgressListener pl, ResultListener rl, RequestType requestType) {
        pl.onRequestFinished(requestType);
        if (activeRequests.decrementAndGet() == 0) {
            progressListener.onAllRequestsFinished();
            rl.onAllRequestsFinished();
        }
    }

    private void showSuccessMessageIfNeeded(RequestType requestType, SuccessMessageListener sml, String successMessage) {
        if (sml != null && successMessage != null && !successMessage.isEmpty()) {
            sml.showSuccessMessage(requestType, successMessage);
        }
    }

    private boolean hasServerError(RequestType requestType, Response<ApiResponse> response, ErrorListener el) {
        if (response.isSuccessful()) {
            BasePrivateApi.resetRetryCount();
            return false;
        }

        if (response.raw().code() == 401 && sessionExpiredListener != null) {
            sessionExpiredListener.onSessionExpired(requestType, response);
        } else {
            el.onUnknownError(requestType);
        }

        ExceptionTracker.logDevError("Server error code: " + response.raw().code() + ", Message: " + response.raw().message());

        return true;
    }

    private boolean hasAppError(RequestType requestType, ApiResponse apiResponse, ErrorListener el) {
        if (apiResponse.isSuccess()) {
            return false;
        }

        el.onAppError(requestType, apiResponse.getError().getDisplayMessage());

        ExceptionTracker.logDevError(apiResponse.getError().getInternalMessage());

        return true;
    }

    private void checkException(RequestType requestType, Throwable t, ErrorListener el) {
        if (t instanceof SocketTimeoutException) {
            el.onTimeoutError(requestType);
            return;
        }// TODO if we will need any other

        el.onExceptionError(requestType, t);
    }
}


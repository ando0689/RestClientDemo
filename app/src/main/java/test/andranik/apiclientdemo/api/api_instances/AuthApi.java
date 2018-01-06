package test.andranik.apiclientdemo.api.api_instances;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import test.andranik.apiclientdemo.api.api_interfaces.AuthApiInterface;
import test.andranik.apiclientdemo.api.pojos.ApiResponse;
import test.andranik.apiclientdemo.api.pojos.OAuthError;
import test.andranik.apiclientdemo.api.pojos.OAuthSuccess;
import test.andranik.apiclientdemo.utils.OAuthHelper;

/**
 * Created by andranik on 3/15/17.
 */

public class AuthApi extends Api<AuthApiInterface>{

    private static final int HTTP_OK = 200;
    private static final int HTTP_BAD_REQUEST = 400;

    AuthApi() {
        super(AuthApiInterface.class);
    }

    @Override
    protected OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = getDefaultHttpClientBuilder();

        builder.addInterceptor(chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder();

            requestBuilder.method(original.method(), original.body());

            Request request = requestBuilder.build();

            okhttp3.Response response = chain.proceed(request);

            return normalizeResponse(response);
        });

        return builder.build();
    }


    private okhttp3.Response normalizeResponse(okhttp3.Response response) throws IOException {
        String jsonString = null;

        switch (response.code()){
            case HTTP_OK:
                jsonString = transformSuccess(response.body().string());
                break;

            case HTTP_BAD_REQUEST:
                jsonString = transformError(response.body().string());
                break;
        }

        if(jsonString != null){
            MediaType contentType = response.body().contentType();
            ResponseBody body = ResponseBody.create(contentType, jsonString);
            return response.newBuilder().body(body).code(HTTP_OK).build();
        }

        return response;
    }

    private String transformError(String jsonString){
        Gson gson = new Gson();

        OAuthError error = gson.fromJson(jsonString, OAuthError.class);
        ApiResponse thResponse = error.getNormalisedResposne();

        return gson.toJson(thResponse, ApiResponse.class);
    }

    private String transformSuccess(String jsonString){
        Gson gson = new Gson();

        OAuthSuccess success = gson.fromJson(jsonString, OAuthSuccess.class);
        OAuthHelper.setOAuthSuccess(success);
        ApiResponse thResponse = success.getNormalisedResposne();

        return gson.toJson(thResponse, ApiResponse.class);
    }

    @Override
    public String getBaseUrl() {
        return AUTH_URL;
    }


    ///////////////////////////////////////// Static Construction /////////////////////////////////////////

    private static AuthApi instance;

    public static AuthApiInterface get(){
        if(instance == null){
            instance = new AuthApi();
        }

        return instance.getApiService();
    }

    public static void reset(){
        instance = null;
    }
}

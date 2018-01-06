package test.andranik.apiclientdemo.api.api_instances;

import test.andranik.apiclientdemo.api.api_interfaces.PublicApiInterface;
import okhttp3.OkHttpClient;


/**
 * Created by andranik on 3/7/17.
 */

public class PublicApi extends Api<PublicApiInterface>{

    private PublicApi() {
        super(PublicApiInterface.class);
    }

    protected OkHttpClient getHttpClient() {
        return getDefaultHttpClientBuilder().build();
    }

    @Override
    protected String getBaseUrl() {
        return APP_API_URL;
    }


    ///////////////////////////////////////// Static Construction /////////////////////////////////////////

    private static PublicApi instance;

    public static PublicApiInterface get(){
        if(instance == null){
            instance = new PublicApi();
        }

        return instance.getApiService();
    }

    public static void reset(){
        instance = null;
    }
}

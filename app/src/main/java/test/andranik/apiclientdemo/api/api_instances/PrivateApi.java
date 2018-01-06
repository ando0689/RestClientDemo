package test.andranik.apiclientdemo.api.api_instances;


import test.andranik.apiclientdemo.api.api_interfaces.PrivateApiInterface;

/**
 * Created by andranik on 3/7/17.
 */
public class PrivateApi extends BasePrivateApi<PrivateApiInterface>{

    PrivateApi() {
        super(PrivateApiInterface.class);
    }

    @Override
    protected String getBaseUrl() {
        return APP_API_URL;
    }


    ///////////////////////////////////////// Static Construction /////////////////////////////////////////

    private static PrivateApi instance;

    public static PrivateApiInterface get(){
        if(instance == null){
            instance = new PrivateApi();
        }

        return instance.getApiService();
    }

    public static void reset(){
        instance = null;
    }
}

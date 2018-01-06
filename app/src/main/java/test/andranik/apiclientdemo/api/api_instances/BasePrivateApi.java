package test.andranik.apiclientdemo.api.api_instances;

import okhttp3.OkHttpClient;
import test.andranik.apiclientdemo.api.HttpHeaders;
import test.andranik.apiclientdemo.utils.OAuthHelper;


/**
 * Created by andranik on 3/22/17.
 */

public abstract class BasePrivateApi<S> extends Api<S> {

    protected static final int MAX_RETRY_COUNT = 10;

    protected static int retryCount = 0;

    protected BasePrivateApi(Class<S> apiInterfaceClass) {
        super(apiInterfaceClass);
    }

    protected OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = getDefaultHttpClientBuilder();

        builder.authenticator((route, response) -> {
            String currentRefreshToken = OAuthHelper.getRefreshToken();

            AuthApi.get().loginRefreshToken(OAuthHelper.getFields(currentRefreshToken)).execute();

            retryCount++;

            if (retryCount >= MAX_RETRY_COUNT){
                //if we reach max retry count we stop trying
                retryCount = 0;
                return null;
            }

            return response.request().newBuilder()
                    .header(HttpHeaders.AUTHORIZATION, OAuthHelper.getAccessToken())
                    .build();
        });

        return builder.build();
    }

    public static void resetRetryCount(){
        retryCount = 0;
    }
}

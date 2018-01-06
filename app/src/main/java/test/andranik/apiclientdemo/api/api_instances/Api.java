package test.andranik.apiclientdemo.api.api_instances;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.andranik.apiclientdemo.App;
import test.andranik.apiclientdemo.BuildConfig;
import test.andranik.apiclientdemo.api.DateDeserializer;
import test.andranik.apiclientdemo.api.DateSerializer;
import test.andranik.apiclientdemo.api.HttpHeaders;
import test.andranik.apiclientdemo.utils.ApiUrls;
import test.andranik.apiclientdemo.utils.OAuthHelper;


/**
 * Created by andranik on 3/7/17.
 */

public abstract class Api<S> implements ApiUrls {

    public static void releaseInstance() {
        PublicApi.reset();
        PrivateApi.reset();
        AuthApi.reset();
    }

    private S apiService;

    public static Gson getGson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();
    }

    protected Api(Class<S> apiInterfaceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(getHttpClient())
                .build();

        apiService = retrofit.create(apiInterfaceClass);
    }

    protected OkHttpClient.Builder getDefaultHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header(HttpHeaders.X_PLATFORM, App.getInstance().getPlatform())
                    .header(HttpHeaders.X_CLIENT_VERSION, App.getInstance().getAppVersion());

            if (this instanceof BasePrivateApi) {
                requestBuilder.header(HttpHeaders.AUTHORIZATION, OAuthHelper.getAccessToken());
            }

            requestBuilder.method(original.method(), original.body());

            Request request = requestBuilder.build();

            return chain.proceed(request);
        });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        builder.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        return builder;
    }

    public static String getBase() {
        return APP_API_URL;
    }

    protected S getApiService() {
        return apiService;
    }

    protected abstract String getBaseUrl();

    protected abstract OkHttpClient getHttpClient();
}


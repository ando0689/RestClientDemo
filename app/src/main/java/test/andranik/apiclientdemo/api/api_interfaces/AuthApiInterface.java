package test.andranik.apiclientdemo.api.api_interfaces;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import test.andranik.apiclientdemo.api.pojos.ApiResponse;
import test.andranik.apiclientdemo.api.pojos.OAuthSuccess;


/**
 * Created by andranik on 3/22/17.
 */

public interface AuthApiInterface {
    @FormUrlEncoded
    @POST("connect/token")
    Call<ApiResponse<OAuthSuccess>> loginRefreshToken(@FieldMap Map<String, String> fields);

}

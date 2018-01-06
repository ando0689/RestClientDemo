package test.andranik.apiclientdemo.api.api_interfaces;

import java.util.List;

import test.andranik.apiclientdemo.api.pojos.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by andranik on 3/22/17.
 */

public interface PrivateApiInterface {

    // Just one method for example, Object return type is also for example

    @GET("Lookup/getAllCountries")
    Call<ApiResponse<List<Object>>> getAllCountries();

}

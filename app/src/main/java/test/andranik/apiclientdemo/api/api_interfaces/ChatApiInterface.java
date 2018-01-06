package test.andranik.apiclientdemo.api.api_interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.andranik.apiclientdemo.api.pojos.ApiResponse;


/**
 * Created by andranik on 3/24/17.
 */

public interface ChatApiInterface {

    // Just one method for example, Object return type is also for example

    @GET("Messages")
    Call<ApiResponse<List<Object>>> getMessages(@Query("conversationId") String conversationId);
}

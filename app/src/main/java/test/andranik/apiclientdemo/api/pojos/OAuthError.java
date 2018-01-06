package test.andranik.apiclientdemo.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andranik on 3/15/17.
 */

public class OAuthError {

    @SerializedName("error")
    @Expose
    public String error;

    @SerializedName("error_description")
    @Expose
    public String errorDescription;


    public ApiResponse getNormalisedResposne(){
        ApiResponse tdResponse = new ApiResponse<>();

        ApiError tdError = new ApiError();

        tdError.setDisplayMessage(errorDescription);
        tdError.setInternalMessage(error);

        tdResponse.setSuccess(false);
        tdResponse.setError(tdError);

        return tdResponse;
    }

}

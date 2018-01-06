package test.andranik.apiclientdemo.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andranik on 3/15/17.
 */

public class OAuthSuccess {

    @SerializedName("access_token")
    @Expose
    public String accessToken;

    @SerializedName("expires_in")
    @Expose
    public Long expiresIn;

    @SerializedName("token_type")
    @Expose
    public String tokenType;

    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;


    public ApiResponse<OAuthSuccess> getNormalisedResposne() {
        ApiResponse<OAuthSuccess> tdResponse = new ApiResponse<>();

        tdResponse.setSuccess(true);
        tdResponse.setMessage("");
        tdResponse.setData(this);

        return tdResponse;
    }

}

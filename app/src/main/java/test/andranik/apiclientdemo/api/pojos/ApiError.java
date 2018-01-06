package test.andranik.apiclientdemo.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andranik on 3/9/17.
 */

public class ApiError {

    @SerializedName("displayMessage")
    @Expose
    private String displayMessage;

    @SerializedName("internalMessage")
    @Expose
    private String internalMessage;

    @SerializedName("errorCode")
    @Expose
    private int errorCode;

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getInternalMessage() {
        return internalMessage;
    }

    public void setInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

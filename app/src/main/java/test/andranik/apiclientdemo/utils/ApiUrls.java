package test.andranik.apiclientdemo.utils;

/**
 * Created by andranik on 9/16/17.
 */

public interface ApiUrls {
    String API_VERSION = "V3/";
    String CHAT_API_VERSION = "V2/";

    String AUTH_URL = "https://auth.somedomain.com/";
    String APP_API_URL = "https://mobileapi.somedomain.com/api/" + API_VERSION;
    String CHAT_API_URL = "https://chatapi.somedomain.com/api/" + CHAT_API_VERSION;
}

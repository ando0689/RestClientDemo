package test.andranik.apiclientdemo.utils;

import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import test.andranik.apiclientdemo.api.pojos.OAuthSuccess;


/**
 * Created by andranik on 3/15/17.
 */

public class OAuthHelper {

    private static final String STORE_KEY_REFRESH_TOKEN = "refresh_token_store";

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_CLIENT_ID = "client_id";
    private static final String KEY_CLIENT_SECRET = "client_secret";
    private static final String KEY_SCOPE = "scope";
    private static final String KEY_GRANT_TYPE = "grant_type";


    private static final String CLIENT_ID = "client11";
    private static final String CLIENT_SECRET = "secret11";
    private static final String SCOPE = "api123 offline_access";

    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String GRANT_TYPE_REFRESH = "refresh_token";


    private static String refreshToken;
    private static OAuthSuccess oAuthSuccess;

    private static Map<String, String> getDefaultMap(String grantType){
        Map<String, String> map = new HashMap<>();

        map.put(KEY_CLIENT_ID, CLIENT_ID);
        map.put(KEY_CLIENT_SECRET, CLIENT_SECRET);
        map.put(KEY_SCOPE, SCOPE);
        map.put(KEY_GRANT_TYPE, grantType);

        return map;
    }

    public static Map<String, String> getFields(String username, String password){
        Map<String, String> map = getDefaultMap(GRANT_TYPE_PASSWORD);

        map.put(KEY_USERNAME, username);
        map.put(KEY_PASSWORD, password);

        return map;
    }

    public static Map<String, String> getFields(String refreshToken){
        Map<String, String> map = getDefaultMap(GRANT_TYPE_REFRESH);

        map.put(KEY_REFRESH_TOKEN, refreshToken);

        return map;
    }

    public static String getAccessToken() {
        return oAuthSuccess == null ? "" : String.format("%s %s", oAuthSuccess.tokenType, oAuthSuccess.accessToken);
    }


    public static String getRefreshToken() {
        return OAuthHelper.restoreRefreshToken() == null ? "" : OAuthHelper.restoreRefreshToken();
    }

    public static void resetTokens(){
        OAuthHelper.oAuthSuccess = null;
        OAuthHelper.refreshToken = null;
        Hawk.put(STORE_KEY_REFRESH_TOKEN, refreshToken);
    }

    public static void setOAuthSuccess(OAuthSuccess oAuthSuccess) {
        OAuthHelper.oAuthSuccess = oAuthSuccess;
        OAuthHelper.saveRefreshToken(oAuthSuccess.refreshToken);
    }

    public static void saveRefreshToken(String refreshToken){
        OAuthHelper.refreshToken = refreshToken;
        Hawk.put(STORE_KEY_REFRESH_TOKEN, refreshToken);
    }

    public static String restoreRefreshToken(){
        if(refreshToken == null){
            refreshToken = Hawk.get(STORE_KEY_REFRESH_TOKEN);
        }

        return refreshToken;
    }

}

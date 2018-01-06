package test.andranik.apiclientdemo.api.api_instances;

import test.andranik.apiclientdemo.api.api_interfaces.ChatApiInterface;

/**
 * Created by andranik on 3/24/17.
 */

public class ChatApi extends BasePrivateApi<ChatApiInterface> {

    ChatApi() {
        super(ChatApiInterface.class);
    }

    @Override
    protected String getBaseUrl() {
        return CHAT_API_URL;
    }

    private static ChatApi instance;

    public static ChatApiInterface get() {
        if (instance == null) {
            instance = new ChatApi();
        }

        return instance.getApiService();
    }

    public static void reset() {
        instance = null;
    }
}

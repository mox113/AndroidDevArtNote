package cn.hudp.androiddevartnote.Retrofit;

import retrofit2.Retrofit;

/**
 * Created by HuDP on 2017/7/28.
 */

public class HttpMethods {
    private static final String baseUrl = "https://api.douban.com/v2/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private API api;
}

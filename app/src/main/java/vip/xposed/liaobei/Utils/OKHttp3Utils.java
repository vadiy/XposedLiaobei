package vip.xposed.liaobei.Utils;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by AiXin on 2019-9-14.
 */
public class OKHttp3Utils {
    public static final String DEBUG_URL = "http://192.168.1.111:8890";
    public static final String RELEASE_URL = "http://192.168.1.111:8890";

    public static String getUrl() {
        if (DBUtil.DEBUG) {
            return DEBUG_URL;
        } else {
            return RELEASE_URL;
        }
    }

    /**
     * GET异步请求
     *
     * @param url
     * @return
     */
    public static void getAsync(String url, Callback callback) {
        //第一步获取okHttpClient对象,禁用代理
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY)
                .build();
        //第二步构建Request对象
        Request request = new Request.Builder().url(url).get().build();
        //第三步构建Call对象
        Call call = client.newCall(request);
        //第四步异步get请求
        call.enqueue(callback);
    }

    /**
     * GET同步请求,不能直接在主线程中使用,需要创建一个线程来调用
     *
     * @param url
     * @return
     */
    public static String getSync(String url) {
        String result = "";
        //第一步获取okHttpClient对象,禁用代理
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY)
                .build();
        //第二步构建Request对象
        Request request = new Request.Builder().url(url).get().build();
        //第三步构建Call对象
        Call call = client.newCall(request);
        //第四步同步get请求
        try {
            Response response = call.execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * POST异步请求
     *
     * @param url
     * @param bodyJson
     * @param callback
     */
    public static void postAsync(String url, String bodyJson, Callback callback) {
        //第一步获取okHttpClient对象,禁用代理
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY)
                .build();
        //第二步构建Request对象
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(bodyJson, MediaType.parse("application/json;charset=utf-8")))
                .build();
        //第三步构建Call对象
        Call call = client.newCall(request);
        //第四步异步get请求
        call.enqueue(callback);
    }

    /**
     * POST同步请求,不能直接在主线程中使用,需要创建一个线程来调用
     *
     * @param url
     * @param bodyJson
     * @return
     */
    public static String postSync(String url, String bodyJson) {
        String result = "";
        //第一步获取okHttpClient对象,禁用代理
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY)
                .build();
        //第二步构建Request对象
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(bodyJson, MediaType.parse("application/json;charset=utf-8")))
                .build();
        //第三步构建Call对象
        Call call = client.newCall(request);
        //第四步同步get请求
        try {
            Response response = call.execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

package it.me.tae.mygithub.restapi;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import it.me.tae.mygithub.constant.Constant;

/**
 * Created by Adisorn on 8/31/2017.
 */


public class RestClient {
    private AsyncHttpClient client;

    public RestClient(Context context) {
        client = new AsyncHttpClient();
        client.setTimeout(3 * 60 * 1000);
        client.setUserAgent("http://developer.github.com/v3/#user-agent-required");
    }

    public void get(String url, RequestParams requestParams, final Class classModelResponse, final RestClientListener clientListener) {
        Log.d("DEV", "url : " + url);
        // url = Constant.ROOT_URL + url;
        client.get(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Log.d("DEV", "response : " + new String(responseBody));
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    if (jsonObject.getBoolean("status")) {
//                        clientListener.onSuccess(convertJsonModel(classModelResponse, new String(responseBody)));
//                    }
//                    else {
//                        clientListener.onFailure(jsonObject.getString("message"));
//                    }
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
                clientListener.onSuccess(convertJsonModel(classModelResponse, new String(responseBody)));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                clientListener.onFailure(new String(responseBody));
                Log.d("DEV", "statusCode : " + statusCode);
                Log.d("DEV", "responseBody : " + new String(responseBody));
                clientListener.onFailure((responseBody != null) ? new String(responseBody) : "");
            }
        });
    }

    public void post(String url, RequestParams requestParams, final Class classModelResponse, final RestClientListener clientListener) {
        Log.d("DEV", "url : " + url);
        url = Constant.ROOT_URL + url;

        client.post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Log.d("DEV", "response : " + new String(responseBody));
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("status")) {
                        clientListener.onSuccess(convertJsonModel(classModelResponse, new String(responseBody)));
                    } else {
                        clientListener.onFailure(jsonObject.getString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                clientListener.onFailure((responseBody != null) ? new String(responseBody) : "");
            }
        });
    }

    private <T> T convertJsonModel(Class<T> model, String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, model);
    }


    public interface RestClientListener<T> {
        public void onSuccess(T res);

        public void onFailure(String message);
    }
}

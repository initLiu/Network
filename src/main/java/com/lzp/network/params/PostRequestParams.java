package com.lzp.network.params;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzp.network.service.NetInterface;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by lillian on 2018/4/29.
 */

public class PostRequestParams extends RequestParams {
    private String mContentType;

    public PostRequestParams(String baseUrl) {
        super(baseUrl);
    }

    public PostRequestParams(String baseUrl, String path) {
        super(baseUrl, path);
    }

    /**
     * @param type application/jsonß
     */
    public PostRequestParams setContentType(String type) {
        mContentType = "application/json";
        return this;
    }

    public String getContentType() {
        return (mContentType == null || mContentType.equals("")) ? "application/json" : mContentType;
    }

    @Override
    public String getEncodedFilters() {
        if (mContentType.equals("application/json")) {
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            return gson.toJson(getFilters());
        }
        return null;
    }

    @Override
    public Call<String> createCall(NetInterface netInterface) {
        RequestBody body = RequestBody.create(MediaType.parse(getContentType()), getEncodedFilters());
        return netInterface.postRequest(getPath(), body, getHeaders());
    }
}

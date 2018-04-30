package com.lzp.network.params;

import com.lzp.network.service.NetInterface;

import retrofit2.Call;

/**
 * Created by lillian on 2018/4/29.
 */

public class FormUePostRequestParams extends RequestParams {
    private String mContentType;

    public FormUePostRequestParams(String baseUrl) {
        super(baseUrl);
    }

    public FormUePostRequestParams(String baseUrl, String path) {
        super(baseUrl, path);
    }

    @Override
    public Call<String> createCall(NetInterface netInterface) {
        return netInterface.postRequestWithFormUrlEncode(getPath(), getFilters(), getHeaders());
    }
}

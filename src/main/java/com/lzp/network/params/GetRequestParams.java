package com.lzp.network.params;

import com.lzp.network.service.NetInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by lillian on 2018/4/29.
 */

public class GetRequestParams<T> extends RequestParams<T> {

    public GetRequestParams(String baseUrl) {
        super(baseUrl);
    }

    public GetRequestParams(String baseUrl, String path) {
        super(baseUrl, path);
    }

    @Override
    public Call<ResponseBody> createCall(NetInterface netInterface) {
        return netInterface.getRequest(getPath(), getFilters(), getHeaders());
    }
}

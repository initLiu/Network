package com.lzp.network.params;

import com.lzp.network.service.NetInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by lillian on 2018/4/29.
 */

public class FormUePostRequestParams<T> extends RequestParams<T> {

    public FormUePostRequestParams(String baseUrl) {
        super(baseUrl);
    }

    public FormUePostRequestParams(String baseUrl, String path) {
        super(baseUrl, path);
    }

    @Override
    public Call<ResponseBody> createCall(NetInterface netInterface) {
        return netInterface.postRequestWithFormUrlEncode(getPath(), getFilters(), getHeaders());
    }
}

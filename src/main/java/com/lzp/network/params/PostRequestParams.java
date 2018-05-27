package com.lzp.network.params;

import com.lzp.network.service.NetInterface;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by lillian on 2018/4/29.
 */

public class PostRequestParams<T> extends RequestParams<T> {

    public PostRequestParams(String baseUrl) {
        this(baseUrl, null);
    }

    public PostRequestParams(String baseUrl, String path) {
        super(baseUrl, path);
    }

    @Override
    public String getEncodedFilters() {
        return mConvert.encode(getFilters());
    }

    @Override
    public Call<ResponseBody> createCall(NetInterface netInterface) {
        RequestBody body = RequestBody.create(MediaType.parse(mConvert.getContentType()), getEncodedFilters());
        return netInterface.postRequest(getPath(), body, getHeaders());
    }
}

package com.lzp.network.service;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by lillian on 2018/4/29.
 */

public interface NetInterface {
    @GET
    Call<ResponseBody> getRequest(@Url String url, @QueryMap Map<String, String> filters, @HeaderMap Map<String, String> headers);

    @POST
    Call<ResponseBody> postRequest(@Url String url, @Body RequestBody data, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postRequestWithFormUrlEncode(@Url String url, @FieldMap Map<String, String> datas, @HeaderMap Map<String, String> headers);
}

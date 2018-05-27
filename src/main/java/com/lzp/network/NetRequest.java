package com.lzp.network;

import com.lzp.network.params.RequestParams;
import com.lzp.network.service.NetInterface;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by lillian on 2018/4/29.
 */

public class NetRequest {
    private volatile static NetRequest sInstance;

    private NetRequest() {
    }

    public static NetRequest getInstance() {
        if (sInstance == null) {
            synchronized (NetRequest.class) {
                if (sInstance == null) {
                    sInstance = new NetRequest();
                }
            }
        }
        return sInstance;
    }

    public void request(final RequestParams params) {
        if (params.getContentConvert() == null) {
            throw new RuntimeException("ContentConvert should not be null,please call setContentConvert method first");
        }

        Retrofit retrofit = createRetrofit(params);

        NetInterface netInterface = retrofit.create(NetInterface.class);
        Call<ResponseBody> call = params.createCall(netInterface);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (params.getCallback() != null) {
                        params.getCallback().onResponse(params.getContentConvert().decode(response.body()));
                    }
                } catch (IOException e) {
                    if (params.getCallback() != null) {
                        params.getCallback().onFailure(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (params.getCallback() != null) {
                    params.getCallback().onFailure(t);
                }
            }
        });
    }

    private Retrofit createRetrofit(RequestParams params) {
        return new Retrofit.Builder()
                .baseUrl(params.getBaseUrl())
                .build();

    }
}

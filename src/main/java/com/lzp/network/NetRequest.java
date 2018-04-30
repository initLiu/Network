package com.lzp.network;

import android.util.Log;

import com.lzp.network.params.RequestParams;
import com.lzp.network.service.NetInterface;

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

    public void request(RequestParams params) {
        Retrofit retrofit = createRetrofit(params);

        NetInterface netInterface = retrofit.create(NetInterface.class);
        Call<String> call = params.createCall(netInterface);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Test", response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private Retrofit createRetrofit(RequestParams params) {
        return new Retrofit.Builder()
                .baseUrl(params.getBaseUrl())
                .addConverterFactory(JsonConverterFactory.create())
                .build();

    }
}

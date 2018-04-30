package com.lzp.network.params;

import com.lzp.network.service.NetInterface;

import retrofit2.Call;

/**
 * Created by lillian on 2018/4/30.
 */

public interface RetrofitCallInterface<T> {
    Call<T> createCall(NetInterface netInterface);
}

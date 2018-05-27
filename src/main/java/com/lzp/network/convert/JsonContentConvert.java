package com.lzp.network.convert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by lillian on 2018/5/27.
 */

public abstract class JsonContentConvert<T> extends AbstractContentConvert<T> {
    @Override
    public String encode(Map<String, String> params) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.toJson(params);
    }

    @Override
    public abstract T decode(ResponseBody responseBody) throws IOException;

    @Override
    public String getContentType() {
        return "application/json";
    }
}

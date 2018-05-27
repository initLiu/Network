package com.lzp.network.convert;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by lillian on 2018/5/27.
 */

public interface Codec<T> {
    String encode(Map<String, String> params);

    T decode(ResponseBody responseBody) throws IOException;
}

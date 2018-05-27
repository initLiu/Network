package com.lzp.network.convert;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by lillian on 2018/5/27.
 */

public interface Codec<T> {
    /**
     * 对http 请求参数，根据ContentType类型对请求参数进行编码（如编码为json）
     * @param params
     * @return
     */
    String encode(Map<String, String> params);

    /**
     * 对Http response内容进行解码，如解码为Json或字符串
     * @param responseBody
     * @return
     * @throws IOException
     */
    T decode(ResponseBody responseBody) throws IOException;
}

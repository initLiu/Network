package com.lzp.network.convert;

import java.util.Map;

/**
 * Created by lillian on 2018/5/27.
 */

public abstract class AbstractContentConvert<T> implements Codec<T> {
    public abstract String getContentType();
}

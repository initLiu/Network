package com.lzp.network.params;


/**
 * Created by lillian on 2018/5/27.
 */

public interface Callback<T> {
    /**
     * Invoked for a received HTTP response.
     */
    void onResponse(T response);

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     */
    void onFailure(Throwable t);
}

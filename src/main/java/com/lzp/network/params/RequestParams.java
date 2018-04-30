package com.lzp.network.params;

import com.lzp.network.service.NetInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by lillian on 2018/4/29.
 */

public abstract class RequestParams implements RetrofitCallInterface<String> {
    private Map<String, String> mHeaders = Collections.EMPTY_MAP;
    private Map<String, String> mFilters = Collections.EMPTY_MAP;
    private String mBaseUrl;
    private String mPath;

    /**
     * @param baseUrl base servel url,must not be null
     */
    public RequestParams(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    /**
     * @param baseUrl base servel url,must not be null
     * @param path    the path of the servel url,may be null
     */
    public RequestParams(String baseUrl, String path) {
        mBaseUrl = baseUrl;
        mPath = path;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public String getPath() {
        return mPath == null ? "" : mPath;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public Map<String, String> getFilters() {
        return mFilters;
    }

    public RequestParams addHeader(String key, String value) {
        if (mHeaders == Collections.EMPTY_MAP) {
            mHeaders = new HashMap<String, String>();
        }
        mHeaders.put(key, value);
        return this;
    }

    public RequestParams addHeader(Map<String, String> headers) {
        if (headers == null || headers.size() == 0) return this;

        if (mHeaders == Collections.EMPTY_MAP) {
            mHeaders = new HashMap<String, String>();
        }
        mHeaders.putAll(headers);
        return this;
    }

    public RequestParams addFilter(String key, String value) {
        if (mFilters == Collections.EMPTY_MAP) {
            mFilters = new HashMap<>();
        }
        mFilters.put(key, value);
        return this;
    }

    public RequestParams addFilter(Map<String, String> filters) {
        if (filters == null || filters.size() == 0) return this;

        if (mFilters == Collections.EMPTY_MAP) {
            mFilters = new HashMap<>();
        }
        mFilters.putAll(filters);
        return this;
    }

    public String getEncodedFilters() {
        return null;
    }

}

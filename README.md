# Network
基于Retrofit封装的网络请求，目前指进行了简单的get／post／formurl封装
对于网络请求的返回，统一使用了ResponseBody返回

# 使用方法
## Get
```java
RequestParams<String> params = new GetRequestParams<String>("https://xxx.xxx.xxx/")
        .addFilter("xxxx", "xxxx")
        .setContentConvert(new JsonContentConvert<String>() {
            @Override
            public String decode(ResponseBody responseBody) throws IOException {
                return responseBody.string();
            }
        }).setCallback(new Callback<String>() {
            @Override
            public void onResponse(String response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

NetRequest.getInstance().request(params);
```

## post
```java
RequestParams<AppData> params = new PostRequestParams<AppData>("https://xxx.xxx.xxx/", "xxx.php")
        .addFilter("xxxx", "xxx").addFilter("xxx", "0").addFilter("xxx", "asdadfadsf")
        .addHeader("xxxx", "xxxxx"))
        .setContentConvert(new JsonContentConvert<AppData>() {
            @Override
            public AppData decode(ResponseBody responseBody) throws IOException {
                Gson gson = new Gson();
                AuthData authData = gson.fromJson(responseBody.string(), AppData.class);
                return authData;
            }
        })
        .setCallback(new Callback<AppData>() {
            @Override
            public void onResponse(AppData response) {
                Log.e("Test", response.toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

NetRequest.getInstance().request(params);
```

## formUrlEncode
```java
RequestParams<String> params = new FormUePostRequestParams<String>("https://xxx.xxx.xxx", "xxx")
        .addFilter("xxx", "xxx").addFilter("xxx", "0").addFilter("xxx", "asdadfadsf")
        .addHeader("xxx", "xxxx")
        .setContentConvert(new JsonContentConvert<String>() {
            @Override
            public String decode(ResponseBody responseBody) throws IOException {
                return responseBody.string();
            }
        }).setCallback(new Callback<String>() {
            @Override
            public void onResponse(String response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
```
同时ContentConvert还支持自定义，只需要继承```AbstractContentConvert```
```java
public abstract class AbstractContentConvert<T> implements Codec<T> {
    public abstract String getContentType();
}
```
这是默认的ContentConvert的实现
```java
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
```
# Network
基于Retrofit封装的网络请求，目前指进行了简单的get／post／formurl封装
对于网络请求的返回，统一使用了string返回
```java
public class JsonConverterFactory extends Converter.Factory {
    public static JsonConverterFactory create() {
        return new JsonConverterFactory(new Gson());
    }

    private final Gson gson;

    private JsonConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JsonResponseBodyConverter();
    }
    
    private static final class JsonResponseBodyConverter implements Converter<ResponseBody, String> {

        JsonResponseBodyConverter() {
        }

        @Override
        public String convert(ResponseBody value) throws IOException {
            char[] buffer = new char[1024];
            StringBuffer sb = new StringBuffer();
            int len;
            while ((len = value.charStream().read(buffer)) != -1) {
                sb.append(buffer, 0, len);
            }
            return sb.toString();
        }
    }
}
```
# 使用方法
## Get
```java
RequestParams params = new GetRequestParams("http://xxx.xxx.xxx");
params.addFilter("xxxx", "ddddddd");
NetRequest.getInstance().request(params);
```

## post
```java
RequestParams params = new PostRequestParams("https://xxx.xx.xxx", "xxxx")
        .setContentType("application/json")
        .addFilter("xxx", "xxx").addFilter("xxx", "xx").addFilter("xx", "xxx")
        .addHeader("xxx", "xxxx");

NetRequest.getInstance().request(params);
```

## formUrlEncode
```java
RequestParams params = new FormUePostRequestParams("https://xxx.xxx.xxx", "xxx")
        .addFilter("xxx", "xxx").addFilter("xxx", "0").addFilter("xxx", "asdadfadsf")
        .addHeader("xxx", "xxxx");
```

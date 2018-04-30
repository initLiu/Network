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
```java
 private void getRequest() {
        RequestParams params = new GetRequestParams("https://iauth-sandbox.wecity.qq.com/config/webappconfig/");
        params.addFilter("appid", "sdk_4396");
        NetRequest.getInstance().request(params);
 }

    private void postRequest() {
        RequestParams params = new PostRequestParams("https://iauth-sandbox.wecity.qq.com/new/cgi-bin/", "appauth.php")
                .setContentType("application/json")
                .addFilter("appid", "4396").addFilter("type", "0").addFilter("uid", "asdadfadsf")
                .addHeader("signature", getSignature("appauth"));

        NetRequest.getInstance().request(params);
    }
    
    private void formUrlencode(){
         RequestParams params = new FormUePostRequestParams("https://iauth-sandbox.wecity.qq.com/new/cgi-bin/", "appauth.php")
         .addFilter("appid", "4396").addFilter("type", "0").addFilter("uid", "asdadfadsf")
         .addHeader("signature", getSignature("appauth"));
    }
    
```

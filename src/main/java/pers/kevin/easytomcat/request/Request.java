package pers.kevin.easytomcat.request;

import java.util.Map;

public class Request {

    private String method;
    private String host;
    private String url;
    private Map<String,String> params;
    
    
    
    public Request() {
        super();
        // TODO Auto-generated constructor stub
    }



    public String getMethod() {
        return method;
    }


    public void setMethod(String method) {
        this.method = method;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public Map<String, String> getParams() {
        return params;
    }




    public void setParams(Map<String, String> params) {
        this.params = params;
    }



    public String getHost() {
        return host;
    }


    public void setHost(String host) {
        this.host = host;
    }



    @Override
    public String toString() {
        return "Request [method=" + method + ", host=" + host + ", url=" + url + ", params=" + params + "]";
    }

    
    public String getAttribute(String param){
        return this.params.get(param);
    }
    public void setAttribute(String key,String value){
        this.params.put(key,value);
    }
    
    
    
}

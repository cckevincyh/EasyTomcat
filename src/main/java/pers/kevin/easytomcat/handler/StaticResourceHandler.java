package pers.kevin.easytomcat.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import pers.kevin.easytomcat.connet.ConnectInfo;
import pers.kevin.easytomcat.connet.Connector;
import pers.kevin.easytomcat.request.Request;
import pers.kevin.easytomcat.response.Response;
import pers.kevin.easytomcat.response.ResponseCodeType;
import pers.kevin.easytomcat.utils.FileUtil;

public class StaticResourceHandler {
    
    private Request request;
    private Response response;
    private static Map<String,String> redirectMap;
    private static final String REDIRECT_RESOURCE = "config/redirect.properties";
    public static Map<String, Object> mimeMap;
    public static final String MIME_PATH = "config/mime.json";
    static{
        redirectMap = FileUtil.getPropertiesMap(REDIRECT_RESOURCE);
    }
    
    
    public StaticResourceHandler(Request request, Response response) {
        super();
        this.request = request;
        this.response = response;
        initMimeType();
    };
    
    
    private void initMimeType(){
        String resource = FileUtil.getResource(MIME_PATH);
        if(resource != null){
            mimeMap = JSON.parseObject(resource);
        }
    }
    
    
    public void handle(){
        String url = request.getUrl();
        String mimeType = getMimeType(url);
        if(url.equals("/")){
            url = "/index.html";
        }
        if(response.getStatusCode() == null) {
        	response.setStatusCode(ResponseCodeType.OK);
        }
        response.setLocation(url);
        response.setContentType(mimeType);
        try {
            //output static resource.
            response.getResponseStream().write(response);
        } catch (FileNotFoundException e) {
            //sendRedirect
            String redirectUrl = redirectMap.get(url);
            if(redirectUrl != null){
                response.sendRedirect(redirectUrl);
            }else{
                //404 can't find static resources.
              response.setLocation(Connector.NOT_FOUND_PAGE);
              response.setStatusCode(ResponseCodeType.NOT_FOUND);
              response.setContentType("text/html");
              try {
                  response.getResponseStream().write(response);
              } catch (IOException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
              }
            }
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    private String getMimeType(String url){
        String mimeType = null;
        int lastIndex = url.lastIndexOf(".");
        if(lastIndex != -1){
            String substring = url.substring(lastIndex, url.length());
            mimeType = (String)mimeMap.get(substring);
        }
        return (mimeType != null) ? mimeType : "text/html";
    }
}

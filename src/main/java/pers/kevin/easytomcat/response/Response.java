package pers.kevin.easytomcat.response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class Response {
    private int contentLength;
    private String contentType;
    private String statusCode;
    private String location;
    private String responseBody;
    private ResponseStream responseStream;
    private String encodingCode = "UTF-8";
    
    
    
    public String getEncodingCode() {
        return encodingCode;
    }
    public void setEncodingCode(String encodingCode) {
        this.encodingCode = encodingCode;
    }
    public String getResponseBody() {
        return responseBody;
    }
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        try {
            if(this.responseBody != null){
                this.contentLength = this.responseBody.getBytes(this.encodingCode).length;
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public Response(ResponseStream responseStream) {
        super();
        this.responseStream = responseStream;
    }
    public ResponseStream getResponseStream() {
        return responseStream;
    }
    public void setResponseStream(ResponseStream responseStream) {
        this.responseStream = responseStream;
    }
    public int getContentLength() {
        return contentLength;
    }
    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void sendRedirect(String url){
        this.setResponseBody(null);
        this.setStatusCode(ResponseCodeType.FOUND);
        this.setLocation(url);
        try {
            this.responseStream.write(this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
  
    
}

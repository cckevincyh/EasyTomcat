package pers.kevin.easytomcat.response;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import pers.kevin.easytomcat.connet.Connector;

public class ResponseStream extends OutputStream{

    private OutputStream output;
    
    
    
    public ResponseStream(OutputStream output) {
        super();
        this.output = output;
    }


    @Override
    public void write(int arg0) throws IOException {
        // TODO Auto-generated method stub
        this.write(arg0);
    }
    
    
    public void write(Response response) throws IOException{
        InputStream resourceAsStream = null;
        if(response != null && response.getLocation() != null ){
            resourceAsStream = ResponseStream.class.getClassLoader().getResourceAsStream(Connector.WEB_ROOT + response.getLocation());
        }
        
        
        if(resourceAsStream != null){
            response.setContentLength(resourceAsStream.available());
        }else if(response != null && response.getResponseBody() != null) {
            response.setContentLength(response.getContentLength());
        }else if(response != null && !ResponseCodeType.FOUND.equals(response.getStatusCode())){
          // if response status is not FOUND(302) /404 can't find local staic resource.
            throw new FileNotFoundException("Not Found");
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + response.getStatusCode() + "\r\n")
        .append(ResponseHeaderType.CONTENT_TYPE + ": " + response.getContentType() + "\r\n")
        .append(ResponseHeaderType.CONTENT_LENGTH + ": " + response.getContentLength() + "\r\n")
        .append(ResponseHeaderType.LOCATION + ": " + response.getLocation() + "\r\n")
        .append("\r\n");
        BufferedOutputStream buffStream = new BufferedOutputStream(output);
        buffStream.write(sb.toString().getBytes());
        
        if(resourceAsStream != null){
            //write static resource.
            BufferedInputStream inputStream = new BufferedInputStream(resourceAsStream);
            byte[] buff = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buff)) != -1){
                buffStream.write(buff, 0, len);
            }
            if(inputStream != null){
                inputStream.close();
            }
            
        }else if(response != null && response.getResponseBody() != null){
            //write responseBody.
            buffStream.write(response.getResponseBody().getBytes());
        }
        
        
        if(buffStream != null){
            buffStream.flush();
            buffStream.close();
        }
    }
    
    

}

package pers.kevin.easytomcat.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;

public class RequestStream extends InputStream{
    
    private InputStream input;
    
    public RequestStream(InputStream input){
        this.input = input;
    }

    @Override
    public int read() throws IOException {
        // TODO Auto-generated method stub
        return input.read();
    }
    
    public Request readRequest()throws SocketException, IOException{
        Request request = new Request();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String content = "";
        int postContentLen = 0;
        StringBuilder sb = new StringBuilder();
        while(reader != null && !"".equals((content = reader.readLine())) && content != null){
            if (content.contains(": ")) {
                //get Request header
                String[] header = content.split(": ");
                if(header.length >= 2){
                    if(RequestHeaderType.CONTENT_LENGTH.equals(header[0])){
                        postContentLen = Integer.parseInt(header[1]);
                    }else if(RequestHeaderType.HOST.equals(header[0])){
                        request.setHost(header[1]);
                    }
                }
            }else{
                String[] split = content.split(" ");
                request.setMethod(split[0]);
                if(split.length >= 2){
                    request.setUrl(split[1]);
                }
            }
            sb.append(content + "\n");
        }
        //can't close socket close
//        if(reader != null){
//            reader.close();
//        }
        //System.out.println(sb);
        
        if(!RequestMethod.GET.toString().equals(request.getMethod())){
            char[] bs = new char[postContentLen];
            reader.read(bs);
            String params = new String(bs);
            params = URLDecoder.decode(params, "UTF-8");
            String[] split = params.split("&");
            HashMap<String, String> postParams = new HashMap<String, String>();
            for (String string : split) {
                String[] postparam = string.split("=");
                if(postparam.length == 2){
                    postParams.put(postparam[0], postparam[1]);
                } 
            }
            //set POST Parmas
            request.setParams(postParams);
        }else {
            URL url = new URL("http://" + request.getHost() + request.getUrl());
            String query = url.getQuery();
            if(query != null && !query.trim().equals("")){
                HashMap<String, String> params = new HashMap<String, String>();
                String decode = URLDecoder.decode(url.getQuery(), "UTF-8");
                if(decode.contains("&")){
                    String[] split = decode.split("&");
                    for (String string : split) {
                        String[] paramString = string.split("=");
                        if(paramString.length == 2){
                            params.put(paramString[0], paramString[1]);
                        }
                    }
                }else{
                    String[] paramString = decode.split("=");
                    if(paramString.length == 2){
                        params.put(paramString[0], paramString[1]);
                    }
                }
                ////set GET Parmas
                request.setParams(params);
            }
        }
        return request;
    }
    
    
    

}

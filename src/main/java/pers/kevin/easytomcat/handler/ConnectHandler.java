package pers.kevin.easytomcat.handler;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import pers.kevin.easytomcat.connet.ConnectInfo;
import pers.kevin.easytomcat.connet.Connector;
import pers.kevin.easytomcat.request.Request;
import pers.kevin.easytomcat.request.RequestMethod;
import pers.kevin.easytomcat.request.RequestStream;
import pers.kevin.easytomcat.response.Response;
import pers.kevin.easytomcat.response.ResponseCodeType;
import pers.kevin.easytomcat.response.ResponseStream;
import pers.kevin.easytomcat.servlet.BaseServlet;
import pers.kevin.easytomcat.servlet.factory.ServletFactory;

public class ConnectHandler {
    
    private Socket socket;
    public ConnectHandler(Socket socket) {
        super();
        this.socket = socket;
    }
    
    public void handle(){
        RequestStream requestStream = null;
        ResponseStream responseStream = null;
        try { 
            requestStream = new RequestStream(socket.getInputStream());
            responseStream = new ResponseStream(socket.getOutputStream());
            Request request = null;
            if(requestStream != null){
                request = requestStream.readRequest();
            }
            Response response = new Response(responseStream);
            try {
                if(request != null){
                    String url = request.getUrl();
                    BaseServlet createServlet = ServletFactory.createServlet(url);
                    if(createServlet != null){
                        createServlet.service(request, response);  
                    }else{
                        //can't find servlet , try to find static resources.
                        if(request != null && RequestMethod.GET.toString().equals(request.getMethod())){
                            StaticResourceHandler handler = new StaticResourceHandler(request,response);
                            handler.handle();
                        } 
                    }
                }
            }catch (Exception e) {
                // TODO: handle exception
                request.setUrl(Connector.ERROR_PAGE);
                response.setStatusCode(ResponseCodeType.INTERNAL_SERVER_ERROR);
                StaticResourceHandler handler = new StaticResourceHandler(request,response);
                handler.handle();
                e.printStackTrace();
            }
            
        }catch(SocketException e){
            //e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try{
                if(requestStream != null){
                    requestStream.close();
                }
                if(responseStream != null){
                    responseStream.close();
                }
                if(socket != null){
                    socket.close();
                }
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        
    }
    

}

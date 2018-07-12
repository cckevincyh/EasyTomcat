package pers.kevin.easytomcat.servlet;

import java.io.IOException;

import pers.kevin.easytomcat.request.Request;
import pers.kevin.easytomcat.request.RequestMethod;
import pers.kevin.easytomcat.response.Response;

public abstract class BaseServlet {
    
    public void service(Request request,Response response) throws IOException{
        if(request.getMethod().equals(RequestMethod.GET.toString())){
            doGet(request,response);
        }else if(request.getMethod().equals(RequestMethod.POST.toString())){
            doPost(request,response);
        }else if(request.getMethod().equals(RequestMethod.PUT.toString())){
            doPut(request,response);
        }else if(request.getMethod().equals(RequestMethod.DELETE.toString())){
            doDelete(request,response);
        }
    }
    
    public void doGet(Request request,Response response) throws IOException{
        
    }
    
    
    public void doPost(Request request,Response response) throws IOException{
        
    }
    
    public void doPut(Request request,Response response) throws IOException{
        
    }
    
    public void doDelete(Request request,Response response)throws IOException{
        
    }

}

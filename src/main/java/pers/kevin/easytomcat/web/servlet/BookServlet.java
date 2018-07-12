package pers.kevin.easytomcat.web.servlet;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import pers.kevin.easytomcat.request.Request;
import pers.kevin.easytomcat.response.Response;
import pers.kevin.easytomcat.response.ResponseCodeType;
import pers.kevin.easytomcat.servlet.BaseServlet;
import pers.kevin.easytomcat.web.domain.Book;

public class BookServlet extends BaseServlet{
    
    public static ConcurrentHashMap<String, Book> books;
    
    static{
        books = new ConcurrentHashMap<String, Book>();
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String uuid2 = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        Book javaBook = new Book(uuid, "Java程序设计", 58.5f, 20);
        Book javaScriptBook = new Book(uuid2, "javaScript程序设计", 45.5f, 20);
        books.put(uuid, javaBook);
        books.put(uuid2, javaScriptBook);
        
    }

    
    @Override
    public void doGet(Request request, Response response) throws IOException {
        // TODO Auto-generated method stub
        response.setContentType("application/json; charset=utf-8");
        response.setEncodingCode("UTF-8");
        response.setStatusCode(ResponseCodeType.OK);
        super.doGet(request, response);
        
        if(request.getUrl().lastIndexOf("/")!= 0){
            String id = request.getUrl().substring(request.getUrl().lastIndexOf("/") + 1,request.getUrl().length());
            response.setResponseBody(JSON.toJSONString(books.get(id)));
            try {
                response.getResponseStream().write(response);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            response.setResponseBody(JSONArray.toJSONString(books.values()));
            try {
                response.getResponseStream().write(response);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
    
    @Override
    public void doPost(Request request, Response response) throws IOException {
        // TODO Auto-generated method stub
        super.doPost(request, response);
        String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String name = request.getAttribute("name");
        float price = Float.parseFloat(request.getAttribute("price"));
        int count = Integer.parseInt(request.getAttribute("count"));
        Book book = new Book(id, name, price, count);
        books.put(id, book);
        response.setResponseBody(JSONArray.toJSONString(books.values()));
        try {
            response.getResponseStream().write(response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void doPut(Request request, Response response) throws IOException {
        // TODO Auto-generated method stub
        super.doPut(request, response);
        String id = request.getAttribute("id");
//        String name = request.getAttribute("name");
//        float price = Float.parseFloat(request.getAttribute("price"));
//        int count = Integer.parseInt(request.getAttribute("count"));
//        Book book = new Book(id, name, price, count);
//        books.put(id, book);
//        response.setResponseBody(JSONArray.toJSONString(books.values()));
//        try {
//            response.getResponseStream().write(response);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        Book book = books.get(id);
        if(book.getCount() > 0){
            book.setCount(book.getCount()-1);
            books.put(id, book);
        }
        response.setResponseBody(JSONArray.toJSONString(books.values()));
        try {
            response.getResponseStream().write(response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void doDelete(Request request, Response response) throws IOException {
        // TODO Auto-generated method stub
        super.doDelete(request, response);
        String id = request.getAttribute("id");
        books.remove(id);
        response.setResponseBody(JSONArray.toJSONString(books.values()));
        try {
            response.getResponseStream().write(response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
 
}

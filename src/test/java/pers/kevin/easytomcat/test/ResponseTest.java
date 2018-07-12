package pers.kevin.easytomcat.test;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

import pers.kevin.easytomcat.response.Response;
import pers.kevin.easytomcat.response.ResponseStream;

public class ResponseTest {
    
    @Test
    public void testGetResponse() throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        inputStream.read();
        OutputStream out = socket.getOutputStream();
//        out.write(("HTTP/1.1 200 OK\r\n"+
//"Server: Apache-Coyote/1.1\r\n"+
//"Content-Type: text/html\r\n"+
//"Content-Length: 105\r\n"+
//"Date: Fri, 24 Mar 2017 07:01:08 GMT\r\n\r\n"+
// "<html><body><div style='text-weight:bold'><a href='/test' target='_blank'>1111111</a></div></body></html>").getBytes());
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        out.write("http/1.1 200 ok\n\n\naaaaa".getBytes());
//        ResponseStream responseStream = new ResponseStream(out);
//        Response response = new Response();
//        response.setContentType("text/html");
//        response.setStatusCode(200);
//        String s = "<html><body><div style='text-weight:bold'><a href='/test' target='_blank'>1111111</a></div></body></html>";
//        response.setResponseBody(s);
//        response.setContentLength(s.length());
//        responseStream.writeResponse(response);
//        responseStream.close();
        out.flush();
        out.close();
    }

  /*  
    @Test
    public void testGetResponse2() throws Exception {
        ServerSocket serverSocket = new ServerSocket(9000);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        inputStream.read();
        OutputStream out = socket.getOutputStream();
        ResponseStream responseStream = new ResponseStream(out);
        Response response = new Response();
        response.setContentType("text/html");
        response.setStatusCode("404");
        String s = "<html><body><div style='text-weight:bold'><a href='/test' target='_blank'>1111111</a></div></body></html>";
        //response.setResponseBody(s);
        response.setContentLength(s.length());
        responseStream.writeResponse(response);
        responseStream.close();
        out.flush();
        out.close();
    }
*/
    
}

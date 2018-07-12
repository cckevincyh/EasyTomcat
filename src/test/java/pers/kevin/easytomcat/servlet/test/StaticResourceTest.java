package pers.kevin.easytomcat.servlet.test;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;

import org.junit.Test;

import pers.kevin.easytomcat.handler.StaticResourceHandler;
import pers.kevin.easytomcat.request.Request;
import pers.kevin.easytomcat.response.Response;
import pers.kevin.easytomcat.response.ResponseStream;

public class StaticResourceTest {
	
	
	@Test
	public void testStaticResource() throws Exception {
		
		Request request = new Request();
		request.setUrl("/index.html");
		request.setMethod("GET");
		ResponseStream responseStream = new ResponseStream(new BufferedOutputStream(System.out));
		Response response = new Response(responseStream);
		response.setContentType("text/html; charset=utf-8");
		StaticResourceHandler handler = new StaticResourceHandler(request, response);
		handler.handle();
	}

}

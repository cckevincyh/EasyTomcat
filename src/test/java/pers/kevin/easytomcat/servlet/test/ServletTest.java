package pers.kevin.easytomcat.servlet.test;


import java.io.BufferedOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import pers.kevin.easytomcat.request.Request;
import pers.kevin.easytomcat.response.Response;
import pers.kevin.easytomcat.response.ResponseStream;
import pers.kevin.easytomcat.servlet.BaseServlet;
import pers.kevin.easytomcat.servlet.factory.ServletFactory;
import pers.kevin.easytomcat.web.domain.Book;
import pers.kevin.easytomcat.web.servlet.BookServlet;

public class ServletTest {
	
	@Test
	public void testServletGetAll() throws Exception {
		BufferedOutputStream output = new BufferedOutputStream(System.out);
		ResponseStream responseStream = new ResponseStream(output);
		Response response = new Response(responseStream);
		Request request = new Request();
		request.setUrl("/books");
		request.setMethod("GET");
		response.setEncodingCode("UTF-8");
		response.setResponseBody("hello");
		BaseServlet createServlet = ServletFactory.createServlet(request.getUrl());
		createServlet.service(request, response);
	}
	
	
	@Test
	public void testServletPost() throws Exception {
		BufferedOutputStream output = new BufferedOutputStream(System.out);
		ResponseStream responseStream = new ResponseStream(output);
		Response response = new Response(responseStream);
		Request request = new Request();
		Map<String, String> params = new HashMap<String,String>();
		params.put("name", "PHP是最好的语言");
		params.put("price", "100");
		params.put("count", "20");
		request.setParams(params);
		request.setUrl("/books");
		request.setMethod("POST");
		response.setStatusCode("200");
		response.setContentType("text/html");
		response.setEncodingCode("UTF-8");
		response.setResponseBody("hello");
		BaseServlet createServlet = ServletFactory.createServlet(request.getUrl());
		createServlet.service(request, response);
	}
	
	
	@Test
	public void testServletGet() throws Exception {
		BufferedOutputStream output = new BufferedOutputStream(System.out);
		ResponseStream responseStream = new ResponseStream(output);
		Response response = new Response(responseStream);
		Request request = new Request();
		Map<String,Book> books = BookServlet.books;
		Book object = (Book) books.values().toArray()[0];
		System.out.println(object.getId());
		String id = object.getId();
		request.setUrl("/books/" + id);
		request.setMethod("GET");
		response.setEncodingCode("UTF-8");
		BaseServlet createServlet = ServletFactory.createServlet(request.getUrl());
		createServlet.service(request, response);
	}
	
	
	@Test
	public void testServletDelete() throws Exception {
		BufferedOutputStream output = new BufferedOutputStream(System.out);
		ResponseStream responseStream = new ResponseStream(output);
		Response response = new Response(responseStream);
		Request request = new Request();
		Map<String,Book> books = BookServlet.books;
		Book object = (Book) books.values().toArray()[0];
		System.out.println(object.getId());
		HashMap<String, String> map = new HashMap();
		String id = object.getId();
		map.put("id", id);
		request.setParams(map);
		request.setUrl("/books");
		request.setMethod("DELETE");
		response.setStatusCode("200");
		response.setContentType("text/html");
		response.setEncodingCode("UTF-8");
		BaseServlet createServlet = ServletFactory.createServlet(request.getUrl());
		createServlet.service(request, response);
	}
	
	
	
}

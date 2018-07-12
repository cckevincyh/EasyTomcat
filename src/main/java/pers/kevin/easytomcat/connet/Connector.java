package pers.kevin.easytomcat.connet;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pers.kevin.easytomcat.utils.FileUtil;

import com.alibaba.fastjson.JSON;

public class Connector {
    
    public static int PORT;
    public static int INITPROCESSORS;
    public static int TIMEOUT;
    public static String ERROR_PAGE;
    public static String NOT_FOUND_PAGE;
    public static final String CONFIG_PATH = "config/config.properties";
    public static final String WEB_ROOT = "WebRoot/static";
   

    public Connector() {
        super();
        // TODO Auto-generated constructor stub
        init();
    }
    

    //init connect, config.properties
    private void init(){
       Properties properties = new Properties();
       InputStream in = Connector.class.getClassLoader().getResourceAsStream(CONFIG_PATH);
       try {
           properties.load(in);
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       PORT = Integer.parseInt(properties.getProperty(ConnectInfo.PORT));
       INITPROCESSORS = Integer.parseInt(properties.getProperty(ConnectInfo.INITPROCESSORS));
       TIMEOUT = Integer.parseInt(properties.getProperty(ConnectInfo.TIMEOUT));
       ERROR_PAGE = properties.getProperty(ConnectInfo.ERROR_PAGE);
       NOT_FOUND_PAGE = properties.getProperty(ConnectInfo.NOT_FOUND_PAGE);
    }
    
    
    
    
    public void connect() throws IOException{
        ServerSocket serverSocket = new ServerSocket(PORT);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(INITPROCESSORS);
        while(true) {
           Socket socket = serverSocket.accept();
           fixedThreadPool.execute(new ConnectThread(socket));
        }
        
    }

}

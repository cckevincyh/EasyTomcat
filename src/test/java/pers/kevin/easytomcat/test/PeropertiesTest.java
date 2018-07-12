package pers.kevin.easytomcat.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;

import pers.kevin.easytomcat.connet.Connector;
import pers.kevin.easytomcat.handler.ConnectHandler;
import pers.kevin.easytomcat.servlet.BaseServlet;
import pers.kevin.easytomcat.servlet.factory.ServletFactory;
import pers.kevin.easytomcat.utils.FileUtil;

public class PeropertiesTest {

    
    @Test
    public void testInit() throws Exception {
        Connector connector = new Connector();
        //connector.init();
        //connector.initMimeType();
        
    }
    
    @Test
    public void testMime() throws Exception {
       // ConnectHandler.getMimeType("/index.html");
//        String resource = FileUtil.getResource(Connector.WEB_ROOT + "/index.html");
//        System.out.println(resource);
//        byte[] staticResource = FileUtil.getStaticResource("WebRoot/static/images/custom.jpg");
//        System.out.println(staticResource.length);
//        FileOutputStream outputStream = new FileOutputStream("1.jpg");
//        outputStream.write(staticResource);
        BaseServlet createServlet = ServletFactory.createServlet("/book");
        createServlet.service(null, null);
//        
    }
    
}

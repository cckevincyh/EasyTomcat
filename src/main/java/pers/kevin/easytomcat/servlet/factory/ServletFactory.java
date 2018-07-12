package pers.kevin.easytomcat.servlet.factory;

import java.util.Map;

import pers.kevin.easytomcat.servlet.BaseServlet;
import pers.kevin.easytomcat.utils.FileUtil;

public class ServletFactory {
    
    private static Map<String,String> servletMap;
    private final static String DEFAULT_WEB_CONFIG = "config/web.properties";
    
    private ServletFactory(){
        
    }
    
    public static BaseServlet createServlet(String url){
        servletMap = FileUtil.getPropertiesMap(DEFAULT_WEB_CONFIG);
        // /books/id
        if(url != null && url.lastIndexOf("/") != 0){
            // /books
            url = url.substring(0,url.lastIndexOf("/"));
        }
        String servletName = servletMap.get(url);
        BaseServlet baseServlet = null;
        if(servletName != null){
            try{
                Object obj = Class.forName(servletName).newInstance();
                if(obj instanceof BaseServlet){
                    baseServlet = (BaseServlet) Class.forName(servletName).newInstance();
                }
            }catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
        
        return baseServlet;
    }

}

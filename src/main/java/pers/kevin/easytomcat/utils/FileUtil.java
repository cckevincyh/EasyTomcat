package pers.kevin.easytomcat.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import pers.kevin.easytomcat.servlet.factory.ServletFactory;

public class FileUtil {
    
    
    public static Map<String,String> getPropertiesMap(String path){
        Properties properties = new Properties();
        InputStream in = ServletFactory.class.getClassLoader().getResourceAsStream(path);
        try {
            properties.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new HashMap<String, String>((Map)properties);
    }
    
    public static String getResource(String filePath){
        InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream(filePath);
        if(resourceAsStream != null){
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            String content = "";
            StringBuilder sb = new StringBuilder();
            try {
                while((content = reader.readLine()) != null){
                    sb.append(content);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }else{
            return null;
        }
    }
    
    
    public static String getStaticResource(String filePath) throws FileNotFoundException{
        InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream(filePath);
        if(resourceAsStream != null){
            BufferedInputStream reader = new BufferedInputStream(resourceAsStream);
            StringBuilder sb = new StringBuilder();
            byte[] buff = new byte[1024];
            int len = 0;
            try {
                while((len = reader.read(buff)) != -1){
                    sb.append(new String(buff,0,len));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }else{
            throw new FileNotFoundException("Not Found");
        }
    }
    
        
    


}

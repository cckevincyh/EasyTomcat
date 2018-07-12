package pers.kevin.easytomcat.server;

import java.io.IOException;

import pers.kevin.easytomcat.connet.Connector;


public class WebServer {
    public static void main(String[] args) {
        try {
            Connector connector = new Connector();
            //start connect
            connector.connect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

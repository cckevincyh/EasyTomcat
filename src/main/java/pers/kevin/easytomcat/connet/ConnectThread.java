package pers.kevin.easytomcat.connet;

import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import pers.kevin.easytomcat.handler.ConnectHandler;

public class ConnectThread implements Runnable{
    
    private Socket socket;
  

    public ConnectThread(Socket socket) {
        super();
        this.socket = socket;
    }



    public Socket getSocket() {
        return socket;
    }



    public void setSocket(Socket socket) {
        this.socket = socket;
    }



    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                try {
                    if(socket != null){
                        //System.out.println(Thread.currentThread().getName() + "关闭");
                        socket.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        },Connector.TIMEOUT);
        ConnectHandler handler = new ConnectHandler(socket);
        handler.handle();
    }

    
}

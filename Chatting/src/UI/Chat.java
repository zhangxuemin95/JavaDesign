package UI;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Chat {
    public ArrayList<String> friendname;
    public static void main(String arg[]) {
        //new Frame("");
        //new Chat();
        //new Administrator("陈某");
    }
    public Chat(){
        /*
        Frame client = new Frame("客户端");
        Frame server = new Frame("服务端");
        try{
            new SendThread(new Socket("127.0.0.1",18888)).run(client);
            new SendThread(new Socket("127.0.0.1",18888)).run(server);
            new Recieve(new ServerSocket(18888).accept()).run(client);
            new Recieve(new ServerSocket(18888).accept()).run(server);
        }catch (Exception e){
            e.printStackTrace();
        }
         */
    }
}

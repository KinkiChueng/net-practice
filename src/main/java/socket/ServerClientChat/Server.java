package socket.ServerClientChat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lasia on 2018/3/26.
 */
public class Server extends Thread {
    //定义服务器接口serverSocket
    ServerSocket serverSocket = null;

    //定义一个服务器，定义端口
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("服务器在启动中。。。等待客户连接");
            while(true) {
                //accept是个阻塞进程，等到有用户连接才能走下去
                Socket socket = serverSocket.accept();
                //通过socket对象可以获取输出流，用来写数据
                OutputStream os = socket.getOutputStream();
                //向客户端发送消息
                os.write("服务器正在向你发送消息！".getBytes());
                //在服务器上显示连接上的电脑
                System.out.println(socket.getInetAddress().getHostAddress() + "连接上了！");
                //通过socket对象可以获得输入流，用来读取用户数据
                InputStream inputStream = socket.getInputStream();
                //读取数据
                int len=0;
                byte[] buf=new byte[1024];
                while ((len=inputStream.read(buf))!=-1) {
                    //直接把获得的数据打印出来
                    System.out.println("服务器接收到客户端的数据："+new String(buf,0,len));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

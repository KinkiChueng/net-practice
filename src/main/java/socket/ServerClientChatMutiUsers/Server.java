package socket.ServerClientChatMutiUsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lasia on 2018/3/27.
 */
public class Server extends Thread {
    //用来存放连接上的用户的socket对象的值
    List list = new ArrayList<Socket>();

    //定义服务器接口ServerSocket
    ServerSocket serverSocket = null;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送消息的线程
    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                String message = socket.getInetAddress().getHostAddress();
                System.out.println(message + "连接上了");
                //向用户发送消息
                SengMessageToAllUser(message);
                //把连接上的用户添加到集合
                list.add(socket);
                new ReadThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //把消息发送给每个用户，涉及到写操作的OutStream
    private void SengMessageToAllUser(String message) {
        //拿到每一个用户的socket对象，对其进行写入数据
        for (Socket socket : (ArrayList<Socket>)list) {
            //判断之前保存的连接是否还在
            if (socket != null && socket.isConnected()) {
                try {
                    OutputStream os = socket.getOutputStream();
                    os.write(message.getBytes());
                    os.flush();//刷新写入数据
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class ReadThread extends Thread {
        InputStream is = null;

        public ReadThread(Socket socket) {
            try {
                //获取输入流
                is = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            try {
                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    //把读取到的数据发送给其他用户
                    SengMessageToAllUser(new String(buf,0,len));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

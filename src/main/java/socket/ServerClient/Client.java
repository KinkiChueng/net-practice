package socket.ServerClient;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by lasia on 2018/3/26.
 */
public class Client extends Thread {
    Socket socket = null;

    @Override
    public void run() {
        //客户端一连接就可以写数据服务器了
        new sendMessThread().start();
        try {
            //读取Sock里的数据，读取服务器发送的消息
            InputStream s = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = s.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class sendMessThread extends Thread {
        @Override
        public void run() {
            //写操作
            Scanner scanner = null;
            OutputStream outputStream = null;
            try {
                scanner = new Scanner(System.in);
                outputStream = socket.getOutputStream();
                String in;
                do {
                    in = scanner.next();
                    outputStream.write(("客户端：" + in).getBytes());
                    outputStream.flush();
                } while (!in.equals("bye"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Client(String host, int port) {
        try {
            //需要服务器的IP地址和端口号，才能获得Socket对象
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

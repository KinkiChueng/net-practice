package netIO.BIO;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * C/S模型，两个进程见的通信
 * Created by lasia on 2018/3/29.
 */
public final class ServerNormal {
    private static int DEFAULT_PORT = 1234;
    //单例的ServerSocket
    private static ServerSocket serverSocket;
    //设置监听端口
    public static void start() {
        start(DEFAULT_PORT);
    }

    //这个方法不会被大量的并发访问，不太需要考虑效率，直接进行方法同步就行了
    public synchronized static void start(int port) {
        if (serverSocket != null) return;
        try {
            //通过构造函数创建serversocket
            serverSocket = new ServerSocket(port);
            System.out.println("服务器已启动，端口号：" + port);
            //通过无线
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

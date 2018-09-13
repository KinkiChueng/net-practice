package socket.eight_ServerSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * http://www.cnblogs.com/dongguacai/p/5747603.html
 * Created by lasia on 2017/11/22.
 */
public class Server {
    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        System.out.println("server start");
        while (true) {
           Socket socket = ss.accept();
           OutputStream outputStream = socket.getOutputStream();
           PrintStream printStream = new PrintStream(outputStream);
           printStream.print("hello world");
           printStream.close();
           outputStream.close();
           socket.close();
        }
    }
}

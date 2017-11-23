package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * http://www.cnblogs.com/dongguacai/p/5747603.html
 * Created by lasia on 2017/11/23.
 */
public class Server1 {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket socket = serverSocket.accept();
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        printStream.print("服务端：开源");
        printStream.print("服务端");
        //关闭输出流说明输出已经结束
        socket.shutdownOutput();
        //判断socket是否关闭
        System.out.println(socket.isClosed());
        Scanner scanner = new Scanner(socket.getInputStream());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
        socket.close();
        serverSocket.close();
    }
}

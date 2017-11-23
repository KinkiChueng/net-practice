package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * http://www.cnblogs.com/dongguacai/p/5747603.html
 * Created by lasia on 2017/11/23.
 */
public class Client1 {
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("localhost",5555);
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int flag = 0;
        while (-1 != (flag = inputStream.read(buffer,0,buffer.length))) {
             String str = new String(buffer,0,flag);
             System.out.println(str);
        }
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        printStream.println("客户端");
        inputStream.close();
        printStream.close();
        socket.close();
    }
}

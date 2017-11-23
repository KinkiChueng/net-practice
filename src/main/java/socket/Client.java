package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * http://www.cnblogs.com/dongguacai/p/5747603.html
 * Created by lasia on 2017/11/22.
 */
public class Client {
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("localhost",8888);
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
        inputStream.close();
        socket.close();
    }
}

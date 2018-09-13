package socket.eight_ServerSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 8-1 读取服务器时间
 * Created by lasia on 2018/2/4.
 */
public class DayTimeClient {
    public static void main(String args[]) {
        String hostname = args.length > 0 ? args[0] : "time.nist.gov";
        Socket socket = null;
        try {
            socket = new Socket(hostname,13);
            socket.setSoTimeout(15000);
            InputStream inputStream = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(inputStream,"ASCII");
            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char)c);
            }
            System.out.println(time);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

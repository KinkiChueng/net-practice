package socket.nine_ServerSocket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 由于可能抛出两类异常：1，可能关闭服务器并记录一个错误信息
 * 2，只关闭活动连接
 * 必须区分开
 * Created by Administrator on 2018/3/13.
 */
public class DayTimeServer {
    public final static int PORT = 13;

    public static void main(String args[]) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = serverSocket.accept()){
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    out.write(now.toString());
                    out.flush();
                    connection.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

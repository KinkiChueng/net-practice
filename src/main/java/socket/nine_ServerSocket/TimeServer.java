package socket.nine_ServerSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 时间服务器9-2
 * Created by Administrator on 2018/3/14.
 */
public class TimeServer {
    public final static int PORT = 37;

    public static void main(String args[]) {
        //时间协议将时间起点设在1900年
        //而date设置在1970年
        // 需要进行转换
        long differencdBetweenEpochs = 2208988800L;
        try (ServerSocket server = new ServerSocket(PORT)){
            while (true) {
                try (Socket connection = server.accept()) {
                    OutputStream outputStream = connection.getOutputStream();
                    Date now = new Date();
                    long msSince1970 = now.getTime();
                    long secondsSince1970 = msSince1970/1000;
                    long secondsSince1900 = secondsSince1970 + differencdBetweenEpochs;

                    byte[] time = new byte[4];
                    time[0] = (byte)((secondsSince1900 & 0x00000000FF000000L)>>24);
                    time[1] = (byte)((secondsSince1900 & 0x00000000FF000000L)>>16);
                    time[2] = (byte)((secondsSince1900 & 0x00000000FF000000L)>>8);
                    time[3] = (byte)((secondsSince1900 & 0x00000000FF000000L));

                    outputStream.write(time);
                    outputStream.flush();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

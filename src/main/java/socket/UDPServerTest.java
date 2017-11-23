package socket;

import java.io.IOException;
import java.net.*;

/**
 * http://www.cnblogs.com/dongguacai/p/5747603.html
 * Created by lasia on 2017/11/23.
 */
public class UDPServerTest {
    public static void main(String args[]) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        String string = "hello world";
        //构造用于发送的数据包，指定主机和端口号
        DatagramPacket packet1 = new DatagramPacket(string.getBytes(),string.length(), InetAddress.getByName("localhost"),5555);
        datagramSocket.send(packet1);

        //读取客户端发送过来的响应
        byte[] buffer = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(buffer,buffer.length);
        datagramSocket.receive(packet2);
        String str2 = new String(buffer,0,packet2.getLength());
        System.out.println(str2);
        datagramSocket.close();
    }
}

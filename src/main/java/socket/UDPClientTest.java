package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * http://www.cnblogs.com/dongguacai/p/5747603.html
 * Created by lasia on 2017/11/23.
 */
public class UDPClientTest {
    public static void main(String args[]) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(5555);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(packet);
        String str = new String(buffer,0, packet.getLength());
        System.out.println(str);

        //接收到数据包之后，客户端返回响应回去
        String str2 = "welcome";
        DatagramPacket packet1 = new DatagramPacket(str2.getBytes(),str2.length(),packet.getAddress(),
                packet.getPort());
        datagramSocket.send(packet1);
        datagramSocket.close();
    }
}

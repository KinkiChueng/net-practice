package socket.ServerClientChat;

/**
 * Created by lasia on 2018/3/26.
 */
public class ClientTest {
        public static void main(String[] args) {
            //需要服务器的正确的IP地址和端口号
            Client client=new Client("localhost", 6768);
            client.start();
        }
}

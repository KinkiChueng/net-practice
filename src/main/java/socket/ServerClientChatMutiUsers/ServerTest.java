package socket.ServerClientChatMutiUsers;

/**
 * Created by lasia on 2018/3/27.
 */
public class ServerTest {
    public static void main(String args[]) {
        Server server = new Server(6768);
        server.start();
    }
}

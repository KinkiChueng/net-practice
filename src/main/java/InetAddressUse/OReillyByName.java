package InetAddressUse;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by lasia on 2017/11/17.
 */
public class OReillyByName {
    public static void main(String args[]) throws UnknownHostException {
        //InetAddress address = InetAddress.getByName("www.baidu.com");
        InetAddress address = InetAddress.getByName("119.75.213.61");
        System.out.println(address);

        InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
        for (InetAddress address1 : addresses) {
            System.out.println(address1);
        }

        InetAddress me = InetAddress.getLocalHost();
        System.out.println(me);

        byte[] address2 = {107,23,(byte)216,(byte)196};
        InetAddress lessWrong = InetAddress.getByAddress(address2);
        InetAddress lessWrongWithname = InetAddress.getByAddress("lesswrong.com", address2);
        System.out.println(lessWrong + "   " + lessWrongWithname);

        //InetAddress machine = InetAddress.getLocalHost();
        InetAddress machine = InetAddress.getByName("119.75.216.20");
        //String localhost = machine.getHostName();
        String localhost = machine.getCanonicalHostName();
        System.out.println("localhost : " + localhost);

        String dottedOuad = me.getHostAddress();
        System.out.println("my address is :" + dottedOuad);
    }
}

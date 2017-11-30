package InetAddressUse.Useable;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**4-9
 * 监视垃圾邮件发送者
 * 逆置这个地址的字节，增加黑洞服务的域，然后查找这个地址，如果这个地址被找到，说明它是一个已知的垃圾邮件发送者
 *
 * Created by lasia on 2017/11/29.
 */
public class SpamCheck {
    public static final String BLACKHOLE = "sbl.spamhaus.org";

    public static void main(String args[]) {
        for (String arg:args) {
            if (isSpammer(arg)) {
                System.out.println(arg + " is a unknown spammer");
            } else {
                System.out.println(arg + " appears legitimate");
            }
        }
    }

    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
          // e.printStackTrace();
            return false;
        }
    }
}

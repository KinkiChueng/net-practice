package InetAddressUse;

import java.net.InetAddress;

/**
 * Created by lasia on 2017/11/28.
 */
public class AddressTests {
    public static int getVersion(InetAddress inetAddress) {
        byte[] address = inetAddress.getAddress();
        if (address.length == 4) return 4;
        else if (address.length == 16) return 6;
        else return -1;
    }
}

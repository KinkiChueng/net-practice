package InetAddressUse.NetworkInterface;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by lasia on 2017/11/28.
 *
 * lo 回环接口(loop back) 或者 本地主机(localhost)
 * gif 通用 IP-in-IP隧道(RFC2893)
 * stf 6to4连接(RFC3056)
 * en 以太网或802.11接口
 * fw IP over FireWire(IEEE-1394), macOS特有
 * p2p Point-to-Point 协议
 * awdl airdrop peer to peer(一种mesh network), apple airdrop设备特有
 * bridge 第2层桥接vlan 虚拟局域网络
 * utun0 回调
 */
public class NetworkInterfaceTest {
    public static void main(String args[]) {
        try {
            NetworkInterface networkInterface = NetworkInterface.getByName("lo0");
            if (networkInterface == null) {
                System.err.println("no such interface: eth0");
            }

            InetAddress local = InetAddress.getByName("127.0.0.1");
            NetworkInterface networkInterface1 = NetworkInterface.getByInetAddress(local);
            if (networkInterface1 == null) {
                System.err.println("that is weird.no local loopback address");
            }

            //列出网络接口的程序 4-8 interfaceListener
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (interfaceEnumeration.hasMoreElements()) {
                System.out.println(interfaceEnumeration.nextElement());
            }

            NetworkInterface eth0 = NetworkInterface.getByName("en0");
            Enumeration addresses = eth0.getInetAddresses();
            while (addresses.hasMoreElements()) {
                System.out.println(addresses.nextElement());
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

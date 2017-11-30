package InetAddressUse.Useable;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**4-10
 * Created by lasia on 2017/11/29.
 * 7以上的版本支持
 * http://www.oschina.net/question/12_10706
 * try() 资源的自动释放，不用在finally里关闭资源
 */
public class Weblog {
    public static void main(String args[]) {
        try (FileInputStream fileInputStream = new FileInputStream(args[0]);
             Reader in = new InputStreamReader(fileInputStream);
             BufferedReader bin = new BufferedReader(in)) {
             for (String entry = bin.readLine(); entry != null; entry = bin.readLine()) {
                int index = entry.indexOf(' ');
                String ip = entry.substring(0, index);
                String theRest = entry.substring(index);

                //向DNS请求主机名并显示
                try {
                    InetAddress address = InetAddress.getByName(ip);
                    System.out.println(address.getHostName() + theRest);
                } catch (UnknownHostException e) {
                    System.err.println(entry);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

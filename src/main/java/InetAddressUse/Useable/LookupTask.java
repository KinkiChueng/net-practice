package InetAddressUse.Useable;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

/** 4-11
 * Created by lasia on 2017/11/29.
 */
public class LookupTask implements Callable<String> {
    private String line;

    public LookupTask(String line) {
        this.line = line;
    }

    public LookupTask() {
    }

    @Override
    public String call() {
        try {
            int index = line.indexOf(" ");
            String address = line.substring(0, index);
            String theRest = line.substring(index);
            String hostname = InetAddress.getByName(address).getHostName();
            return hostname + " " + theRest;
        } catch (UnknownHostException e) {
          //  e.printStackTrace();
            return line;
        }
    }
}

package URLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 显示整个http首部
 * Created by lasia on 2018/1/15.
 */
public class AllHeader {
    public static void main(String args[]) {
        for (int i = 0; i < args.length; i++) {
            try {
                URL u = new URL(args[i]);
                URLConnection urlConnection = u.openConnection();
                for (int j = 1; ; j++) {
                    String header = urlConnection.getHeaderField(j);
                    if (header == null) break;
                    System.out.println(urlConnection.getHeaderFieldKey(j) + ":" + header);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package URLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 7-12 获取指向URLconnection的URL
 * Created by lasia on 2018/1/15.
 */
public class URLPrinter {
    public static void main(String args[]) {
        try {
            URL url = new URL("http://www.oreilly.com/");
            URLConnection urlConnection = url.openConnection();
            System.out.println(urlConnection.getURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package URLConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * 7-13 将ifModifiedSince设置为24小时前
 * Created by lasia on 2018/1/15.
 */
public class Last24 {
    public static void main(String args[]) {
        Date today = new Date();
        long millisecondsPerDay = 24 * 60 * 60 * 1000;

        for (int i = 0; i < args.length; i++) {
            try {
                URL url = new URL(args[i]);
                URLConnection urlConnection = url.openConnection();
                System.out.println("Will retrieve file if it's modified since" + new Date(urlConnection.getIfModifiedSince()));
                urlConnection.setIfModifiedSince((new Date(today.getTime() - millisecondsPerDay)).getTime());
                System.out.println("Will retrieve file if it's modified since" + new Date(urlConnection.getIfModifiedSince()));
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                Reader r = new InputStreamReader(in);
                int c;
                while ((c = r.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

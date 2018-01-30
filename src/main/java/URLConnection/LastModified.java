package URLConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * 7-15 获取最后修改时间
 * Created by lasia on 2018/1/27.
 */
public class LastModified {
    public static void main(String args[]) {
        for (int i = 0; i < args.length; i++) {
            try {
                URL url = new URL(args[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("HEAD");   //important
                System.out.println(url + " was modified at " + new Date(httpURLConnection.getLastModified()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

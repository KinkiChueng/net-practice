package URLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * 参数：http://www.badiu.com
 * 结果：Content-tyep:text/html
 *       Date: Sun Jan 21 20:55:34 CST 2018
 * 返回文档最后修改日期，过期日期，当前日期
 * Created by lasia on 2018/1/9.
 */
public class HeaderViewer {
    public static void main(String args[]) {
        for (int i = 0; i < args.length; i++) {
            try {
                URL url = new URL(args[i]);
                URLConnection urlConnection = url.openConnection();
                System.out.println("Content-tyep:" + urlConnection.getContentType());
                if (urlConnection.getContentEncoding() != null) {
                    System.out.println("Content-encoding:" + urlConnection.getContentEncoding());
                }
                if (urlConnection.getDate() != 0) {
                    System.out.println("Date: " + new Date(urlConnection.getDate()));
                }
                if (urlConnection.getLastModified() != 0) {
                    System.out.println("last modified: " + new Date(urlConnection.getLastModified()));
                }
                if (urlConnection.getExpiration() != 0) {
                    System.out.println("Expiration Date: " + new Date(urlConnection.getExpiration()));
                }
                if (urlConnection.getContentLength() != -1) {
                    System.out.println("Content-length: " + urlConnection.getContentLength());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

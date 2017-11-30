package URIURL;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**p130
 * Created by lasia on 2017/11/30.
 */
public class URLGetData {
    public static void main(String args[]) {
        InputStream inputStream = null;
        try {
            URL url = new URL("https://www.baidu.com");
            inputStream = url.openStream();
            int c;
            while ((c = inputStream.read()) != -1) System.out.println(c);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * jdk7 写法
         */
        try {
            URL url = new URL("https://www.baidu.com");
            try (InputStream inputStream1 = url.openStream()) {
                int c;
                while ((c = inputStream1.read()) != -1) System.out.println(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

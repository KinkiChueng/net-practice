package URIURL;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**5-2
 * Created by lasia on 2017/11/30.
 */
public class SourceViewer {
    public static void main(String args[]) {
        InputStream inputStream = null;
        try {
            URL url = new URL("https://www.baidu.com");
            inputStream = url.openStream();
            //缓冲输入提高性能
            inputStream = new BufferedInputStream(inputStream);
            Reader reader = new InputStreamReader(inputStream);
            int c;
            while ((c = reader.read()) != -1) System.out.print((char) c);
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
    }
}

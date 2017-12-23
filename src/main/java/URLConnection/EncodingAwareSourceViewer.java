package URLConnection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lasia on 2017/12/23.
 */
public class EncodingAwareSourceViewer {
    public static void main(String args[]) {
        for (int i = 0; i < args.length; i++) {
            try {
                //设置默认编码方式
                String encoding = "ISO-8859-1";
                URL url = new URL(args[i]);
                URLConnection urlConnection = url.openConnection();
                String contentType = urlConnection.getContentType();
                int encodingStart = contentType.indexOf("charset=");
                if (encodingStart != -1) {
                    encoding = contentType.substring(encodingStart + 8);
                }
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                Reader reader = new InputStreamReader(inputStream,encoding);
                int c;
                while ((c=reader.read()) != -1) {
                    System.out.println(c);
                }
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

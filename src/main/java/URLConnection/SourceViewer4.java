package URLConnection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 7-17 直接下载整个页面
 * 和16一样嘛····
 * Created by lasia on 2018/1/27.
 */
public class SourceViewer4 {
    private static void printFromStream(InputStream raw) {
        try (InputStream buffer = new BufferedInputStream(raw)) {
            Reader reader = new InputStreamReader(buffer);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        try {
            URL url = new URL(args[0]);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            try (InputStream raw = urlConnection.getInputStream()) {
                printFromStream(raw);
            } catch (IOException e) {
                printFromStream(urlConnection.getErrorStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.err.println(args[0] + " is not a parseable URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

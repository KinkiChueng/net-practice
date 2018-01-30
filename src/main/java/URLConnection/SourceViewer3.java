package URLConnection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 7-16 获取响应码 消息 还有资源
 * Created by lasia on 2018/1/27.
 */
public class SourceViewer3 {
    public static void main(String args[]) {
        for (int i = 0; i < args.length; i++) {
            try {
                URL url = new URL(args[i]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                int code = urlConnection.getResponseCode();
                String response = urlConnection.getResponseMessage();
                System.out.println("HTTP/1.x " + code + " " + response);
                for (int j = 1; ; j++) {
                    String header = urlConnection.getHeaderField(j);
                    String key = urlConnection.getHeaderFieldKey(j);
                    if (header == null || key == null) break;
                    System.out.print(urlConnection.getHeaderFieldKey(j) + ": " + header);
                }
                System.out.println();

                try (InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream())) {
                    //将inputstream串联到一个reader
                    Reader reader = new InputStreamReader(inputStream);
                    int c;
                    while ((c = reader.read()) != -1) {
                        System.out.print((char) c);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

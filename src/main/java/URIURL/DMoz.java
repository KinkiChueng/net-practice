package URIURL;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lasia on 2017/12/5.
 */
public class DMoz {
    public static void main(String args[]) {
        String target = "";
        for (int i = 0; i < args.length; i++) {
            target += args[i] + " ";
        }
        target.trim();

        QueryString queryString = new QueryString();
        queryString.add("wd", target);
        try {
            URL url = new URL("http://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&ch=&tn=baiduerr&b?" + queryString);
            System.out.println(url.toString());
            try (InputStream inputStream = new BufferedInputStream(url.openStream())){
                InputStreamReader theHTML = new InputStreamReader(inputStream);
                int c;
                while((c = theHTML.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

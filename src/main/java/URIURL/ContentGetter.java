package URIURL;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**5-3
 * Created by lasia on 2017/11/30.
 */
public class ContentGetter {
    public static void main(String args[]) {
        try {
            URL url = new URL("https://www.oreilly.com");
            Object o = url.getContent();

            System.out.println(o.getClass().getName());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

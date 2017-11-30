package URIURL;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lasia on 2017/11/29.
 */
public class URLTest {
    public static void main(String args[]) throws MalformedURLException {
        URL u1 = new URL("https://ihainan.gitbooks.io/spark-source-code/content/index.html");
        URL u2 = new URL(u1,"search.html");
        System.out.println(u2);
    }
}

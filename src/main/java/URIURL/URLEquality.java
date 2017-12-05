package URIURL;

import java.net.MalformedURLException;
import java.net.URL;

/**5-5
 * Created by lasia on 2017/11/30.
 */
public class URLEquality {
    public static void main(String args[]) {
        try {
            URL url = new URL("http://www.ibiblio.org/");
            URL ibiblio = new URL("http://ibiblio.org/");
            if (ibiblio.equals(url)) {
                System.out.println(url + " is the same as " + ibiblio);
            } else {
                System.out.println(url + " is not the same as " + ibiblio);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}

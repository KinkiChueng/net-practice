package URIURL;

import java.io.*;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

/**下载由口令保护的web页面程序
 * Created by lasia on 2017/12/5.
 */
public class SecureSourceViewer {
    public static void main(String args[]) {
        Authenticator.setDefault(new DialogAuthenticator());
        for (int i = 0; i < args.length; i++) {
            try {
                URL url = new URL(args[i]);
                try (InputStream in = new BufferedInputStream(url.openStream())) {
                    //将inputstream串联到一个reader
                    Reader reader = new InputStreamReader(in);
                    int c;
                    while ((c = reader.read()) != -1) {
                        System.out.println((char) c);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println(args[0] + " is not a parseable url");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println(e);
            }
        }
    }
}

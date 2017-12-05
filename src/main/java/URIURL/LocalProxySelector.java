package URIURL;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**5-9 代理服务器记录可以连接的URL
 * Created by lasia on 2017/12/4.
 */
public class LocalProxySelector extends ProxySelector {
    private List<URI> failed = new ArrayList<URI>();
    public List<Proxy> select(URI uri) {
        List<Proxy> result = new ArrayList<Proxy>();
        if (failed.contains(uri) || "http".equalsIgnoreCase(uri.getScheme())) {
            result.add(Proxy.NO_PROXY);
        } else {
            SocketAddress proxyAddress = new InetSocketAddress("proxy.example.com", 8000);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
            result.add(proxy);
        }
        return result;
    }

    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        failed.add(uri);
    }
}
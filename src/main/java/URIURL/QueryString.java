package URIURL;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lasia on 2017/12/4.
 */
public class QueryString {
    private StringBuilder query = new StringBuilder();

    public QueryString() {
    }

    public synchronized void add(String name, String value) {
        query.append('&');
        encode(name, value);
    }

    public synchronized void encode(String name, String value) {
        try {
            query.append(URLEncoder.encode(name,"UTF-8"));
            query.append('=');
            query.append(URLEncoder.encode(value,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getQuery();
    }

    public synchronized String getQuery() {
        return query.toString();
    }

    public static void main(String args[]) {
        QueryString queryString = new QueryString();
        queryString.add("h1", "en");
        queryString.add("as_q","Java");
        queryString.add("as_epq","I/O");
        String url = "http://www.baidu.com/search?" + queryString;
        System.out.println(url);
    }
}

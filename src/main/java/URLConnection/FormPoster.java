package URLConnection;

import URIURL.QueryString;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 7-14 提交一个表单
 * Created by lasia on 2018/1/27.
 */
public class FormPoster {
    private URL url;
    //取自第五章，5-8
    private QueryString queryString = new QueryString();

    public FormPoster(URL url) {
        if (!url.getProtocol().toLowerCase().startsWith("http")) {
            throw new IllegalArgumentException("Posting only works for http URLs");
        }
        this.url = url;
    }

    public void add(String name, String value) {
        queryString.add(name, value);
    }

    public InputStream post() throws IOException {
        //打开连接准备post
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);//允许连接输出
        try (OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream(),"UTF-8")) {
            //post行，content-type首部和content-length首部
            //由URLConnection发送
            //我们只需要发送数据
            out.write(queryString.toString());
            out.write("\r\n");
            out.flush();
        }
        //返回响应
        return urlConnection.getInputStream();
    }

    public static void main(String args[]) {
        URL url;
        if (args.length > 0) {
            try {
                url = new URL(args[0]);
            } catch (MalformedURLException e) {
                System.err.println("Usage: java FormPoster url");
                return;
            }
        } else {
            try {
                url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
            } catch (MalformedURLException e) {
                System.err.println(e);
                return;
            }
        }

        FormPoster poster = new FormPoster(url);
        poster.add("name", "lasia,Elliotte Rusty Harold");
        poster.add("email","elharo@ibiblio.org");

        try (InputStream inputStream = poster.post()) {
            //读取响应
            Reader reader = new InputStreamReader(inputStream);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package URLConnection;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**下载二进制文件并保存磁盘
 * Created by Administrator on 2017/12/25.
 */
public class BinarySaver {
    public static void main(String[] args) {
        for (int i = 0;i<args.length;i++) {
            try {
                URL root = new URL(args[i]);
                saveBinary(root);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveBinary(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        String contentType = urlConnection.getContentType();
        int contentLength = urlConnection.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("this is not a binary file");
        }
        try (InputStream raw = urlConnection.getInputStream()) {
            InputStream inputStream = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset = 0;
            while (offset < contentLength) {
                int byteRead = inputStream.read(data, offset, data.length - offset);
                if (byteRead == -1) break;
                offset += byteRead;
            }
            if (offset != contentLength) {
                throw new IOException("only read " + offset + "bytes;Expected " + contentLength + "bytes");
            }
            String filename = url.getFile();
            filename = filename.substring(filename.lastIndexOf('/') + 1);
            try (FileOutputStream fout = new FileOutputStream(filename)){
                fout.write(data);
                fout.flush();
            }
        }
    }
}


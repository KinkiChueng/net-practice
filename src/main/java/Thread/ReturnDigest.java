package Thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 3-3 这个例子线程会在计算完成前结束，所以会报空指针异常
 * Created by lasia on 2017/11/24.
 */
public class ReturnDigest extends Thread {
    private String filename;
    private byte[] digest;

    public ReturnDigest(String filename) {
        this.filename = filename;
    }

    public byte[] getDigest() {
        return digest;
    }

    @Override
    public void run() {
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream digestInputStream = new DigestInputStream(inputStream,sha);
            while (digestInputStream.read() != -1) ;
            digestInputStream.close();
            digest = sha.digest();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

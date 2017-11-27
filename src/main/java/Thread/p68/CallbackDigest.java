package Thread.p68;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 3-5
 * Created by lasia on 2017/11/27.
 */
public class CallbackDigest implements Runnable {
    private String filename;

    public CallbackDigest(String filename) {
        this.filename = filename;
    }

    public void run() {

        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            DigestInputStream digestInputStream = new DigestInputStream(in, messageDigest);
            digestInputStream.getMessageDigest();
            while (digestInputStream.read() != -1) ;
            digestInputStream.close();
            byte[] digest = messageDigest.digest();
            //基于结果调用主程序中的一个已知方法，而不是由主程序询问每个线程来寻求答案
            CallbackDigestUserInterface.receiveDigest(digest, filename);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

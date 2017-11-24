package Thread;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * java网络编程 3-2
 * Created by lasia on 2017/11/23.
 */
public class DigestRunnable implements Runnable {
    private String filename;
    public DigestRunnable(String filename) {
        this.filename = filename;
    }

    public void run() {
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, sha);
            while (digestInputStream.read() != -1);
            digestInputStream.close();
            byte[] digect = sha.digest();

            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            result.append(DatatypeConverter.printHexBinary(digect));
            System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        for (String filename: args) {
            Runnable thread = new DigestRunnable(filename);
            thread.run();
        }
    }
}

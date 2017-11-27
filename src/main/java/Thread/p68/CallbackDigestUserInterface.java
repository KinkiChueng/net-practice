package Thread.p68;

import javax.xml.bind.DatatypeConverter;

/**
 * 3-6
 * Created by lasia on 2017/11/27.
 */
public class CallbackDigestUserInterface {
    public static void receiveDigest(byte[] digest, String name) {
        StringBuilder result = new StringBuilder(name);
        result.append(":");
        result.append(DatatypeConverter.printHexBinary(digest));
        System.out.println(result);
    }

    public static void main(String args[]) {
        for (String filename:args
             ) {
            CallbackDigest callbackDigest = new CallbackDigest(filename);
            Thread thread = new Thread(callbackDigest);
            thread.start();
        }
    }
}

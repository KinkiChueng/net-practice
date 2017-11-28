package Thread.Callback;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by lasia on 2017/11/27.
 */
public class InstanceCallbackDigestUserInterface {
    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }

    public void calculateDigest() {
        InstanceCallbackDigest callbackDigest = new InstanceCallbackDigest(filename, this);
        Thread thread = new Thread(callbackDigest);
        thread.start();
    }

    void receiveDigest(byte[] digest) {
        this.digest = digest;
        System.out.println(this);
    }

    @Override
    public String toString() {
        String result = filename + ": ";
        if (digest != null) {
            result += DatatypeConverter.printHexBinary(digest);
        } else {
            result += "digest not available";
        }
        return result;
    }

    public static void main(String args[]) {
        for (String filename:args
             ) {
            InstanceCallbackDigestUserInterface digestUserInterface = new InstanceCallbackDigestUserInterface(filename);
            digestUserInterface.calculateDigest();
        }
    }
}

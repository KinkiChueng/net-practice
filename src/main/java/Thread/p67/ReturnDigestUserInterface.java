package Thread.p67;

import javax.xml.bind.DatatypeConverter;

/**
 * 3-4 dr.start 启动的计算可能在main调用dr.getdigest之前结束，也可能还没有结束
 * 如果还没结束，会报空指针异常
 * Created by lasia on 2017/11/24.
 */
public class ReturnDigestUserInterface {
    public static void main(String args[]) {
        for (String filename:
             args) {
            ReturnDigest returnDigest = new ReturnDigest(filename);
            returnDigest.start();
            StringBuilder result = new StringBuilder(filename);
            result.append(":");
            byte[] digest = returnDigest.getDigest();
            result.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(result);
        }
    }
}

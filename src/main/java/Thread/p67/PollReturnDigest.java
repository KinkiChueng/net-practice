package Thread.p67;

import javax.xml.bind.DatatypeConverter;

/**
 * p67 轮询 让获取方法返回一个标志值（或者可能抛出一个异常），直到设置了结果字段为止
 * 主线程占用所有可用时间，工作进程没有时间完成任务
 * Created by lasia on 2017/11/24.
 */
public class PollReturnDigest {
    public static void main(String args[]) {
        ReturnDigest[] digests = new ReturnDigest[args.length];
        for (int i = 0; i < args.length; i++) {
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }

        for (int i = 0; i < args.length; i ++) {
            while (true) {
                byte[] digest = digests[i].getDigest();
                if (digest != null) {
                    StringBuilder result = new StringBuilder(args[i]);
                    result.append(":");
                    result.append(DatatypeConverter.printHexBinary(digest));
                    System.out.println(result);
                    break;
                }
            }
        }
    }
}

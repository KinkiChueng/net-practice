package socket.eight_ServerSocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * 8-3 时间协议客户端
 * Created by lasia on 2018/2/4.
 */
public class Time {
    private static final String HOSTNAME = "time.nist.gov";

    public static void main(String args[]) {
        Date d = Time.getDateFromNetwork();
        System.out.println("It is " + d);
    }

    public static Date getDateFromNetwork() {
        //协议设置时间起点是1900年，
        //Java Date类起始于1970年。利用这个数字在它们间进行转换
        long differenceBetweenEpochs = 22089888000L;

        //如果不愿使用这个麻烦数字，就取消以下代码的注释，这段代码负责自动计算
//        TimeZone gmt = TimeZone.getTimeZone("GMT");
//        Calendar epoch1900 = Calendar.getInstance(gmt);
//        epoch1900.set(1900, 01, 01, 00, 00, 00);
//        long epoch1900ms = epoch1900.getTime().getTime();
//        Calendar epoch1970 = Calendar.getInstance(gmt);
//        epoch1900.set(1970, 01, 01, 00, 00, 00);
//        long epoch1970ms = epoch1970.getTime().getTime();
//
//        long differenceInMS = epoch1970ms - epoch1900ms;
//        long differenceBetweenEpochs = differenceInMS/1000;

        try (Socket socket = new Socket(HOSTNAME, 37)) {
            socket.setSoTimeout(15000);

            InputStream raw = socket.getInputStream();
            long secondsSince1900 = 0;
            for (int i = 0; i < 4; i++) {
                secondsSince1900 = (secondsSince1900 << 8) | raw.read();
            }

            long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
            long msSince1970 = secondsSince1970 * 1000;
            Date time = new Date(msSince1970);
            return time;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 8-2 通过对话构建date
 * Created by lasia on 2018/2/4.
 */
public class DayTime {
    public Date getDateFromNetwork() {
        try (Socket socket = new Socket("time.nist.gov", 13)) {
            socket.setSoTimeout(15000);
            InputStream inputStream = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(inputStream,"ASCII");
            for (int c = reader.read(); c != -1; c =reader.read()) {
                time.append((char)c);
            }
            return parseDate(time.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Date parseDate(String s) throws ParseException {
        String[] pieces = s.split(" ");
        String dateTime = pieces[1] + " " + pieces[2] + " UTC";
        DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
        return format.parse(dateTime);
    }

    public static void main(String args[]) {
     System.out.println(new DayTime().getDateFromNetwork());
    }
}

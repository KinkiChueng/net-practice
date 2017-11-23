package reflect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lasia on 2017/11/17.
 */
public class TestReflection2 {
    public static void main(String args[]) {
        try {
            Test obj = Test.class.newInstance();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            Method m = Test.class.getDeclaredMethod(s, int.class, int.class);
            Integer a = Integer.valueOf(br.readLine());
            Integer b = Integer.valueOf(br.readLine());
            System.out.println("Test." + m.getName() + " has been called with " + a + ", " + b);
            System.out.println("!!!!!!!" + m.invoke(obj,a,b));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

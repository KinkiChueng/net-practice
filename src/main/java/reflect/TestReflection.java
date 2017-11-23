package reflect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射练习
 * Created by lasia on 2017/11/17.
 */
public class TestReflection {
    public static void main(String args[]) {
        try {
            Test obj = Test.class.newInstance();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferedReader.readLine();

            Method m;
            if (s.equals("sayh")) {
                m = Test.class.getDeclaredMethod("sayHello");
            } else {
                m = Test.class.getDeclaredMethod("sayWorld");
            }
            m.invoke(obj);
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

class Test {

    public  void sort() {
        int[] a = {4,7,1,3,4,6,2};
        System.out.println(a.length);
    }
    public void add(int a, int b) {
        System.out.println(a + b);
    }
    public void sayHello() {
        System.out.println("hello");
    }
    public void sayWorld() {
        System.out.println("world");
    }
}
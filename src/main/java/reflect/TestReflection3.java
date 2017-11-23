package reflect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lasia on 2017/11/17.
 */
public class TestReflection3 {
    public static void main(String args[]) {
        try {
            Test1 obj;
            obj = Test1.class.newInstance();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String fieldName = br.readLine();

            Field field = Test1.class.getDeclaredField(fieldName);
            field.set(obj, Class.forName("reflect."+br.readLine()).newInstance());

            Method m = Test1.class.getDeclaredMethod(br.readLine());
            m.invoke(obj);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

class Test1 {
    public Sorter sorter;

    public void sort() {
        System.out.println("asdfghjkl;'");
        int[] a = {5, 7, 3, 1, 4, 6, 2};
        sorter.sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}

class Sorter {
    public void sort(int[] a) {
        for (int i : a) {
            System.out.println(i);
        }
    }
}
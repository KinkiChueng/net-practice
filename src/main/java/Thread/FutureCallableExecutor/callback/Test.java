package Thread.FutureCallableExecutor.callback;

/**
 * Created by lasia on 2017/11/28.
 */
public class Test {
    public static void main(String args[]) {
        Li li = new Li();
        Wang wang = new Wang(li);
        wang.askQuestion("1 + 1 = ?");
    }
}

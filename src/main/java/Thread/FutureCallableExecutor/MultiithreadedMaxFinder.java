package Thread.FutureCallableExecutor;

import java.util.concurrent.*;

/**
 * Created by lasia on 2017/11/27.
 */
public class MultiithreadedMaxFinder {
    public static int max(int[] data) throws IllegalAccessException, ExecutionException, InterruptedException {
        if (data.length == 1) {
            return data[0];
        } else if (data.length == 0) {
            throw new IllegalAccessException();
        }

        //将任务分解为两个部分
        FindMaxTask task1 = new FindMaxTask(data,0,data.length/2);
        FindMaxTask task2 = new FindMaxTask(data, data.length/2, data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = (Future<Integer>) service.submit((Runnable) task1);
        Future<Integer> future2 = (Future<Integer>) service.submit((Runnable) task2);

        return Math.max(future1.get(), future2.get());
    }
}

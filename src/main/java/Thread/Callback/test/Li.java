package Thread.Callback.test;

/**
 * Created by lasia on 2017/11/28.
 */
public class Li {
    public void executeMessage(CallBack callBack, String question) {
        System.out.println("小王的问题：" + question);

        for (int i=0; i<10000; i++) {
            //模拟办事需要很长时间
        }

        String result = "答案是2";

        /*
        打电话告诉小王，调用小王中的方法
        相当于B类反过来调用A的方法D
         */
        callBack.solve(result);
    }
}

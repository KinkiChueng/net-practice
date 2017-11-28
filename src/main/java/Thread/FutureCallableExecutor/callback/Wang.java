package Thread.FutureCallableExecutor.callback;

/**
 * Created by lasia on 2017/11/28.
 */
public class Wang implements CallBack {
    private Li li;

    public Wang(Li li) {
        this.li = li;
    }

    /**
     * 小王问小李问题
     * @param question
     */
    public void askQuestion(final String question) {
        new Thread(new Runnable() {
            public void run() {
                /**
                 * 小王调用小李中的方法，在这里注册回调接口
                 * 这就相当于A类调用B的方法C
                 */
                li.executeMessage((CallBack) Wang.this, question);
            }
        }).start();

        play();
    }

    public void play() {
        System.out.println("我要逛街去了");
    }


    public void solve(String result) {
        System.out.println("小李告诉小王的答案是--》" + result);
    }
}

package Thread.Callback.test;

/**
 * 回调接口练习
 * Class A实现接口CallBack callback——背景1
 * class A中包含一个class B的引用b ——背景2
 * class B有一个参数为callback的方法f(CallBack callback) ——背景3
 * A的对象a调用B的方法 f(CallBack callback) ——A类调用B类的某个方法 C
 * 然后b就可以在f(CallBack callback)方法中调用A的方法 ——B类调用A类的某个方法D
 * Created by lasia on 2017/11/28.
 */
public interface CallBack {
    public void solve(String result);
}

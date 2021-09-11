package com.atguigu.java;

/**
 * @author shkstart
 * @create 2019-02-15 上午 11:15
 */
/**
 *  * 内部类thread.state记录几种状态
 *    新建
 *    就绪  新建的线程start() 进入线程队列 等待 CPU时间片  具备了运行的条件 但是没有CPU资源
 *    运行  线程被调度 并且获得CPU资源
 *    阻塞  运行和死亡 之间的状态  意味着临时终止
 *    死亡
 *    新建  调用start()-->  就绪
 *    就绪  获得CPU资源 --> 运行 yield()/失去CPU资源  --> 就绪
 *    运行 调用方法sleep(time)/ A线程当中调用其他对象的join()方法 A会阻塞/ 同步锁/ wait() /suspend()  --> 阻塞
 *    阻塞 sleep()时间到/ join()结束/  获取到同步锁/  与wait()相反的 notify()/notifyAll() / 与suspend()相反的  resume() -->就绪
 *    运行   执行完run()/调用线程stop()/出现Error/Exception --> 死亡
 *
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 * 例子：创建三个窗口卖票，总票数为100张.使用继承Thread类的方式
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器。★★★★★
 *
 * @author shkstart
 * @create 2019-02-13 下午 4:20
 */
class Window2 extends Thread{


    private static int ticket = 100;

    private static Object obj = new Object();//通过静态 表示这个对象共享 不是实例变量

    @Override
    public void run() {

        while(true){
            //正确的
//            synchronized (obj){
            //Class clazz = Window2.class,   Window2.class只会加载一次
            synchronized (Window2.class){
                //错误的方式：this代表着t1,t2,t3三个对象 是因为 继承Thread  而不是实现Runnable()方法里面的三个线程
//              synchronized (this){

                if(ticket > 0){

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }

        }

    }
}


public class WindowTest2 {
    public static void main(String[] args) {
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}

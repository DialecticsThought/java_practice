package com.atguigu.java;

/**
 * 使用同步方法处理继承Thread类的方式中的线程安全问题
 * 内部类thread.state记录几种状态
 * 新建
 * 就绪  新建的线程start() 进入线程队列 等待 CPU时间片  具备了运行的条件 但是没有CPU资源
 * 运行  线程被调度 并且获得CPU资源
 * 阻塞  运行和死亡 之间的状态  意味着临时终止
 * 死亡
 * 新建  调用start()-->  就绪
 * 就绪  获得CPU资源 --> 运行 yield()/失去CPU资源  --> 就绪
 * 运行 调用方法sleep(time)/ A线程当中调用其他对象的join()方法 A会阻塞/ 同步锁/ wait() /suspend()  --> 阻塞
 * 阻塞 sleep()时间到/ join()结束/  获取到同步锁/  与wait()相反的 notify()/notifyAll() / 与suspend()相反的  resume() -->就绪
 * 运行   执行完run()/调用线程stop()/出现Error/Exception --> 死亡
 *
 *   关于同步方法的总结：
 *   1. 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
 *   2. 非静态的同步方法，同步监视器是：this
 *      静态的同步方法，同步监视器是：当前类本身
 * @author shkstart
 * @create 2019-02-15 上午 11:43
 */
class Window4 extends Thread {


    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {

            show();
        }

    }
    private static synchronized void show(){//同步监视器：Window4.class
        //private synchronized void show(){ //同步监视器：t1,t2,t3。此种解决方式是错误的
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
            ticket--;
        }
    }
}


public class WindowTest4 {
    public static void main(String[] args) {
        Window4 t1 = new Window4();
        Window4 t2 = new Window4();
        Window4 t3 = new Window4();


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
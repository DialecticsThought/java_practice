package atguigu.java;

/**
 *  线程是进程的一个执行过程
 *  一个进程里多个线程共享一个方法区和堆  每个线程有对应的虚拟机栈和程序计数器
 *  并行：多个cpu核心同时在做多个事情
 *  并发：一个cpu一段时间内同时执行多个任务
 *
 *  创建一个线程就要创建一个thread的子类对象，并且重写方法
 * 多线程的创建，方式一：继承于Thread类
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用start()  start()是父类的方法 不用子类对象重写
 * <p>
 * 例子：遍历100以内的所有的偶数
 *
 * @author shkstart
 * @create 2019-02-13 上午 11:46
 */

//1. 创建一个继承于Thread类的子类
class MyThread extends Thread {
    //2. 重写Thread类的run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}


public class ThreadTest {
    public static void main(String[] args) {
        //3. 创建Thread类的子类的对象
        MyThread t1 = new MyThread();

        //4.通过此对象调用start():①启动当前线程 ② 调用当前线程(MyThread对象)的run()
        t1.start();
        //问题一：我们不能通过直接调用run()的方式启动线程。
        //t1.run(); 并没有开辟新的进程 必须用start

        //问题二：再启动一个线程，遍历100以内的偶数。不可以还让已经start()的线程去执行。会报IllegalThreadStateException
        //t1.start(); 线程只能start一次 一个对象只对应一个线程
        //我们需要重新创建一个线程的对象
        MyThread t2 = new MyThread();
        t2.start();


        //如下操作仍然是在main线程中执行的。
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                //Thread.currentThread().getName() 获取当前线程的名字
                System.out.println(Thread.currentThread().getName() + ":" + i + "***********main()************");
            }
        }
    }

}

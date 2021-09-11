package com.atguigu.java;

/**
 * * 内部类thread.state记录几种状态
 *  新建
 *  就绪  新建的线程start() 进入线程队列 等待 CPU时间片  具备了运行的条件 但是没有CPU资源
 *  运行  线程被调度 并且获得CPU资源
 *  阻塞  运行和死亡 之间的状态  意味着临时终止
 *  死亡
 *  新建  调用start()-->  就绪
 *  就绪  获得CPU资源 --> 运行 yield()/失去CPU资源  --> 就绪
 *  运行 调用方法sleep(time)/ A线程当中调用其他对象的join()方法 A会阻塞/ 同步锁/ wait() /suspend()  --> 阻塞
 *  阻塞 sleep()时间到/ join()结束/  获取到同步锁/  与wait()相反的 notify()/notifyAll() / 与suspend()相反的  resume() -->就绪
 *  运行   执行完run()/调用线程stop()/出现Error/Exception --> 死亡
 * 例子：创建三个窗口卖票，总票数为100张.使用实现Runnable接口的方式
 *
 * 1.问题：卖票过程中，出现了重票、错票 -->出现了线程的安全问题
 *
 * 2.问题出现的原因：当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票。
 * 某个线程执行run()方法时候，其他线程也进入了
 *
 * 3.如何解决：当一个线程a在操作ticket的时候，其他线程不能参与进来。直到线程a操作完ticket时，其他
 *            线程才可以开始操作ticket。这种情况即使线程a出现了阻塞，也不能被改变。
 *
 *
 * 4.在Java中，我们通过同步机制，来解决线程的安全问题。
 *
 *  方式一：同步代码块
 *
 *   synchronized(同步监视器){
 *      //需要被同步的代码
 *
 *   }
 *
 *    synchronized的锁是什么？  任意对象都可以作为同步锁。所有对象都自动含有单一的锁（监视器）。
 *    同步方法的锁：静态方法（类名.class）、非静态方法（this）
 *    同步代码块：自己指定，很多时候也是指定为this或类名.class
 *
 *
 *  说明：1.操作共享数据的代码，即为需要被同步的代码。  -->不能包含代码多了，也不能包含代码少了。
 *       2.共享数据：多个线程共同操作的变量。比如：ticket就是共享数据。
 *       3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *          要求：多个线程必须要共用同一把锁。
 *
 *       补充：在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
 *  方式二：同步方法。
 *     如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的。
 *
 *
 *  5.同步的方式，解决了线程的安全问题。---好处
 *    操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。 ---局限性
 *
 *                      同步的范围
 * 1、如何找问题，即代码是否存在线程安全？（非常重要） （1）明确哪些代码是多线程运行的代码
 * （2）明确多个线程是否有共享数据
 * （3）明确多线程运行代码中是否有多条语句操作共享数据
 *
 *
 * 2、如何解决呢？（非常重要）
 * 对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其
 * 他线程不可以参与执行。
 * 即所有操作共享数据的这些语句都要放在同步范围中
 *
 *
 * 3、切记：
 *  范围太小：没锁住所有有安全问题的代码
 *  范围太大：没发挥多线程的功能。
 *
 *
 * @author shkstart
 * @create 2019-02-13 下午 4:47
 */
class Window1 implements Runnable{

    private int ticket = 100;
//    Object obj = new Object();
//    Dog dog = new Dog();
    @Override
    public void run() {
//        Object obj = new Object(); 如果用obj的话每个线程各有一把琐 所以是错的
        while(true){
            //同步监视器，俗称：锁。任何一个类的对象，都可以充当锁
            synchronized (this){//此时的this:唯一的Window1的对象  //方式二：synchronized (dog) {

                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);


                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}


public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }

}


class Dog{

}
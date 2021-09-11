package com.atguigu.java1;

/**
 * 演示线程的死锁问题
 *
 * 1.死锁的理解：不同的线程分别占用对方需要的同步资源不放弃，
 * 都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 * eg: 两个人吃饭 只有一双筷子 每个人都拿了一根 双方都等待对方把他的那个根交出来
 *
 * 2.说明：
 * 1）出现死锁后，不会出现异常，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 * 2）我们使用同步时，要避免出现死锁。
 *
 * @author shkstart
 * @create 2019-02-15 下午 3:20
 */
public class ThreadTest {

    public static void main(String[] args) {

        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        //匿名线程
        new Thread(){
            @Override
            public void run() {

                synchronized (s1){
                    //某个线程有了锁s1的时候执行下列操作
                    s1.append("a");
                    s2.append("1");

                    try {
                        //某个线程阻塞了0.1秒，这个0.1秒中 下面定义的线程也可能持有锁s2执行下面定义的操作
                        //这个线程那着s1 等着 s2 而下面的线程是拿着s2 等着s1
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //某个线程有了锁s2的时候执行下列操作
                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }

            }
        }.start();

        //new Thread创建一个内部类  形参是个匿名内部类的对象
        //new Runnable()表示创建一个匿名的 实现Runnable接口的对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2){

                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1){
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }

            }
        }).start();


    }


}

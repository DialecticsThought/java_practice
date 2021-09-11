package com.atguigu.java1;

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 *
 * @author shkstart
 * @create 2019-02-15 下午 2:50
 */
public class BankTest {

}

class Bank{
    //私有化构造器
    private Bank(){}
    //私有的静态的Bank类型的对象 要共享
    private static Bank instance = null;
    //run()方法中调用了 getInstance()
    //首次调用了getInstance()时候 instance==null
    //static 同步方法的锁是当前类本身 Bank.class
    public static  Bank getInstance(){
        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if(instance == null){
//
//                instance = new Bank();
//            }
//            return instance;
//        }
        //方式二：效率更高
        if(instance == null){

            synchronized (Bank.class) {
                //操作共享数据
                if(instance == null){
                    instance = new Bank();
                }

            }
        }
        return instance;
    }

}

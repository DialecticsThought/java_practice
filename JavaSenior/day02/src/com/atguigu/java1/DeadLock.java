package com.atguigu.java1;
//死锁的演示
class A {
	public synchronized void foo(B b) { //同步监视器：A类的对象：a 同步方法的锁是this 也就是当前类的对象
		System.out.println("当前线程名: " + Thread.currentThread().getName()
				+ " 进入了A实例的foo方法"); // ①
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
		System.out.println("当前线程名: " + Thread.currentThread().getName()
				+ " 企图调用B实例的last方法"); // ③
		b.last();//b作为形参传下来 但是b.last执行也要握住另一把锁
	}

	public synchronized void last() {//同步监视器：A类的对象：a
		System.out.println("进入了A类的last方法内部");
	}
}

class B {
	public synchronized void bar(A a) {//同步监视器：b 同步方法的锁是this 也就是当前类的对象
		System.out.println("当前线程名: " + Thread.currentThread().getName()
				+ " 进入了B实例的bar方法"); // ②
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
		System.out.println("当前线程名: " + Thread.currentThread().getName()
				+ " 企图调用A实例的last方法"); // ④
		a.last();
	}

	public synchronized void last() {//同步监视器：b
		System.out.println("进入了B类的last方法内部");
	}
}

public class DeadLock implements Runnable {
	A a = new A();
	B b = new B();

	public void init() {
		Thread.currentThread().setName("主线程");
		// 调用a对象的foo方法
		a.foo(b);
		System.out.println("进入了主线程之后");
	}

	public void run() {
		Thread.currentThread().setName("副线程");
		// 调用b对象的bar方法
		b.bar(a);
		System.out.println("进入了副线程之后");
	}

	public static void main(String[] args) {
		//主线程先拿a锁 再拿b锁  分线程先拿b锁 再拿a锁
		DeadLock dl = new DeadLock();
		new Thread(dl).start();//执行DeadLock类dl对象的run()方法 也就是分线程 因为主线程main
		//主线程main
		dl.init();
	}
}

package com.atguigu.java2;
/*
 * 单例模式的懒汉式实现
 * 
 */
public class SingletonTest2 {
	public static void main(String[] args) {
		//通过(类.静态方法)调用
		Order order1 = Order.getInstance();
		Order order2 = Order.getInstance();
		
		System.out.println(order1 == order2);
		
	}
}


class Order{
	
	//1.私有化类的构造器
	private Order(){
		
	}
	
	//2.声明当前类对象，没有初始化★★★★★
	//4.此对象也必须声明为static的
	private static Order instance = null;
	
	//3.声明public、static的返回当前类对象的方法
	public static Order getInstance(){
		//防止每次调用该静态方法的时候创建一个对象,所以要判断语句
		if(instance == null){
			
			instance = new Order();
			
		}
		return instance;
	}
	
}
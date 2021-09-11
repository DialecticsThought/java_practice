package com.atguigu.java;
/*
 * 抽象类的匿名子类
 * 匿名的都只用一次
 * 
 */
public class PersonTest {
	
	public static void main(String[] args) {
		
		method(new Student());//匿名对象
		
		Worker worker = new Worker();
		method1(worker);//非匿名的类非匿名的对象
		
		method1(new Worker());//非匿名的类匿名的对象 因为类的名字是Worker
		
		System.out.println("********************");
		
		//创建了一匿名子类的对象：p  person是不能new的因为是抽象类，对象名是p 但是谁继承了这个抽象类是匿名的
		Person p = new Person(){
	//匿名类实现了抽象方法，匿名类之中一次
			@Override
			public void eat() {
				System.out.println("吃东西");
			}

			@Override
			public void breath() {
				System.out.println("好好呼吸");
			}
			
		};
		
		method1(p);
		System.out.println("********************");
		//创建匿名子类的匿名对象
		method1(new Person(){
			@Override
			public void eat() {
				System.out.println("吃好吃东西");
			}

			@Override
			public void breath() {
				System.out.println("好好呼吸新鲜空气");
			}
		});
	}
	
	
	public static void method1(Person p){
		p.eat();
		p.breath();
	}
	
	public static void method(Student s){
		
	}
}

class Worker extends Person{

	@Override
	public void eat() {
	}

	@Override
	public void breath() {
	}
	
}

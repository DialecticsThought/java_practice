package com.atguigu.java;
/*
 * abstract关键字的使用 抽象类是用来模型化那些无法确定具体实现，而其子类能提供具体实现的对象的类，只是声明一个抽象方法，让其子类实现
 * 1.abstract:抽象的
 * 2.abstract可以用来修饰的结构：类、方法
 * 
 * 3. abstract修饰类：抽象类
 * 		> 此类不能实例化
 *      > 抽象类中一定有构造器，便于子类实例化时调用（涉及：子类对象实例化的全过程）
 *      > 开发中，都会提供抽象类的子类，让子类对象实例化，完成相关的操作
 * ★★★★★一定要让抽象类有子类去继承他，如果不继承，自身也不能new，就没有用了
 * 
 * 
 * 4. abstract修饰方法：抽象方法
 * 		> 抽象方法只有方法的声明，没有方法体，就是没有大括号，有大括号就不能是抽象方法
 * 			如果连方法体也没有的话，就不能被调用，为了不去调用抽象类，
 * 又因为只有对象才能调用方法，并且抽象类不能造对象，所以必须是抽象类
 * 		> 包含抽象方法的类，一定是一个抽象类。反之，抽象类中可以没有抽象方法的。
 *      > 若子类重写了父类中的所有的抽象方法后，此子类才可以实例化
 *        若子类没有重写父类中的所有的抽象方法，则此子类也是一个抽象类，需要使用abstract修饰
 *  ★★★★★子类必须实现抽象父类的方法
 */
public class AbstractTest {
	public static void main(String[] args) {
		
		//一旦Person类抽象了，就不可实例化
//		Person p1 = new Person();
//		p1.eat();
		
	}
}

abstract class Creature{
	public abstract void breath();
}

abstract class Person extends Creature{
	String name;
	int age;
	
	public Person(){
		
	}
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	
	//不是抽象方法：
//	public void eat(){
//		
//	}
	//抽象方法
	public abstract void eat();
	
	public void walk(){
		System.out.println("人走路");
	}
	
	
}


class Student extends Person{
	
	public Student(String name,int age){
		super(name,age);
	}
	public Student(){
	}
	
	public void eat(){
		System.out.println("学生多吃有营养的食物");
	}

	@Override
	public void breath() {
		System.out.println("学生应该呼吸新鲜的没有雾霾的空气");
	}
}
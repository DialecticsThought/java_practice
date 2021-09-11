package com.atguigu.java3;

public class Student extends Person{
	
	String major;
	int id = 1002;//学号
	
	public Student(){
		super();
	}
	public Student(String major){
		super();
		this.major = major;
	}
	
	public Student(String name,int age,String major){
//		this.name = name;因为父类的name和age权限修饰符不是private，所以可以用
//		this.age = age;
		super(name,age);//调用父类的Person(String name,int age)
		this.major = major;
	}
	
	@Override
	public void eat() {
		System.out.println("学生：多吃有营养的食物");
	}
	
	public void study(){
		System.out.println("学生：学习知识");
		this.eat();
		super.eat();
		walk();
	}
	
	public void show(){
		System.out.println("name = " + name + ", age = " + age);
		//person有个id student也有各自几个id并且继承了person的id this.id表示当前对象的id super.id表示其父类的id
		//属性不同于方法会复写，同命属性会一起出现在内存中
		System.out.println("id = " + this.id);
		System.out.println("id = " + super.id);
	}
}

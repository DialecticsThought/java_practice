package com.atguigu.java1;

public class Customer {
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Customer() {
		super();
	}
	public Customer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	//自动生成的equals()
	@Override
	public boolean equals(Object obj) {
		//先比较两个对象的地址，如果相同，则一定相同
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//getClass()方法是得到obj是哪个类创的
		if (getClass() != obj.getClass())
			return false;
		//如果是，谨慎起见，先强制转换
		Customer other = (Customer) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
		/*if(obj instanceof Customer){
			Customer customer=(Customer)obj;
			//像String、Date、File、包装类等都重写了Object类中的equals()方法。重写以后，比较的不是
			//比较当前对象和给入形参对象的属性值是否相同，因为name是String类型，所以不能用
			//==符号，应为会比较引用地址而不是属性值，所以要用String类型的equals方法比较内容
			if(this.age==customer.age&&this.name.equals(customer.name)){
				return  true;
			}else {
				return false;
			}
		}
		return false;*/
	}
	
	
	
	//重写的原则：比较两个对象的实体内容(即：name和age)是否相同
	//手动实现equals()的重写
//	@Override
//	public boolean equals(Object obj) {
//		
////		System.out.println("Customer equals()....");
//		if (this == obj) {
//            return true;
//        }
//		
//		if(obj instanceof Customer){
//			Customer cust = (Customer)obj;
//			//比较两个对象的每个属性是否都相同
////			if(this.age == cust.age && this.name.equals(cust.name)){
////				return true;
////			}else{
////				return false;
////			}
//			
//			//或
//			return this.age == cust.age && this.name.equals(cust.name);
//		}else{
//			return false;
//			
//		}
//		
//	}
	//手动实现
//	@Override
//	public String toString() {
//		return "Customer[name = " + name + ",age = " + age + "]"; 
//	}
	//自动实现
	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + "]";
	}
}

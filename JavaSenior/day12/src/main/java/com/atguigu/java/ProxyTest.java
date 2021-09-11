package com.atguigu.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 动态代理的举例
 *
 * @author shkstart
 * @create 2019 上午 10:18
 */

interface Human{

    String getBelief();

    void eat(String food);

}
//被代理类
class SuperMan implements Human{


    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}
//
class HumanUtil{

    public void method1(){
        System.out.println("====================通用方法一====================");

    }

    public void method2(){
        System.out.println("====================通用方法二====================");
    }

}

/*
要想实现动态代理，需要解决的问题？
问题一：如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象。
问题二：当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。


 */

class ProxyFactory{
    //调用此方法，返回一个代理类的对象。解决问题一 如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象。
    public static Object getProxyInstance(Object obj){//obj:被代理类的对象 传入是用来告知创建哪一个类的被代理类
        //MyInvocationHandler就是Proxy.newProxyInstance方法的第三个形参
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);
        //第一个参数 被代理类的类加载器 用来创建相同加载器加载的代理类
        //传入被代理类所实现的接口数组
        //invocationHandler是用来解决第二个问题，是个接口，动态的去调用被代理类中的同名方法a
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }

}
//解决问题 当通过代理类的对象调用方法a时，如何动态的去调用被代理类中的同名方法a。
//MyInvocationHandler就是Proxy.newProxyInstance方法的第三个形参
class MyInvocationHandler implements InvocationHandler{
    //此obj是getProxyInstance传入的形参 也就是被代理对象

    private Object obj;//需要使用被代理类的对象进行赋值
    //相当于调用getProxyInstance的时候一起实例化 与getProxyInstance中的handler.bind(obj)相对应
    public void bind(Object obj){
        this.obj = obj;
    }//也可以 写成 有参构造
    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    //第一个是代理类的对象 第二个，被代理类的方法 method 的形参是不固定的 也就是说方法不断在变
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //将被代理类要执行的方法a的功能就声明在invoke()中，invoke方法里有被代理类的昂发
        HumanUtil util = new HumanUtil();
        util.method1();

        //method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj:被代理类的对象 args就是被代理类的方法所需要的参数
        //用returnValue来接收方法返回的值，等于被代理类对象执行该方法的时候返回的值
        //Object表多态
        Object returnValue = method.invoke(obj,args);

        util.method2();

        //上述方法的返回值returnValue就作为当前类中的invoke()的返回值。
        return returnValue;

    }
}
//根据运行时类 动态创建一个和你实现接口一样的类，代理那个被代理类去执行
public class ProxyTest {

    public static void main(String[] args) {
        //被代理的对象
        SuperMan superMan = new SuperMan();
        //proxyInstance:代理类的对象
        //代理类实现了被代理类的相同的接口 但代理类不是被代理类
        // 所以proxyInstance前面是Human而不是superMan
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        //invoke(Object proxy, Method method, Object[] args)
        // proxy对应proxyInstance metho对应eat args对应四川麻辣烫
        proxyInstance.eat("四川麻辣烫");

        System.out.println("*****************************");

        NikeClothFactory nikeClothFactory = new NikeClothFactory();

        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);

        proxyClothFactory.produceCloth();

    }
}

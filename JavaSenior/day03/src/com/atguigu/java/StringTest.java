package com.atguigu.java;

import org.junit.Test;

/**
 * String的使用
 *
 * @author shkstart
 * @create 2019 上午 10:26
 */
public class StringTest {



    /*
    结论：
    1.常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
    2.只要其中有一个是变量，结果就在堆中。
    3.如果拼接的结果调用intern()方法，返回值就在常量池中
     */
    @Test
    public void test4(){
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.println(s1 == s3);//false

        final String s4 = "javaEE";//s4:常量
        String s5 = s4 + "hadoop";//常量和常量的拼接在常量池
        System.out.println(s1 == s5);//true

    }

    @Test
    public void test3(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";//字面量
        String s4 = "javaEE" + "hadoop";//字面量的链接
        String s5 = s1 + "hadoop";//有变量名参与  就涉及堆空间 s5存的是堆的地址 堆地址对应的值指向常量池的地址
        String s6 = "javaEE" + s2;//有变量名参与  就涉及堆空间 s6存的是堆的地址 堆地址对应的值指向常量池的地址
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true 字面量与 字面量的链接相同
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false  堆地址不相同
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        String s8 = s6.intern();//返回值得到的s8使用的常量值中已经存在的“javaEEhadoop” 常量池的地址
        System.out.println(s3 == s8);//true 拼接的结果调用intern()方法，强制返回值就在常量池中 不在堆


    }

    /*
    String的实例化方式：
    方式一：通过字面量定义的方式
    方式二：通过new + 构造器的方式

     面试题：String s = new String("abc");方式创建对象，在内存中创建了几个对象？
            两个:一个是堆空间中new结构，另一个是char[]对应的常量池中的数据："abc"

     */
    @Test
    public void test2(){
        //通过字面量定义的方式：此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中。
        String s1 = "javaEE";
        String s2 = "javaEE";
        //通过new + 构造器的方式:此时的s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址值。
        // 堆里面存放的值是常量池 值"javaEE"的地址   s3 s4存的是堆里两个不同的地址 两个地址里面存的值是常量池的那个地址
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false 比的是堆的值的地址 和常量池里值的地址
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false 两个不同的地址

        System.out.println("***********************");
        Person p1 = new Person("Tom",12);
        Person p2 = new Person("Tom",12);

        System.out.println(p1.name.equals(p2.name));//true String类型equal比较的是值而不是地址
        System.out.println(p1.name == p2.name);//true  因为是通过字面量方式定义

        p1.name = "Jerry";
        System.out.println(p2.name);//Tom
    }


    /*
    String:字符串，使用一对""引起来表示。
    1.String声明为final的，不可被继承
    2.String实现了Serializable接口：表示字符串是支持序列化的。
            实现了Comparable接口：表示String可以比较大小
    3.String内部定义了final char[] value用于存储字符串数据 final 表示 数组的元素不能再被赋值
    4.String:代表不可变的字符序列。简称：不可变性。 数组的元素不能再被修改
        体现：1.当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值。
             2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
             3. 当调用String的replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
    5.通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中。
    6.字符串常量池中是不会存储相同内容的字符串的。
     */
    @Test
    public void test1(){
        String s1 = "abc";//字面量的定义方式 不是 用new 而是直接赋值
        String s2 = "abc";
        s1 = "hello";

        System.out.println(s1 == s2);//对象的话 比较s1和s2的地址值
        //栈里面 会有个 s1 和 s2  方法区里面 有个字符串常量池 常量池里面不会存两个相同的变量 池子里面有个 "abc" s1 s2都指向它
        //常量池里面有新的值 "hello" 而不是 在"abc" 地址上修改它的值 体现了不可变性

        System.out.println(s1);//hello
        System.out.println(s2);//abc

        System.out.println("*****************");

        String s3 = "abc";
        s3 += "def";
        System.out.println(s3);//abcdef  常量池有了新的值"abcdef" 也有了新的地址
        System.out.println(s2);

        System.out.println("*****************");

        String s4 = "abc";
        String s5 = s4.replace('a', 'm');
        System.out.println(s4);//abc
        System.out.println(s5);//mbc  常量池有了新的值"mbc" 也有了新的地址

    }

}

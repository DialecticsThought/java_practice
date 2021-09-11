package com.atguigu.java;



import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 * 类的加载过程： 类的加载 --->类的链接 ---->类的初始化
 * 1.将类的class文件读入内存,并为之创建一个java.Lang.Class对象
 * 2.将类的二进制数据合并到JRE
 * 3.JVM负责对类的初始化
 *
 * 类加载器的作用：将class文件字节码内容加载到内存中 将这些静态数据转换成方法去的运行时数据结构
 * 生成Class类的实例 作为方法去中 类数据的访问入口
 *
 * 类缓存  一旦加载到类加载器中 将维持加载一段时间 不过JVM可回收这些Class对象
 *
 * 引导类加载器 JVM自带的类加载器 负责Java平台核心库 无法直接获取
 * 扩展加载器 负责jre/lib/ext目录下的jar包 装入工作库
 * 系统类加载器  自己定义的类的加载
 * @author shkstart
 * @create 2019 下午 2:16
 */
public class ClassLoaderTest {

    @Test
    public void test1(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);

    }
    /*
    Properties：用来读取配置文件。

     */
    @Test
    public void test2() throws Exception {

        Properties pros =  new Properties();
        //此时的文件默认在当前的module下。
        //读取配置文件的方式一：
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
//        pros.load(fis);//加载对应的流

        //读取配置文件的方式二：使用ClassLoader
        //配置文件默认识别为：当前module的src下☆☆☆☆☆☆☆
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();//得到系统类加载器
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");//以流的方式得到资源
        pros.load(is);


        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ",password = " + password);



    }

}

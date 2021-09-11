package com.atguigu.java;


import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 对象流的使用
 * 1.ObjectInputStream 和 ObjectOutputStream
 * 2.作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 *
 * 序列化 用ObjectOutputStream保存基本类型数据或者 对象的机制
 * 反序列化 用 ObjectInputStream类读取基本类型数据或 对象的机制
 *
 * 3.要想一个java对象是可序列化的，需要满足相应的要求。见Person.java
 *
 * 4.序列化：
 * 对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种
 * 二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
 * 反序列化
 * 当其它程序获取了这种二进制流，就可以恢复成原来的Java对象。


 *序列化的好处 将任何实现了Serializable几口的对象转化为 字节数据
 * 序列化是 RMI（远程方法调用）过程的参数和返回值都必须实现的机制
 *

 * @author shkstart
 * @create 2019 上午 10:27
 */
public class ObjectInputOutputStreamTest {

    /*
    序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
    使用ObjectOutputStream实现
     */
    @Test
    public void testObjectOutputStream(){
        //序列化
        ObjectOutputStream oos = null;

        try {
            //1.造流 造文件 从程序输出到文件中
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            //2.写文件
            oos.writeObject(new String("我爱北京天安门"));
            oos.flush();//刷新操作
            //对自定义类的对象的序列化
            oos.writeObject(new Person("王铭",23));
            oos.flush();

            oos.writeObject(new Person("张学良",23,1001,new Account(5000)));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                //3. 关闭资源
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /*
    反序列化：将磁盘文件中的对象还原为内存中的一个java对象
    使用ObjectInputStream来实现
     */
    @Test
    public void testObjectInputStream(){
        //反序列化
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            //将磁盘文件中的对象还原为内存中的一个java对象
            Object obj = ois.readObject();
            String str = (String) obj;

            Person p = (Person) ois.readObject();
            Person p1 = (Person) ois.readObject();

            System.out.println(str);
            System.out.println(p);
            System.out.println(p1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }



    }

}

package com.atguigu.java1;


import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 实现TCP的网络编程
 * 例题2：客户端发送文件给服务端，服务端将文件保存在本地。
 *
 * @author shkstart
 * @create 2019 下午 3:53
 */
public class TCPTest2 {

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void client() throws IOException {
        //1.创建Socket对象，指明服务器端的ip和端口号 socket雷士传输信息的载体
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
        //2. 获取一个输出流，用于输出数据
        OutputStream os = socket.getOutputStream();
        //3.要把文件读进来 而且是非文本文件
        FileInputStream fis = new FileInputStream(new File("beauty.jpg"));
        //4. 写出数据的操作 传给服务端 getBytes()方法让传输的数据转成字节
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        //5.
        fis.close();
        os.close();
        socket.close();
    }

    /*
    这里涉及到的异常，应该使用try-catch-finally处理
     */
    @Test
    public void server() throws IOException {
        //1. 创建服务器端的ServerSocket，指明自己的端口号
        ServerSocket ss = new ServerSocket(9090);
        //2.调用accept()表示接收来自于客户端的socket
        Socket socket = ss.accept();
        //3. 获取输入流
        InputStream is = socket.getInputStream();
        //4. 把传来的文件读出到本地的路径
        FileOutputStream fos = new FileOutputStream(new File("beauty1.jpg"));
        //5.
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        //6.
        fos.close();
        is.close();
        socket.close();
        ss.close();

    }
}

package com.atguigu.java1;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDPd协议的网络编程
 * @author shkstart
 * @create 2019 下午 4:34
 */
public class UDPTest {

    //发送端
    @Test
    public void sender() throws IOException {
        //socket相当于快递员
        DatagramSocket socket = new DatagramSocket();



        String str = "我是UDP方式发送的导弹";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();//表示本地换回地址
        //所有数据都封装到DatagramPacket 里面有对方的ip和端口号
        //相当于快递
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);
        //发出去  可以想象成快递员发送快递
        socket.send(packet);

        socket.close();

    }
    //接收端
    @Test
    public void receiver() throws IOException {
        //指定端口号用来接收
        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
        //接收数据
        socket.receive(packet);
        //本质上写的是buffer 但还是写成packet packet.getData()就是buffer也就是字节数组
        // packet.getLength()指明有多少数据写到字节数组里
        System.out.println(new String(packet.getData(),0,packet.getLength()));
        //资源关闭
        socket.close();
    }
}

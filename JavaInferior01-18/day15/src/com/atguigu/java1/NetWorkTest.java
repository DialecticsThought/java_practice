package com.atguigu.java1;
/*
 * 接口的应用：代理模式
 * 
 */
public class NetWorkTest {
	public static void main(String[] args) {
		Server server = new Server();
//		server.browse();
		ProxyServer proxyServer = new ProxyServer(server);
		
		proxyServer.browse();
		
	}
}

interface NetWork{
	
	public void browse();
	
}

//被代理类
class Server implements NetWork{

	@Override
	public void browse() {
		System.out.println("真实的服务器访问网络");
	}

}

//代理类
class ProxyServer implements NetWork{

	//定义了Network接口的实现类
	private NetWork work;
	//构造函数 传入Network接口的实现类也就是要被代理的类，根据要被代理的类，创出代理类
	public ProxyServer(NetWork work){
		this.work = work;
	}
	

	public void check(){
		System.out.println("联网之前的检查工作");
	}
	
	@Override
	public void browse() {
		check();//代理类比被代理类多余的事情
		
		work.browse();
		
	}
	
}
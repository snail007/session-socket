Java Socket通信工具类SessionSocket，java 中的socket类能力很强大，但是使用起来就不是那么方便了，往往为了写一个简单的通信程序就需要处理很多东西写很多代码，学过VB人想必都知道Winsocket控件进行网络编程是多么简单只有在几个事件方法里面写上自己的处理代码就可以通信了，于是我就想能不能在Java里面实现这样的模式而且是多线程的，经过思考和实验，本类便产生了。
个人博客:http://www.host900.com/
看一个简单的示例：
客户端主要代码：
```
public class ClientSocket extends SessionSocket{

    public static void main(String args[]) {
    	Socket socket;
		try {
                        socket = new Socket(127.0.0.1,50000);

			ClientSocket clientSocket=new ClientSocket(socket);

			clientSocket.start();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
        public void onClose(Socket socket, Thread thread) {
		System.out.println("与服务器断开连接。");
	}
	public void onConnected(Socket socket, Thread thread) {
		System.out.println("连接服务器成功！");
	}
	public void onDataArrived(byte[] data, Socket socket, Thread thread) {
		System.out.println("有数据到达:"+new String(data));
	}
	public void onError(Exception e, Socket socket, Thread thread) {
		System.out.println("与服务器连接异常，断开连接。");
	}
}
```
就这么简单，只要继承本类然后实现几个事件处理方法，再写上自己的处理代码一个完整的socket通信程序就完成了。

## SessionSocket流程图 ##

![http://session-socket.googlecode.com/svn/trunk/SessionSocket%E6%B5%81%E7%A8%8B%E5%9B%BE.jpg](http://session-socket.googlecode.com/svn/trunk/SessionSocket%E6%B5%81%E7%A8%8B%E5%9B%BE.jpg)

## SessionSocket状态转换图 ##

![http://session-socket.googlecode.com/svn/trunk/SessionSocket%E7%8A%B6%E6%80%81%E8%BD%AC%E6%8D%A2%E5%9B%BE.jpg](http://session-socket.googlecode.com/svn/trunk/SessionSocket%E7%8A%B6%E6%80%81%E8%BD%AC%E6%8D%A2%E5%9B%BE.jpg)

## 示例运行截图 ##

![http://session-socket.googlecode.com/svn/trunk/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE.jpg](http://session-socket.googlecode.com/svn/trunk/%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE.jpg)
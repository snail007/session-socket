/**
 * @Title Client.java
 * @Package com.test
 * @Description 用一句话描述该文件做什么
 * @author 狂奔的蜗牛 672308444@163.com  
 * @date 2011-9-13 下午09:23:17
 * @version V1.0  
*/
package com.demo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import com.socketUtility.SessionSocket;


/**
 * @ClassName Client
 * @Description 客户端
 * @author 狂奔的蜗牛 672308444@163.com  
 * @date 2011-9-13 下午09:23:17
 *
 */
public class ClientSocket extends SessionSocket{


	public ClientSocket(Socket socket) {
		super(socket);
	}
    public static void main(String args[]) {
    	Socket socket;
		try {
			socket = new Socket(args[0],Integer.valueOf(args[1]));
			ClientSocket clientSocket=new ClientSocket(socket);
			//启动ClientSocket线程
			clientSocket.start();
			
			Thread iMonitor=new Thread(clientSocket.new InputMonitor());
			iMonitor.setDaemon(true);
			//启动输入
			iMonitor.start();
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
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
		System.out.println(new String(data));
	}
	public void onError(Exception e, Socket socket, Thread thread) {
		System.out.println("与服务器连接异常，断开连接。");
	}
	class InputMonitor implements Runnable{
		String nickName=null;
		public void run() {
		    while (nickName==null||nickName.equals("")) {
		    	System.out.println("请输入昵称:");
		    	nickName=getInput();
			}
		    System.out.println("Input:>");
			while(true){
				try {
					sendMessage((nickName+":"+getInput()).getBytes());
				} catch (Exception e) {
				}
			}
		}
		public String getInput(){
			InputStream inputStream=System.in;
			 BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
			try {
				String inputString=reader.readLine();
				return inputString;
			} catch (Exception e) {
				return null;
			}
		}
		
	}
}

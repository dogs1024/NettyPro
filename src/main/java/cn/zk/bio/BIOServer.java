package cn.zk.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zk
 * @version 1.0
 * @description BIO模式
 * @date 2021/2/25 11:52
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        //线程池机制
        //思路
        //1、创建一个线程池
        //2、如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //创建一个ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了...");

        while (true){
            //监听、等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println();
            System.out.println("连接到一个客户端...");
            //先创建主线程mainThread去执行程序，然后在创建子线程poolThread去与客户端通信
            System.out.println("当前线程id："+Thread.currentThread().getId()+"====当前线程名："+Thread.currentThread().getName());

            //创建一个线程与之通讯
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    //与客户端通讯
                    handler(socket);
                }
            });

        }

    }

    //单独编写handler方法，与客户端通讯
    public static void handler(Socket socket){
        byte[] bytes = new byte[1024];
        try {
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            System.out.println("当前线程id："+Thread.currentThread().getId()+"====当前线程名："+Thread.currentThread().getName());

            //循环读取客户端发送的数据
            while (true){
                System.out.println("当前线程id："+Thread.currentThread().getId()+"====当前线程名："+Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if (read!=-1) {
                    //输出客户端发送的数据
                    System.out.print(new String(bytes,0,read));
                }else {
                    //读完跳出循环
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭client连接...");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

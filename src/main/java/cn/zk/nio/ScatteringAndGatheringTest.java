package cn.zk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author zk
 * @version 1.0
 * @description 通过多个Buffer（即Buffer数组）完成读写操作，即Scattering、Gathering
 * Scattering：将数据写入到buffer时，可以采用buffer数组依次写入[分散]
 * Gathering：从buffer读取数据时，可以采用buffer数据依次读取
 * @date 2021/3/2 12:20
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {

        //使用ServerSocketChannel和SocketChannel 网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6000);

        //绑定端口到Socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建Buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接 （使用Telnet测试）
        SocketChannel socketChannel = serverSocketChannel.accept();

        //从客户端接收8个字节
        int messageLength = 8;
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long readTemp = socketChannel.read(byteBuffers);
                byteRead += readTemp;//累计读取的字节数
                System.out.println("byteRead：" + byteRead);
                //使用流打印，查看当前buffer的position和limit
                Arrays.asList(byteBuffers).stream().map(buffer -> "position=" + buffer.position() + "  limit=" + buffer.limit()).forEach(System.out::println);
            }
            //将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

            //将数据显示到控制台
            long byteWrite = 0;
            while (byteWrite<messageLength){
                long writeTemp = socketChannel.write(byteBuffers);
                byteWrite+= writeTemp;
            }

            //将所有的buffer进行clear
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead="+byteRead+"   byteWrite="+byteWrite+"     messageLength="+messageLength);

        }

    }
}

package cn.zk.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zk
 * @version 1.0
 * @description NIO——FileChannel——读本地文件
 * @date 2021/2/26 11:06
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {

        File file = new File("D:\\test\\file01.txt");

        //创建一个文件的输入流
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过fileInputStream获取FileChannel->实际类型FileChannelImpl
        FileChannel channel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将Channel通道的数据读入到buffer
        channel.read(byteBuffer);

        //将ByteBuffer中的字节数据转换成String
        System.out.println(new String(byteBuffer.array()));

        //关闭流
        fileInputStream.close();


    }
}

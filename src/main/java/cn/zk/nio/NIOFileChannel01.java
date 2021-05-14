package cn.zk.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zk
 * @version 1.0
 * @description NIO——FileChannel——写本地文件
 * @date 2021/2/26 10:32
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello,我要写入文件!";

        //创建一个输出流->Channel
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\file01.txt");

        //通过fileOutputStream获取对应的FileChannel。###这个FileChannel的真实类型是FileChannelImpl
        FileChannel channel = fileOutputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str放入到byteBuffer
        byteBuffer.put(str.getBytes());

        //对byteBuffer进行I/O反转
        byteBuffer.flip();

        //将byteBuffer数据写入到fileChannel
        channel.write(byteBuffer);
        fileOutputStream.close();

    }
}

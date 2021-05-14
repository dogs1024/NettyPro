package cn.zk.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zk
 * @version 1.0
 * @description MappedByteBuffer 可以在内存（堆外内存）中修改文件,操作系统不需要拷贝一次
 * @date 2021/3/2 11:21
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {

        //rw代表读写权限
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");

        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * @param FileChannel.MapMode.READ_WRITE 使用读写模式
         * @param 0 可以直接修改的起始位置
         * @param 5 映射到内存的大小（不是索引的位置）。即可直接修改的索引为0-4 。实际类型为DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'M');
        mappedByteBuffer.put(4, (byte) '0');
//        mappedByteBuffer.put(5, (byte) '9');//IndexOutOfBoundsException

        channel.close();
        randomAccessFile.close();

    }
}

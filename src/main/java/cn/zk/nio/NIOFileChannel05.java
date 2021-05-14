package cn.zk.nio;

import java.nio.ByteBuffer;

/**
 * @author zk
 * @version 1.0
 * @description 操作buffer注意事项
 *              1、put进去什么类型，get取出也得用对应的类型。否则可能出现BufferUnderflowException 异常。
 *              2、可以将普通Buffer转换成只读Buffer
 * @date 2021/3/1 17:05
 */
public class NIOFileChannel05 {
    public static void main(String[] args) {
        //创建一个普通Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putChar('爱');
        buffer.putDouble(3.1415926535);

        //取出数据——1、按顺序取。2、类型与存入的类型一致。否则可能抛出BufferUnderflowException异常
        buffer.flip();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
//        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());

        //将普通buffer转换成只读buffer。
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        //只读buffer存数据会抛出ReadOnlyBufferException异常
        readOnlyBuffer.put((byte) 100);


    }
}

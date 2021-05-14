package cn.zk.nio;

import java.nio.IntBuffer;

/**
 * @author zk
 * @version 1.0
 * @description NIO
 * @date 2021/2/25 17:16
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //举例说明Buffer的使用（简单说明）
        //创建一个Buffer
        IntBuffer allocate = IntBuffer.allocate(5);

        //向Buffer存放数据
//        allocate.put(10);
//        allocate.put(11);
//        allocate.put(12);
//        allocate.put(13);
//        allocate.put(14);

        //想Buffer中存入数据
        for (int i = 0; i < allocate.capacity(); i++) {//capacity()方法获取Buffer的容量
            allocate.put(i * 2);
        }

        //从Buffer中读取数据
        //###将Buffer转换，读写切换
        allocate.flip();

        while (allocate.hasRemaining()){//hasRemaining() 方法是迭代Buffer中是否有数据
            System.out.println(allocate.get());//get() 方法是Buffer中取到数据后指针会跳到下一个索引
        }

    }
}

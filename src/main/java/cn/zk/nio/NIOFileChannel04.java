package cn.zk.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author zk
 * @version 1.0
 * @description 使用transferFrom()方法快速拷贝文件。
 * @date 2021/3/1 16:37
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {

        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("D:\\test\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\b.jpg");

        //获取各个流对应的通道
        FileChannel channelInput = fileInputStream.getChannel();
        FileChannel channelOutput = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        channelOutput.transferFrom(channelInput, 0, channelInput.size());

        //关闭相关流
        channelInput.close();
        channelOutput.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}

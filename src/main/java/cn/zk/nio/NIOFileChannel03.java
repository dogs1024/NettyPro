package cn.zk.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zk
 * @version 1.0
 * @description 使用1个Buffer将一个文件拷贝到另一个文件
 *              将文件1读到buffer缓冲区，之后在将buffer缓冲区的数据写在文件2中
 *              file1.txt ——（read）——> Buffer ——（write）——> file2.txt
 * @date 2021/3/1 15:15
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {

        //原始文件 （没有路径默认在项目目录下找）
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel channel1 = fileInputStream.getChannel();

        //没有该文件会自动生成
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel channel2 = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(510);

        while (true){//可能一次读不完整个文件，所以要循环读取
            //这里有个重要操作，要清空buffer，否则flip()读写反转后跳不出死循环。
            buffer.clear();
            int read = channel1.read(buffer);
            System.out.println(read);
            if (read==-1) {//表示读完
                break;
            }
            //将buffer中的数据写到channel2 -- 2.txt
            buffer.flip();
            channel2.write(buffer);
        }

        //关闭相关的流
        channel1.close();
        channel2.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}

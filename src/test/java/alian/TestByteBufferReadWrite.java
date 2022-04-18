/**
 * @program: AlianNetty
 * @author: alian
 * @description: 测试
 * @create: 2022-04-14 14:24
 **/

package alian;

import util.ByteBufferUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        try(FileChannel channel =new RandomAccessFile("data.txt","rw").getChannel()) {
            ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode("测试Java啦");
//            RandomAccessFile rw = new RandomAccessFile("data.txt", "rw").getChannel();
            channel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

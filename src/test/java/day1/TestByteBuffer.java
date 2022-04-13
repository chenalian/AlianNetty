/**
 * @program: AlianNetty
 * @author: alian
 * @description: buffer测试
 * @create: 2022-04-13 21:47
 **/

package day1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        // FileChannel
        // 1.输入流 FileInputStream
        try(FileChannel channel =new FileInputStream("data.txt").getChannel()){
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            //从channel中读取数据
            while(channel.read(buffer)!=-1){
                buffer.flip();//将buffer切换成读的模式
                while(buffer.hasRemaining()){//判断buffer是否还有数据
                    byte b=buffer.get();
                    log.debug("{}",(char)b);
                }
                buffer.clear();
                //清空buffer，将buffer切换成读模式,或者buffer.compact();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

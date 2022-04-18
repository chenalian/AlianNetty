/**
 * @program: AlianNetty
 * @author: alian
 * @description: 服务器
 * @create: 2022-04-15 09:01
 **/

package alian.nbio;

import lombok.extern.slf4j.Slf4j;
import util.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
/**
* @Description: 非阻塞模式会不会进行阻塞，应该要采用轮询的方式完成业务逻辑
* @Param:
* @return:
* @Author: alian
* @Date: 2022/4/15
*/
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 建立服务器的连接
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);//设置非阻塞模式

        //绑定监听端口
        ssc.bind(new InetSocketAddress(8080));

        //连接集合
        List<SocketChannel> channels = new ArrayList<>();

        //读取buffer
        ByteBuffer buffer = ByteBuffer.allocate(16);

        while(true){
            SocketChannel sc=ssc.accept();// accept建立与客服端的连接，非阻塞方式
            if(sc!=null){
                sc.configureBlocking(false);// 设置非阻塞模式
                log.debug("conected、、、、、、、、、{}",sc);
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                int read = channel.read(buffer);
                if(read>0){
                    buffer.flip();// 切换模式
                    ByteBufferUtil.debugAll(buffer);
                    buffer.clear();
                }
            }
        }
    }
}

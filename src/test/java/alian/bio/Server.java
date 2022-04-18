/**
 * @program: AlianNetty
 * @author: alian
 * @description: 服务器
 * @create: 2022-04-15 09:01
 **/

package alian.bio;

import lombok.extern.slf4j.Slf4j;
import util.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 建立服务器的连接
        ServerSocketChannel ssc = ServerSocketChannel.open();

        //绑定监听端口
        ssc.bind(new InetSocketAddress(8080));

        //连接集合
        List<SocketChannel> channels = new ArrayList<>();

        //读取buffer
        ByteBuffer buffer = ByteBuffer.allocate(16);

        while(true){
            log.debug("connecting........");
            SocketChannel sc=ssc.accept();// accept建立与客服端的连接，阻塞方式
            log.debug("conected、、、、、、、、、{}",sc);
            channels.add(sc);
            for (SocketChannel channel : channels) {
                log.debug("before read/........{}",channel);
                channel.read(buffer);
                buffer.flip();// 切换模式
                ByteBufferUtil.debugAll(buffer);
                buffer.clear();
            }
        }
    }
}

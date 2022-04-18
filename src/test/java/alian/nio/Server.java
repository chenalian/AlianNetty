/**
 * @program: AlianNetty
 * @author: alian
 * @description: 服务器
 * @create: 2022-04-15 09:01
 **/
package alian.nio;
import lombok.extern.slf4j.Slf4j;
import util.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建selector对象，管理多个channe
        Selector selector = Selector.open();
        // 建立服务器的连接
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        //绑定监听端口
        ssc.bind(new InetSocketAddress(8080));
        // 建立selector的联系-将channel注册到selector中
        // SelectionKey事件发生后，通过它可以知道是哪个channel发生的事件
        // 事件的类型： accept、connect、read、write四种类型。
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // ssckey只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("registry key{}",sscKey);
        while(true){
            // selector没有事件就会阻塞，有事件就会运行
           selector.select();
            //处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            log.debug("selects中的selectKeys中的个数：{}",selector.selectedKeys().size());
            //electionKeys中包含了所有发生的事件,时间要么处理，要么取消，否则将会一直会认为未处理
            while(iterator.hasNext()){
                // 在selectKeys，是selector的复制，且新增事件会自动加入selectKeys中，但处理完之后需要手动进行移除。
                log.debug("selects中的selectKeys中的个数：{}",selector.selectedKeys().size());
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){//建立连接
                    log.debug("触发的可以：{}",key);
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();//建立连接
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16);// 将Bytebuffer和channel以附件的形式进行绑定到selector中
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                }else if(key.isReadable()){//读信息
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);
                        if(read==-1){
                            // 客户端主动关闭
                            key.cancel();
                        }else{
                            buffer.flip();
                            ByteBufferUtil.debugAll(buffer);
                        }
                    } catch (IOException e) {
                        // 将异常的客户端对应的channel进行删除，从select中的key删除
                        key.cancel();
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

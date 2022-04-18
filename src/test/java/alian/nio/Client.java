/**
 * @program: AlianNetty
 * @author: alian
 * @description: 客服端
 * @create: 2022-04-15 09:09
 **/

package alian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
* @Description: 阻塞模式，在建立连接诶，读取数据都会进行堵塞
* @Param:
* @return:
* @Author: alian
* @Date: 2022/4/15
*/
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();

        sc.connect(new InetSocketAddress("localhost",8080));

        System.out.println("waiting.........");

    }
}

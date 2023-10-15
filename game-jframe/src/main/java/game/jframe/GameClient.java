package game.jframe;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class GameClient {
    public static void main(String[] args) throws Exception{
        GameWindow gameWindow = new GameWindow(); // Запуск игрового окна
        gameWindow.repaint();
        new GameClient("localhost", 8000).run();
    }

    private final String host;
    private final int port;

    public GameClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new GameClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            // while(true) {
            //     String line = in.readLine();
            //     if (line == null) {
            //         break;
            //     }

            //     lastWriteFuture = channel.writeAndFlush(line + "\r\n");
            // }

            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}

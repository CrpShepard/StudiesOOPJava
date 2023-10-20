package game.jframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class GameClient {
    static GameWindow gameWindow;
    public static void main(String[] args) throws Exception{
        gameWindow = new GameWindow(); // Запуск игрового окна
        
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
                .handler(new GameClientInitializer(gameWindow));

            Channel channel = bootstrap.connect(host, port).sync().channel();
            // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                if (gameWindow.sentToServer) {
                    channel.writeAndFlush(gameWindow.playerMoveData);

                    gameWindow.sentToServer = false;

                    System.out.println("sent to server!");
                }

                Thread.sleep(200);
            }
                        
        } finally {
            group.shutdownGracefully();
        }
    }
}

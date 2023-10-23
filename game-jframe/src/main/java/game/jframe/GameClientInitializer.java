package game.jframe;

import io.netty.channel.socket.SocketChannel;
import game.jframe.Decoders.MyMessageDecoder;
import game.jframe.Encoders.MyMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class GameClientInitializer extends ChannelInitializer<SocketChannel>{
    private final GameWindow gameWindow;

    public GameClientInitializer(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();

        pipeline.addLast(new MyMessageDecoder());
        pipeline.addLast(new MyMessageEncoder());

        pipeline.addLast("handler", new GameClientHandler(gameWindow));
    }
}

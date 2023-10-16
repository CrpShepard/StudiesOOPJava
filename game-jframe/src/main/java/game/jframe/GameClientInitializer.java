package game.jframe;

import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;

public class GameClientInitializer extends ChannelInitializer<SocketChannel>{
    private final GameWindow gameWindow;

    public GameClientInitializer(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();

        //pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        //pipeline.addLast("decoder", new StringDecoder());
        //pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast(new IntegerEncoder());
        pipeline.addLast(new IntArrayDecoder());

        pipeline.addLast("handler", new GameClientHandler(gameWindow));
    }
}

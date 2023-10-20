package game.jframe;

import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import game.jframe.Decoders.AllPlayerMoveDataDecoder;
import game.jframe.Decoders.IntArrayDecoder;
import game.jframe.Decoders.PlayerIdDecoder;
import game.jframe.Decoders.PlayerMoveDataDecoder;
import game.jframe.Encoders.IntegerEncoder;
import game.jframe.Encoders.PlayerMoveDataEncoder;
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

        pipeline.addLast(new PlayerIdDecoder());
        pipeline.addLast(new AllPlayerMoveDataDecoder());
        pipeline.addLast(new PlayerMoveDataEncoder());
        pipeline.addLast(new PlayerMoveDataDecoder());

        pipeline.addLast("handler", new GameClientHandler(gameWindow));
    }
}

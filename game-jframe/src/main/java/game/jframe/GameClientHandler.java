package game.jframe;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GameClientHandler extends SimpleChannelInboundHandler<int[]>{
    private final GameWindow gameWindow;

    public GameClientHandler(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext arg0, int[] coords) throws Exception {
        System.out.println(coords.toString());
        gameWindow.setTarget(coords[0], coords[1], coords[2]);
    }
}

package game.jframe;

import game.jframe.MessageClasses.AllPlayerMoveData;
import game.jframe.MessageClasses.PlayerId;
import game.jframe.MessageClasses.PlayerMoveData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GameClientHandler extends SimpleChannelInboundHandler{
    private final GameWindow gameWindow;

    public GameClientHandler(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object message) throws Exception {
        System.out.println("GameClientHandler channelRead0");

        if (message instanceof PlayerId) {
            PlayerId playerId = (PlayerId) message;
            gameWindow.playerId = playerId.id;
            System.out.println("PlayerId");
            ctx.flush();
        }
        else if (message instanceof AllPlayerMoveData) {
            AllPlayerMoveData allPlayerMoveData = (AllPlayerMoveData) message;
            gameWindow.setAllPlayerMoveData(allPlayerMoveData);
            System.out.println("AllPlayerMoveData");
            ctx.flush();

        }
        else if (message instanceof PlayerMoveData) {
            PlayerMoveData playerMoveData = (PlayerMoveData) message;
            gameWindow.setPlayerMoveData(playerMoveData);
            System.out.println("PlayerMoveData");
            ctx.flush();

        }


    }
}

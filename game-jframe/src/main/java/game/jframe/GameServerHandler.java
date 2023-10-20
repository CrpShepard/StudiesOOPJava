package game.jframe;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import game.jframe.MessageClasses.AllPlayerMoveData;
import game.jframe.MessageClasses.PlayerId;
import game.jframe.MessageClasses.PlayerMoveData;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class GameServerHandler extends SimpleChannelInboundHandler{

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private ArrayList<SocketAddress> playerList = new ArrayList<SocketAddress>();

    AllPlayerMoveData allPlayerMoveData = new AllPlayerMoveData(
        new ArrayList<Boolean>(),
        new ArrayList<Long>(),
        new ArrayList<ArrayList<Integer>>(),
        new ArrayList<ArrayList<Integer>>(), 
        new ArrayList<ArrayList<Integer>>(), 
        new ArrayList<Float>());

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // игрок подключается
        Channel incoming = ctx.channel();
        channels.add(ctx.channel());

        playerList.add(incoming.remoteAddress());
        allPlayerMoveData.initializePlayer();

        PlayerId playerId = new PlayerId();
        playerId.id = playerList.size() - 1;

        incoming.writeAndFlush(playerId);
        incoming.writeAndFlush(allPlayerMoveData); // отправка данных о игроках подключившемуся

        System.out.println("channel added!" + ctx.channel().toString());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // игрок отключается
        Channel incoming = ctx.channel();
        channels.remove(ctx.channel());

        // TO-DO удаление данных игрока из сервака и передача об этом другим игрокам

        playerList.remove(incoming.remoteAddress());
        System.out.println("channel left!" + ctx.channel().toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object message) throws Exception {
        if (message instanceof PlayerMoveData) {
            PlayerMoveData msg = (PlayerMoveData) message;

            allPlayerMoveData.playerStartCoord.get(msg.getId()).set(msg.getId(), msg.getStartCoordX());
            allPlayerMoveData.playerStartCoord.get(msg.getId()).set(msg.getId(), msg.getStartCoordX());

            allPlayerMoveData.playerTargetCoord.get(msg.getId()).set(msg.getId(), msg.getTargetCoordX());
            allPlayerMoveData.playerTargetCoord.get(msg.getId()).set(msg.getId(), msg.getTargetCoordY());

            allPlayerMoveData.playerCoord.get(msg.getId()).set(msg.getId(), msg.getCoordX());
            allPlayerMoveData.playerCoord.get(msg.getId()).set(msg.getId(), msg.getCoordY());

            allPlayerMoveData.playerAnimStarted.set(msg.getId(), msg.isAnimStarted());
            allPlayerMoveData.playerAnimStartTime.set(msg.getId(), msg.getAnimStartTime());

            for (Channel channel : channels) {
                if (channel != ctx.channel()) {
                    channel.writeAndFlush(msg); // Отправка всем игрокам данные перемещения отправившего игрока
                }
            }

        }
        
    }
    
}

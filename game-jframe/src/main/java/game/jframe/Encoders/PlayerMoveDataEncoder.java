package game.jframe.Encoders;

import game.jframe.MessageClasses.PlayerMoveData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PlayerMoveDataEncoder extends MessageToByteEncoder<PlayerMoveData>{

    @Override
    protected void encode(ChannelHandlerContext ctx, PlayerMoveData message, ByteBuf out) throws Exception {
        out.writeInt(message.getId());
        out.writeInt(message.getStartCoordX());
        out.writeInt(message.getStartCoordY());
        out.writeInt(message.getTargetCoordX());
        out.writeInt(message.getTargetCoordY());
        out.writeInt(message.getCoordX());
        out.writeInt(message.getCoordY());
        out.writeBoolean(message.isAnimStarted());
        out.writeLong(message.getAnimStartTime());
    }
    
}

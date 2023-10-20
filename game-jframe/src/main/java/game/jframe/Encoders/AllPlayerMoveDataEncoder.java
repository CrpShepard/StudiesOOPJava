package game.jframe.Encoders;

import java.util.ArrayList;

import game.jframe.MessageClasses.AllPlayerMoveData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class AllPlayerMoveDataEncoder extends MessageToByteEncoder<AllPlayerMoveData>{

    @Override
    protected void encode(ChannelHandlerContext ctx, AllPlayerMoveData message, ByteBuf out) throws Exception {
        out.writeInt(message.playerAnimStarted.size());
        for (Boolean value: message.playerAnimStarted) {
            out.writeBoolean(value);
        }

        out.writeInt(message.playerAnimStartTime.size());
        for (Long value: message.playerAnimStartTime) {
            out.writeLong(value);
        }

        out.writeInt(message.playerCoord.size());
        for (ArrayList<Integer> list: message.playerCoord) {
            out.writeInt(list.size());
            for (Integer value: list) {
                out.writeInt(value);
            }
        }

        out.writeInt(message.playerStartCoord.size());
        for (ArrayList<Integer> list: message.playerStartCoord) {
            out.writeInt(list.size());
            for (Integer value: list) {
                out.writeInt(value);
            }
        }

        out.writeInt(message.playerTargetCoord.size());
        for (ArrayList<Integer> list: message.playerTargetCoord) {
            out.writeInt(list.size());
            for (Integer value: list) {
                out.writeInt(value);
            }
        }

        out.writeInt(message.playerMoveSpeed.size());
        for (Float value: message.playerMoveSpeed) {
            out.writeFloat(value);
        }
    }
    
}

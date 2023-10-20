package game.jframe.Decoders;

import java.util.ArrayList;
import java.util.List;

import game.jframe.MessageClasses.AllPlayerMoveData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class AllPlayerMoveDataDecoder extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int listSize = in.readInt();
        ArrayList<Boolean> playerAnimStarted = new ArrayList<Boolean>();
        for (int i = 0; i < listSize; i++) {
            playerAnimStarted.add(in.readBoolean());
        }

        listSize = in.readInt();
        ArrayList<Long> playerAnimStartTime = new ArrayList<Long>();
        for (int i = 0; i < listSize; i++) {
            playerAnimStartTime.add(in.readLong());
        }

        listSize = in.readInt();
        ArrayList<ArrayList<Integer>> playerCoord = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < listSize; i++) {
            int innerListSize = in.readInt();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < innerListSize; j++) {
                list.add(in.readInt());
            }
            playerCoord.add(list);
        }

        listSize = in.readInt();
        ArrayList<ArrayList<Integer>> playerStartCoord = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < listSize; i++) {
            int innerListSize = in.readInt();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < innerListSize; j++) {
                list.add(in.readInt());
            }
            playerStartCoord.add(list);
        }

        listSize = in.readInt();
        ArrayList<ArrayList<Integer>> playerTargetCoord = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < listSize; i++) {
            int innerListSize = in.readInt();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < innerListSize; j++) {
                list.add(in.readInt());
            }
            playerTargetCoord.add(list);
        }

        listSize = in.readInt();
        ArrayList<Float> playerMoveSpeed = new ArrayList<Float>();
        for (int i = 0; i < listSize; i++) {
            playerMoveSpeed.add(in.readFloat());
        }

        out.add(new AllPlayerMoveData(
            playerAnimStarted,
            playerAnimStartTime,
            playerCoord,
            playerStartCoord,
            playerTargetCoord,
            playerMoveSpeed));
    }
    
}

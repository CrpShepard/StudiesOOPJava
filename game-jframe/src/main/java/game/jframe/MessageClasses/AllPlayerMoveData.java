package game.jframe.MessageClasses;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AllPlayerMoveData extends MyMessage{
    public ArrayList<Boolean> playerAnimStarted;
    public ArrayList<Long> playerAnimStartTime;

    public ArrayList<ArrayList<Integer>> playerCoord;
    public ArrayList<ArrayList<Integer>> playerStartCoord;
    public ArrayList<ArrayList<Integer>> playerTargetCoord;
    public ArrayList<Float> playerMoveSpeed;

    public AllPlayerMoveData(
        ArrayList<Boolean> playerAnimStarted, 
        ArrayList<Long> playerAnimStartTime,
            ArrayList<ArrayList<Integer>> playerCoord, 
            ArrayList<ArrayList<Integer>> playerStartCoord,
            ArrayList<ArrayList<Integer>> playerTargetCoord, 
            ArrayList<Float> playerMoveSpeed) {
        this.playerAnimStarted = playerAnimStarted;
        this.playerAnimStartTime = playerAnimStartTime;
        this.playerCoord = playerCoord;
        this.playerStartCoord = playerStartCoord;
        this.playerTargetCoord = playerTargetCoord;
        this.playerMoveSpeed = playerMoveSpeed;
    }

    public void initializePlayer() {
        playerAnimStarted.add(false);
        playerAnimStartTime.add((long) 0);

        playerCoord.add(new ArrayList<Integer>());
        playerCoord.get(playerCoord.size() - 1).add(50); // x
        playerCoord.get(playerCoord.size() - 1).add(50); // y

        playerStartCoord.add(new ArrayList<Integer>());
        playerStartCoord.get(playerStartCoord.size() - 1).add(50); // x
        playerStartCoord.get(playerStartCoord.size() - 1).add(50); // y

        playerTargetCoord.add(new ArrayList<Integer>());
        playerTargetCoord.get(playerTargetCoord.size() - 1).add(50); // x
        playerTargetCoord.get(playerTargetCoord.size() - 1).add(50); // y

        playerMoveSpeed.add(0.1f);
    }

    //  public byte[] serialize() throws IOException {
    //     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    //     ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
    //     out.writeObject(this);
    //     out.flush();
    //     return byteArrayOutputStream.toByteArray();
    // }

    // // Статический метод для десериализации объекта из байтов
    // public static AllPlayerMoveData deserialize(byte[] data) throws IOException, ClassNotFoundException {
    //     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
    //     ObjectInputStream in = new ObjectInputStream(byteArrayInputStream);
    //     return (AllPlayerMoveData) in.readObject();
    // }
}

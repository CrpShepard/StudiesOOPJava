package game.jframe.MessageClasses;

import java.io.Serializable;

public class PlayerMoveData implements Serializable{
    private int id;
    
    private int startCoordX;
    private int startCoordY;

    private int targetCoordX;
    private int targetCoordY;

    private int coordX;
    private int coordY;

    private boolean animStarted;
    private long animStartTime;

    public PlayerMoveData(
        int id,
        int startCoordX,
        int startCoordY,
        int targetCoordX, 
        int targetCoordY, 
        int coordX, 
        int coordY, 
        boolean animStarted,
        long animStartTime) 
        {
        this.id = id;
        this.startCoordX = startCoordX;
        this.startCoordY = startCoordY;
        this.targetCoordX = targetCoordX;
        this.targetCoordY = targetCoordY;
        this.coordX = coordX;
        this.coordY = coordY;
        this.animStarted = animStarted;
        this.animStartTime = animStartTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartCoordX() {
        return startCoordX;
    }

    public void setStartCoordX(int startCoordX) {
        this.startCoordX = startCoordX;
    }

    public int getStartCoordY() {
        return startCoordY;
    }

    public void setStartCoordY(int startCoordY) {
        this.startCoordY = startCoordY;
    }

    public int getTargetCoordX() {
        return targetCoordX;
    }

    public void setTargetCoordX(int targetCoordX) {
        this.targetCoordX = targetCoordX;
    }

    public int getTargetCoordY() {
        return targetCoordY;
    }

    public void setTargetCoordY(int targetCoordY) {
        this.targetCoordY = targetCoordY;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public boolean isAnimStarted() {
        return animStarted;
    }

    public void setAnimStarted(boolean animStarted) {
        this.animStarted = animStarted;
    }

    public long getAnimStartTime() {
        return animStartTime;
    }

    public void setAnimStartTime(long animStartTime) {
        this.animStartTime = animStartTime;
    }
}

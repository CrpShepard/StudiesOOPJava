package game.jframe.MessageClasses;

import java.io.Serializable;

public class PlayerMoveData extends MyMessage{
    private int id;
    
    private int startCoordX;
    private int startCoordY;

    private int targetCoordX;
    private int targetCoordY;

    private int coordX;
    private int coordY;

    private boolean animStarted;
    private long animStartTime;

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

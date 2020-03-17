package first.graphic;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frame;
    private int currentFrame;
    private int numFrames;
    private int[] states;

    private int count;
    private int delay;
    private int timePlayed;

    public Animation(BufferedImage[] frames){
        setFrames(frames,0);
        timePlayed=0;
        states = new int[10];
    }

    public Animation() {
        timePlayed=0;
        states = new int[10];
    }

    public void setFrames(BufferedImage[] frames,int i){
        this.frame=frames;
        currentFrame=0;
        count=0;
        timePlayed=0;
        delay=2;
        if(states[i] == 0) {
            numFrames = frames.length;
        } else {
            numFrames = states[i];
        }
    }

    public void setDelay(int i){
        delay=i;
    }
    public void setFrame(int i){currentFrame=i;}
    public void setNumFrames(int i,int state){states[state]=i;}

    public void update(){
        if(delay==-1)
            return;

        count++;

        if(count==delay){
            currentFrame++;
            count=0;
        }
        if(currentFrame==numFrames){
            currentFrame=0;
            timePlayed++;
        }
    }

    public int getDelay(){
        return delay;
    }

    public int getFrame() {
        return currentFrame;
    }

    public BufferedImage getImage() {
        return frame[currentFrame];
    }

    public int getCount() {
        return count;
    }
    public boolean hasPlayedOnce(){
        return timePlayed>0;
    }
    public boolean hasPlayed(int i){
        return timePlayed==i;
    }
}

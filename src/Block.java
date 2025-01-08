import processing.core.PApplet;

public class Block {
    private int xLoc;
    private int yloc;
    private int color;
    private PApplet canves;
    private int screensize;
    private int BLocksize = 100;
    public Block(int xLoc, int yloc, int color, int size, PApplet c){
        this.xLoc = xLoc;
        this.yloc = yloc;
        this.color = color;
        this.canves = c;
        this.screensize = size;
    }
    //displays block
    public void Display(){
        canves.fill(color);
        canves.square(xLoc*screensize/20f, yloc*screensize/20f, screensize/20);
    }
    //location getters
    public int xLoc(){
        return xLoc;
    }
    public int yLoc(){
        return yloc;
    }

}

import processing.core.PApplet;

public class Coin {
    private float xLoc;
    private float yloc;
   
    private PApplet canves;
    private int screensize;
    public Coin(float xLoc, float yloc,int size, PApplet c){
        this.xLoc = xLoc;
        this.yloc = yloc;
        
        this.canves = c;
        this.screensize = size;
    }
    // check if coin is touvhing player
    public boolean touch(float playerXloc, float playerYloc){
        if (Math.sqrt(Math.pow(playerXloc - xLoc,2)+Math.pow(playerYloc - yloc,2)) < 1) {
            return true;
        }
        return false;
    }
    // displays the coin
    public void Display(){
        canves.fill(255,255,0);
        canves.circle(xLoc*screensize/20f, yloc*screensize/20f, screensize/20);
    }
    //moves the coin randomly 
    public void move(){
        double angle = Math.random()*2*Math.PI;
        xLoc += Math.cos(angle)*.1;
        yloc += Math.sin(angle)*.1;
        if (xLoc <0) {
            xLoc=0;
        }
        if (yloc <0) {
            yloc=0;
        }
        if (xLoc >20) {
            xLoc = 20;
        }
        if (yloc >20) {
            yloc = 20;
        }

    }


    
    
}

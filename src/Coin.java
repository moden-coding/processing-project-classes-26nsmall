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
    public boolean touch(float playerXloc, float playerYloc){
        System.out.println(Math.sqrt(Math.pow(2,playerXloc - xLoc)/Math.pow(2,playerYloc - yloc)));
        if (Math.sqrt(Math.pow(2,playerXloc - xLoc)/Math.pow(2,playerYloc - yloc)) < screensize/20) {
            return true;
        }
        return false;
    }
    public void Display(){
        canves.fill(255,255,0);
        canves.circle(xLoc*screensize/20f, yloc*screensize/20f, screensize/20);
    }
    
    
}

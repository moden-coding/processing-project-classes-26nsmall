import processing.core.PApplet;

public class View {
    private PApplet canvas;
    private int size;

    public View(PApplet c, int size) {
        this.canvas = c;
        this.size = size;
    }
    public void background(){
        canvas.background(0);
    }
    public void Player(){
        canvas.fill(255, 0, 0);
        canvas.circle(xLoc * screenSize / 20f, yLoc * screenSize / 20f, screenSize / 40);
    }
}

import java.util.ArrayList;

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
    public void drawPlayer(Player player){
        canvas.fill(255,0,0);
        canvas.circle(player.getXloc()*size/20, player.getYloc()*size/20, size/40);
    }
    public void drawBlocks(Block[][] blocks) {
        for (Block[] row : blocks) {
            for (Block block : row) {
                if (block != null) {
                    block.Display();
                }
            }
        }
    }
        public void drawCoins(ArrayList<Coin> coins) {
        for (Coin coin : coins) {
            coin.Display();
        }
    }
    public void UI(int coinsCollected, float timer) {
        canvas.fill(255);
        canvas.textAlign(PApplet.LEFT);
        canvas.textSize(size / 20);
        canvas.text("Coins: " + coinsCollected, 16 * size / 20, size / 20);
        canvas.text("Time Left: " + Math.floor(timer * 10) / 10, 0, size / 20);
    }
}

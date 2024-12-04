import processing.core.*;

import java.nio.file.Paths;
import java.util.*;

public class App extends PApplet {

    int size = 800;
    Block[][] blocks = new Block[20][20];
    Player player = new Player(1.5f, 18.75f, this, size);
    boolean LeftOrRightIsPressed = false;
    boolean leftHeld = false;
    boolean rightHeld = false;
    boolean upHeld = false;
    boolean dive = false;
    ArrayList<Coin> Coins = new ArrayList<Coin>();

    public static void main(String[] args) {

        PApplet.main("App");
    }

    public void setup() {
        try (Scanner scanner = new Scanner(Paths.get("Coin_Level.txt"))) {
            while (scanner.hasNextLine()) {
                String blockString = scanner.nextLine();
                String[] blockArray = blockString.split(",");
                int xLoc = Integer.valueOf(blockArray[0]);
                int yloc = Integer.valueOf(blockArray[1]);
                int color = Integer.valueOf(blockArray[2]);
                blocks[xLoc][yloc] = new Block(xLoc, yloc, color, size, this);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void settings() {

        size(size, size);

    }

    public void draw() {
        background(0);
        for (Block[] row : blocks) {
            for (Block block : row) {
                if (block != null)
                    block.Display();
            }

        }
        
        if (frameCount%60 == 0) {
            Coins.add(new Coin(random(1, 19), random(1, 19), size, this));
        }
        for (Coin coin: Coins) {
            coin.Display();
        }
        for (int i = 0; i < Coins.size(); i++){
            Coin coin = Coins.get(i);
            if (coin.touch(player.getXloc(), player.getYloc())) {
                Coins.remove(i);
            }
        }

        if (leftHeld) {
            if (player.isOnBlocY(blocks)) {
                if (player.getXSpeed() > -.1f) {
                    if (player.getXSpeed() > 0.01f) {
                        player.SetXCel(0);
                        LeftOrRightIsPressed = false;
                    } else {
                        player.SetXCel(-.002f);
                        LeftOrRightIsPressed = true;

                    }

                }

            } else if (player.getXSpeed() >= 0 && player.getXSpeed() > -.01f) {
                player.SetXCel(-.005f);
                LeftOrRightIsPressed = true;

            }

        }
        if (rightHeld) {
            if (player.isOnBlocY(blocks)) {
                if (player.getXSpeed() < .1f) {
                    if (player.getXSpeed() < -.01f) {
                        player.SetXCel(0);
                        LeftOrRightIsPressed = false;
                    } else {
                        player.SetXCel(.002f);
                        LeftOrRightIsPressed = true;
                    }

                }

            } else if (player.getXSpeed() <= 0 && player.getXSpeed() < .01f) {
                player.SetXCel(.005f);
                LeftOrRightIsPressed = true;

            }

        }

        player.Update(player.isOnBlocY(blocks), player.isOnBlocX(blocks), blocks, LeftOrRightIsPressed);
        player.Display();
        if (player.isOnBlocY(blocks)) {
            dive = false;
        }

    }

    public void keyPressed() {
        if (keyCode == UP) {
            if (player.isOnBlocY(blocks)) {
                player.SetYspeed(-.2f - Math.abs(player.getXSpeed() / 2));
            }

        }
        if (key == 'f') {
            player.setXLoc(1.5f);
            player.setYLoc(18.75f);
            player.SetXspeed(0);
            player.SetYspeed(0);
            player.SetXCel(0);
            player.SetYCel(0);
        }

        if (keyCode == LEFT) {
            leftHeld = true;

        }
        if (keyCode == RIGHT) {
            rightHeld = true;

        }
        if (keyCode == DOWN) {
            if (player.isOnBlocY(blocks) == false && dive == false) {
                if (player.getXCel() > 0) {
                    player.SetXspeed(Math.abs(player.getXSpeed() * 1.1f));
                } else if (player.getXCel() < 0) {
                    player.SetXspeed(-Math.abs(player.getXSpeed() * 1.1f));
                }
                System.out.println("2");
                player.SetXCel(player.getXCel() * 1.3f);
                player.SetYspeed(.2f);
                dive = true;
            }
        }
    }

    public void keyReleased() {
        if (keyCode == LEFT) {
            player.SetXCel(0f);
            leftHeld = false;
            LeftOrRightIsPressed = false;
        }
        if (keyCode == RIGHT) {
            rightHeld = false;
            player.SetXCel(0f);
            LeftOrRightIsPressed = false;
        }

    }
}

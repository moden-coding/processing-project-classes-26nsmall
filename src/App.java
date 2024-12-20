import processing.core.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class App extends PApplet {

    int size = 800;
    Block[][] blocks = new Block[20][20];
    Player player = new Player(1.5f, 18.75f, this, size);
    boolean leftHeld = false;
    boolean rightHeld = false;
    boolean upHeld = false;
    boolean dive = false;
    ArrayList<Coin> Coins = new ArrayList<Coin>();
    int CoinsCollected = 0;
    int timeWhenDive = 0;
    boolean levelDrawn = false;
    String level = "";
    boolean mouseClicked = false;
    float timer = -1;
    ArrayList<Integer> Leaderboard = new ArrayList<Integer>();
    Boolean added = false;
    String text ="";
    View view;
    modal model;
    Controller controller;
    boolean LeftOrRightIsPressed = false;

    public static void main(String[] args) {

        PApplet.main("App");
    }

    public void setup() {
        try (Scanner scanner = new Scanner(Paths.get("Leaderboard.txt"))) {
            while (scanner.hasNextLine()) {
                int numb = Integer.valueOf(scanner.nextLine());
                Leaderboard.add(numb);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        int loops = Leaderboard.size();
        for (int i = 0; i < 10 - loops; i++) {
            Leaderboard.add(0);

        }
        
        view = new View(this, size);
        model = new modal(size, player, blocks, Leaderboard);
        controller = new Controller(model, view, this);

    }

    public void settings() {
        size(size, size);
    }

    public boolean mouseOnButton(float top, float bottom, float left, float right) {
        if (mouseX > left && mouseX < right && mouseY > top && mouseY < bottom) {
            return true;
        }
        return false;
    }

    public void draw() {
        view.background();
        if (level.equals("")) {
            textSize(size / 8);
            textAlign(CENTER);

            rectMode(CENTER);
            fill(255, 255, 0);
            rect(size / 2, size * 7.1f / 16, size / 1.5f, size / 8);
            if (mouseClicked) {
                if (mouseOnButton(size * 7.1f / 16 - size / 8, size * 7.1f / 16 + size / 8, size / 2 - size / 1.5f,
                        size / 2 + size / 1.5f)) {
                    level = "Coin_Level.txt";
                }
            }
            fill(255);
            text("BALL", size / 2, size / 10);
            fill(0);
            text("Coin_Mode", size / 2, size / 2);
        } else if (level.equals("Leaderboard")) {
            textAlign(CENTER);
            fill(255);
            textSize(size / 8);
            text("Leaderboard", size / 2, size / 10);
            for (int i=0; i <10; i++){
                if (i<5) {
                    text((i+1)+". "+Leaderboard.get(i), 5*size/20,(i+2)*size/10 );
                }
                else{
                    text((i+1)+". "+Leaderboard.get(i), 11*size/20,(i+2-5)*size/10 );
                }
                
                

            }
        } else {
            rectMode(CORNER);

            if (levelDrawn == false) {
                try (Scanner scanner = new Scanner(Paths.get(level))) {
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

                levelDrawn = true;
            }

            for (Block[] row : blocks) {
                for (Block block : row) {
                    if (block != null)
                        block.Display();
                }

            }

            
            // if (rightHeld) {
            //     if (player.isOnBlocY(blocks)) {
            //         if (player.getXSpeed() < .05f) {
            //             if (player.getXSpeed() < -.01f) {
            //                 player.SetXCel(0);
            //                 LeftOrRightIsPressed = false;
            //             } else {
            //                 player.SetXCel(.002f);
            //                 LeftOrRightIsPressed = true;
            //             }

            //         }

            //     } else if (player.getXSpeed() <= 0 && player.getXSpeed() < .01f) {
            //         player.SetXCel(.005f);
            //         LeftOrRightIsPressed = true;

            //     }

            // }
            // if (leftHeld) {
            //     if (player.isOnBlocY(blocks)) {
            //         if (player.getXSpeed() > -.05f) {
            //             if (player.getXSpeed() > .01f) {
            //                 player.SetXCel(0);
            //                 LeftOrRightIsPressed = false;
            //             } else {
            //                 player.SetXCel(-.002f);
            //                 LeftOrRightIsPressed = true;
            //             }

            //         }

            //     } else if (player.getXSpeed() >= 0 && player.getXSpeed() > -.01f) {
            //         player.SetXCel(-.005f);
            //         LeftOrRightIsPressed = true;

            //     }

            // }
            // if (dive) {
            //     player.setColor(0, 255, 0);
            // } else {
            //     player.setColor(255, 0, 0);
            // }
            // if ((timeWhenDive - frameCount) % 15 == 0 && timeWhenDive != frameCount && timeWhenDive != 0) {
            //     timeWhenDive = 0;
            //     if (player.isOnBlocY(blocks)) {
            //         player.SetXspeed(player.getXSpeed() / 3);
            //     }
            //     dive = false;
            // }
            controller.Update();
            controller.render();

          //]  player.Update(player.isOnBlocY(blocks), player.isOnBlocX(blocks), blocks, LeftOrRightIsPressed);
            //player.Display();
            if (player.isOnBlocY(blocks) && timeWhenDive == 0) {
                dive = false;
            }
            if (level.equals("Coin_Level.txt")) {
                if (timer == -1) {
                    timer = 60;
                }
                timer -= .016f;
                // if (frameCount % 60 == 0) {
                //          model.addCoin((new Coin(random(1, 19), random(1, 19), size, this)));
                //     }

                // if (frameCount % 60 == 0) {
                //     Coins.add((new Coin(random(1, 19), random(1, 19), size, this)));
                // }
                // for (Coin coin : Coins) {
                //     coin.move();
                //     coin.Display();
                // }
                // for (int i = 0; i < Coins.size(); i++) {
                //     Coin coin = Coins.get(i);
                //     if (coin.touch(player.getXloc(), player.getYloc())) {
                //         CoinsCollected++;
                //         Coins.remove(i);
                //     }
                // }
                // textSize(size / 20);
                // fill(255);
                // textAlign(LEFT);
                // text("Coins:", 16 * size / 20, size / 20);
                // text("Time Left: " + Math.floor(10 * timer) / 10, 0, size / 20);
                // textAlign(RIGHT);  
                // text(CoinsCollected, size, size / 20);
                if (timer <= 0) {
                    
                    try (PrintWriter writer = new PrintWriter("Leaderboard.txt")) {
                        for (int num : Leaderboard) {
                            writer.println(num);
                        }
                        writer.close();

                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    System.out.println(Leaderboard);
                    level = "Leaderboard";
                }
            }

        }
        mouseClicked = false;

    }

    public void  keyPressed() {
        
        text += key;
        controller.handlekeyPressed(keyCode);

        // if (keyCode == UP) {
        //     if (player.isOnBlocY(blocks)) {
        //         player.SetYspeed(-.2f - (float) Math.sqrt(10 * Math.abs(player.getXSpeed())) / 10);
        //         System.out.println(-.2f - (float) Math.sqrt(10 * Math.abs(player.getXSpeed())) / 10);
        //     }

        // }
        // if (key == 'f') {
        //     player.setXLoc(1.5f);
        //     player.setYLoc(18.75f);
        //     player.SetXspeed(0);
        //     player.SetYspeed(0);
        //     player.SetXCel(0);
        //     player.SetYCel(0);
        // }

        // if (keyCode == LEFT) {
        //     leftHeld = true;

        // }
        // if (keyCode == RIGHT) {
        //     rightHeld = true;

        // }
        // if (keyCode == DOWN) {
        //     if (player.isOnBlocY(blocks) == false && dive == false) {
        //         if (player.getXCel() > 0) {
        //             player.SetXspeed(Math.abs(player.getXSpeed() * 1.1f));
        //         } else if (player.getXCel() < 0) {
        //             player.SetXspeed(-Math.abs(player.getXSpeed() * 1.1f));
        //         }

        //         player.SetXCel(player.getXCel() * 1.3f);
        //         player.SetYspeed(.4f);
        //         dive = true;
        //     } else if (dive == false) {
        //         if (player.getXCel() > 0) {
        //             player.SetXspeed(Math.abs(player.getXSpeed() * 2f));
        //             timeWhenDive = frameCount;
        //             dive = true;
        //         } else if (player.getXCel() < 0) {
        //             player.SetXspeed(-Math.abs(player.getXSpeed() * 2f));
        //             timeWhenDive = frameCount;
        //             dive =  true;
        //         }

        //     }
        // }
    }

    public void keyReleased() {
        controller.handlekeyReleased(keyCode);
        // if (keyCode == LEFT) {
        //     player.SetXCel(0f);
        //     leftHeld = false;
        //     LeftOrRightIsPressed = false;
        // }
        // if (keyCode == RIGHT) {
        //     rightHeld = false;
        //     player.SetXCel(0f);
        //     LeftOrRightIsPressed = false;
        // }

    }

    public void mouseClicked() {
        mouseClicked = true;

    }
}

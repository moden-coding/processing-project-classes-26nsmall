import java.util.ArrayList;

import javax.security.auth.login.ConfigurationSpi;

import processing.core.PApplet;
public class Controller {
    private modal model;
    private View view;
    private PApplet canvas;
    private boolean leftHeld = false;
    private boolean rightHeld = false;
    private boolean upHeld = false;
    private boolean DOWN = false;
    private boolean dive = false;
    private float timer = -1;
    boolean LeftOrRightIsPressed = false;
    private ArrayList<Coin> Coins;
    
    public Controller(modal model, View view, PApplet c){
        this.model = model;
        this.view = view;
        this.canvas = c;
        Coins = model.getCoins();
    }
    public void Update(){
        Player player = model.getPlayer();
        
        //LeftOrRightIsPressed = false;
        if (dive) {
            player.setColor(0, 255, 0);
        } else {
            player.setColor(255, 0, 0);
        }
        if ((model.timeWhenDive() - canvas.frameCount) % 15 == 0 && model.timeWhenDive() != canvas.frameCount && model.timeWhenDive() != 0) {
            //System.out.println("guhhuidgfhuigfduih");
            model.setTImewhendive(0);
            if (player.isOnBlocY(model.getBlocks())) {
                player.SetXspeed(player.getXSpeed() / 3);
            }
            dive = false;
        }
        if (player.isOnBlocY(model.getBlocks())&& model.timeWhenDive() == 0) {
            dive = false;
        }
        System.out.println(rightHeld);
        if (leftHeld) {
            if(player.left(model.getBlocks())){
                LeftOrRightIsPressed = true;
            }
        }
        if (rightHeld) {
           // System.out.println("fhjid");
            if(player.right(model.getBlocks())){
                LeftOrRightIsPressed = true;
            }
        }
        if (canvas.frameCount % 60 == 0) {
            model.addCoin((new Coin(canvas.random(1, 19), canvas.random(1, 19), 800, canvas)));
       }

        player.Update(player.isOnBlocY(model.getBlocks()), player.isOnBlocX(model.getBlocks()), model.getBlocks(), LeftOrRightIsPressed);
        for (int i = 0; i < Coins.size(); i++) {
            //System.out.println("ihguihufgihudfghuigdfiuhdgf");
            Coin coin = Coins.get(i);
             coin.move();
            if (coin.touch(player.getXloc(), player.getYloc())) {
                model.collectCoin(coin);
                //Coins.remove(i);
            }
        }
        Coins = model.getCoins();
    }
    
    public void handlekeyPressed(int keyCode){
        Player player = model.getPlayer();
        if(keyCode == canvas.DOWN){
            if (player.isOnBlocY(model.getBlocks()) == false && dive == false) {
                if (player.getXCel() > 0) {
                    player.SetXspeed(Math.abs(player.getXSpeed() * 1.1f));
                } else if (player.getXCel() < 0) {
                    player.SetXspeed(-Math.abs(player.getXSpeed() * 1.1f));
                }

                player.SetXCel(player.getXCel() * 1.3f);
                player.SetYspeed(.4f);
                dive = true;
            } else if (dive == false) {
                if (player.getXCel() > 0) {
                    player.SetXspeed(Math.abs(player.getXSpeed() * 2f));
                    model.setTImewhendive(canvas.frameCount);
                    dive = true;
                } else if (player.getXCel() < 0) {
                    player.SetXspeed(-Math.abs(player.getXSpeed() * 2f));
                    model.setTImewhendive(canvas.frameCount);
                    dive =  true;
                }

            }
        }
        if (keyCode == canvas.LEFT) {
            leftHeld = true;
            
        }
        if (keyCode == canvas.RIGHT) {
            rightHeld = true;
        }
        if (keyCode == canvas.UP) {
            if (player.isOnBlocY(model.getBlocks())) {
                player.SetYspeed(-.2f - (float) Math.sqrt(10 * Math.abs(player.getXSpeed())) / 10);
                System.out.println(-.2f - (float) Math.sqrt(10 * Math.abs(player.getXSpeed())) / 10);
            }
        }
    }
    public void handlekeyReleased(int keyCode){
        if (keyCode == canvas.LEFT) {
            leftHeld = false;
            LeftOrRightIsPressed = false;
        }
        if (keyCode == canvas.RIGHT) {
            rightHeld = false;
            LeftOrRightIsPressed = false;
        }
    }
    public void render(){
        view.background();
        view.drawBlocks(model.getBlocks());
        view.drawCoins(model.getCoins());
        view.drawPlayer(model.getPlayer());
        view.UI(model.getCoinsCollected(), timer);
    }
    
}

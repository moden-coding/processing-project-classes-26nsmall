import java.security.PublicKey;

import processing.core.PApplet;

public class Player {
    private float xLoc;
    private float yLoc;
    private float xspeed;
    private float yspeed;
    private float xCel = .0f;
    private float yCel = .01f;
    private PApplet canvas;
    private int screenSize;

    

    public Player(float xLoc, float yLoc, PApplet c, int size) {
        this.screenSize = size;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.canvas = c;
    }


    public float getYloc() {
        return yLoc;

    }

    public float getXloc() {
        return xLoc;

    }
    public float getXSpeed(){
        return xspeed;
    }
    public float getYSpeed(){
        return yspeed;
    }
    public void setXLoc(float setter){
        xLoc = setter;

    }
    public void setYLoc(float setter){
        yLoc = setter;

    }

    public boolean isOnBlocY(Block[][] blocks) {
        for (Block[] blocksss : blocks) {
            for (Block block : blocksss) {
                if (block != null) {
                    float yLocNext = yLoc + yspeed + yCel;
                    if (yLocNext >= block.yLoc() - 1 / 4f && yLocNext <= block.yLoc() + 5 / 4f
                            && xLoc > block.xLoc() - 24 / 100f && xLoc < block.xLoc() + 5 / 4f) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean isOnBlocX(Block[][] blocks) {
        for (Block[] blocksss : blocks) {
            for (Block block : blocksss) {
                if (block != null) {
                    float xLocNext = xLoc + xspeed + xCel;
                    if (xLocNext >= block.xLoc() - 1 / 4f && xLocNext <= block.xLoc() + 5 / 4f
                            && yLoc > block.yLoc() - 24 / 100f && yLoc < block.yLoc() + 5 / 4f) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public float yValueOfBlock(Block[][] blocks) {
        for (Block[] blocksss : blocks) {
            for (Block block : blocksss) {
                if (block != null) {
                    float yLocNext = yLoc + yspeed + yCel;
                    if (yLocNext >= block.yLoc() - 1 / 4f && yLocNext <= block.yLoc() + 5 / 4f
                            && xLoc > block.xLoc() - 24 / 100f && xLoc < block.xLoc() + 5 / 4f) {
                        if (yspeed > 0) {
                            return block.yLoc() - 1 / 4f;
                        }
                        if (yspeed < 0) {
                            return block.yLoc() + 5 / 4f;
                        }
                    }
                }

            }
        }
        return 0;
    }

    public float xValueOfBlock(Block[][] blocks) {
        for (Block[] blocksss : blocks) {
            for (Block block : blocksss) {
                if (block != null) {
                    float xLocNext = xLoc + xspeed + xCel;
                    if (xLocNext >= block.xLoc() - 1 / 4f && xLocNext <= block.xLoc() + 5 / 4f
                            && yLoc > block.yLoc() - 24 / 100f && yLoc < block.yLoc() + 5 / 4f) {
                        if (xspeed > 0) {
                            return block.xLoc() - 1 / 4f;
                        }
                        if (xspeed < 0) {
                            return block.xLoc() + 5 / 4f;
                        }
                    }
                }

            }
        }
        return 0;
    }

    public void SetXspeed(float setter) {
        xspeed = setter;

    }
    public void setColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void SetYspeed(float setter) {
        yspeed = setter;
        

    }

    public void SetXCel(float setter) {
        xCel = setter;

    }

    public void SetYCel(float setter) {
        yCel = setter;

    }
    public float getXCel(){
        return xCel;
    }

    public void Update(Boolean isOnBlocY, Boolean isOnBlockX, Block[][] blocks, Boolean LeftOrRightIsPressed ) {
        if (isOnBlocY) {
            if (yValueOfBlock(blocks) != 0) {
                yLoc = yValueOfBlock(blocks);
                yspeed = 0;
                yCel = 0;
                System.out.println(yLoc%1);
                if (yLoc%1 == 1/4f) {
                    yLoc += .01f;
                }
            }
           
            
            if (LeftOrRightIsPressed != true) {
                xspeed *= .9f;
            }

        } else {
            yCel = .01f;
            yspeed +=yCel;
            yLoc += yspeed;
        }
        if (isOnBlockX) {
            if (xValueOfBlock(blocks) != 0) {
                xLoc = xValueOfBlock(blocks);
                xspeed = 0;
                xCel = 0;
            }

        } else {
            
            xspeed += xCel;
            xLoc += xspeed;

        }
        if (xLoc <0) {
            xLoc = 20;
        }
        if (xLoc >20) {
            xLoc = 0;
        }
        if (yLoc <0){
            yLoc = 0;
        }

    }

}

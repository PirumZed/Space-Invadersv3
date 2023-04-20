import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    private double x;
    private double y;
    private double velX;
    private Textures textures;
    public Player(double x, double y, Textures textures){
        this.x = x;
        this.y = y;
        this.textures = textures;

    }

    public void tick(){
        x += velX;

        if (x <= 0)
            x = 0;
        if (x >= 640 - 32)
            x = 640 - 32;
    }

    public void render(Graphics g){
        g.drawImage(textures.player,(int)x,(int)y,null);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }
}


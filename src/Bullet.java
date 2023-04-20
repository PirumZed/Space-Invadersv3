import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {
    private double x;
    private double y;
    private Textures textures;

    public Bullet(double x, double y, Textures textures){
        this.x = x;
        this.y = y;
        this.textures = textures;
    }



    public void tick(){
        y -= 10;
    }

    public void render(Graphics g){
        g.drawImage(textures.bullet,(int)x,(int)y,null);
    }

    public double getY() {
        return y;
    }
}

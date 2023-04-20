import java.awt.*;

public class Enemy {


    private  double x,y;
    private Textures textures;
    public Enemy(double x, double y, Textures textures){
        this.x = x;
        this.y = y;
        this.textures = textures;
    }
    public void tick(){
        y += 0.5;

        if (y >= 480 - 32 )
            y = 0;
    }
    public void render(Graphics g){
        g.drawImage(textures.enemy,(int)x,(int)y,null);
    }
}

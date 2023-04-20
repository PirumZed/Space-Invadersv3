import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    public final String TITLE = "SPACE INVADERS";

    private BufferedImage image = new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private boolean is_shooting = false;
    private Player p;
    private Controller c;
    private Textures textures;
    private void init() {
        requestFocus();
        BufferedImageLoader ldr = new BufferedImageLoader();
        try{
            spriteSheet = ldr.loadImage("images/sprite_sheet.png");
            background = ldr.loadImage("images/background.png");
        }catch(IOException e){
            e.printStackTrace();
        }
        addKeyListener(new KeyInput(this));
        textures = new Textures(this);
        p = new Player(290,400,textures);
        c = new Controller(this,textures);


    }

    private synchronized void start(){
    if(running)
        return;
    running = true;
    thread = new Thread(this);
    thread.start();
    }
    private synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.exit(1);

    }
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer =  System.currentTimeMillis();
        while(running){
            // game loop goes here
            long now =  System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + "Ticks, FPS" + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    private void tick(){
        p.tick();
        c.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////

        g.drawImage(image,0,0,640,480,this);

        g.drawImage(background,0,0,null);
        p.render(g);
        c.render(g);
        ////
        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(5);
        } else if (key == KeyEvent.VK_LEFT) {
            p.setVelX(-5);
        } else if (key == KeyEvent.VK_SPACE && !is_shooting) {
            is_shooting = true;
            c.addBullet(new Bullet(p.getX(),p.getY(),textures));
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_SPACE) {
            is_shooting = false;
        }
    }




    public static void main(String args[]){
        App app = new App();

        app.setPreferredSize(new Dimension(640,480));
        app.setMinimumSize(new Dimension(640,480));
        app.setMaximumSize(new Dimension(640,480));

        JFrame frame =  new JFrame(app.TITLE);
        frame.add(app);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        app.start();
    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }


}

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    App app;
    public KeyInput(App app){
        this.app = app;
    }
    public void keyPressed(KeyEvent e){
    app.keyPressed(e);
    }
    public void keyReleased(KeyEvent e) {
    app.keyReleased(e);
    }

    }

package Lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIGHT = 800;
    private static final int WINDOW_HEIGHT = 600;

    private static int click = 0;

    Sprite[] sprite = new Sprite[10];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();

            }
        });
    }

   private MainCircles(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X,POS_Y,WINDOW_WIGHT,WINDOW_HEIGHT);

        GameCanvas canvas = new GameCanvas(this);
        add(canvas, BorderLayout.CENTER);

        setTitle("Circles");

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Click(e);
            }
        });

        setVisible(true);

    }

    private void Click(MouseEvent e) {
        System.out.println("e.getbutton" + e.getButton());

        if ((e.getButton() == 1) && (click < sprite.length-1)  ) {
            click++;
            initApplication();
        }

        if ((e.getButton() == 3) && (click > 0)  ) {
            sprite[click] = null;
            click--;
          }

        System.out.println(click);
    }

    private void initApplication() {
        sprite[click] = new Ball();
    }

    void onCanvasRepainted (GameCanvas canvas, Graphics g,  float deltatime) {
        update(canvas, deltatime);
        render(canvas, g);

    }

    private void update (GameCanvas canvas, float deltatime){
        for (int i =0; i < sprite.length; i++){
        if(sprite[i]!=null){
            sprite[i].update(canvas,deltatime);}
        }

    }

    private void render (GameCanvas canvas, Graphics g) {
          for (int i =0; i < sprite.length; i++){
            if(sprite[i]!=null){
            sprite[i].render(canvas, g);}
        }
    }

}

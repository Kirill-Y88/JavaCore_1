package Lesson1;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    MainCircles listener;
    long lastFrameTime;

    int i = 1, r = 1, gr = 3*i, bl = 2*i, ib = 2*i, ig = 3*i;

    GameCanvas(MainCircles listener){
        this.listener = listener;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime)*0.000000001f;
        lastFrameTime = currentTime;

    listener.onCanvasRepainted(this, g,  deltaTime);
        try {
            Thread.sleep(17);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

// изменение цвета канвы
        setBackground(new Color(r,gr,bl));

        if(r > 255 - Math.abs(i)) { i = -i;}
        if(r < 0 + Math.abs(i)) {i = -i;}
        r += i;

        if(gr > 255- Math.abs(ig)) { ig = -ig;}
        if(gr < 0 + Math.abs(ig)) {ig = -ig;}
        gr += ig;

        if(bl > 255 - Math.abs(ib)) { ib = -ib;}
        if(bl < 0 + Math.abs(ib)) {ib = -ib;}
        bl += ib;

        repaint();
    }
    public  int getLeft() {return  0;}
    public  int getRight() {return  getWidth() - 1;}
    public  int getTop() {return  0;}
    public  int getBottom() {return  getHeight() - 1;}

    //private

}



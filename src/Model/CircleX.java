package Model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by caitlincoyiuto on 2016-12-23.
 */
public class CircleX implements ShapeX {

    private Ellipse2D circle;
    private int w, h;
    private int posX, posY, initPosX, initPosY;
    private double velX, velY;
    private double spdIncrement;

    public CircleX(int w, int h, double velX, double velY, int posX, int posY, int spdIncrement){
        this.w = w;
        this.h = h;
        this.velX = velX;
        this.velY = velY;
        this.posX = posX;
        this.posY = posY;
        initPosX = posX;
        initPosY = posY;
        this.spdIncrement = spdIncrement;

        circle = new Ellipse2D.Double(posX, posY, w, h);

    }

    public void resetPos(){
        posX = initPosX;
        posY = initPosY;
    }

    public Object getObject(){return circle; }

    public boolean containsShape(Point p){
        return circle.contains(p);
    }

    public void calcVel(int width, int height){
        if (posX < 0 || posX > width) {
            velX = -velX;
        }
        if (posY < 0 || posY > height) {
            velY = -velY;
        }
        posX += velX*spdIncrement;
        posY += velY*spdIncrement;
    }

    @Override
    public void setPosXY(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void setVelXY(double velX, double velY) {
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void setSpdIncrement(double spdIncrement){
        this.spdIncrement = spdIncrement;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

}

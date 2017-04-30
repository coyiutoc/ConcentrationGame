package Model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by caitlincoyiuto on 2016-12-24.
 */
public class RectangleX implements ShapeX {

    private Rectangle2D rectangle;
    private int w, h;
    private int posX, posY, initPosX, initPosY;
    private double velX, velY;
    private double spdIncrement;

    public RectangleX(int w, int h, double velX, double velY, int posX, int posY, int spdIncrement){
        this.w = w;
        this.h = h;
        this.velX = velX;
        this.velY = velY;
        this.posX = posX;
        this.posY = posY;
        initPosX = posX;
        initPosY = posY;
        this.spdIncrement = spdIncrement;

        rectangle = new Rectangle2D.Double(posX, posY, w, h);
    }

    public void resetPos(){
        posX = initPosX;
        posY = initPosY;
    }

    public Object getObject(){
        return rectangle;
    }

    public boolean containsShape(Point p){
        return rectangle.contains(p.getX(), p.getY());
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

    public static void main(String[] args){

        RectangleX temp = new RectangleX(50, 100, 2, 2, 0, 0, 2);
        Point p = new Point(0, 0);

        System.out.println(temp.containsShape(p));

        Rectangle2D temp2 = new Rectangle2D.Double();
        temp2.setRect(0, 0, 50, 100);

        System.out.println(temp2.contains(p.getX(), p.getY()));

        String FontList[];
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        FontList = ge.getAvailableFontFamilyNames();

        for (int i = 0; i<FontList.length; i++){
            System.out.println(FontList[i]);
        }

    }
}

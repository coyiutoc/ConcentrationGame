package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by caitlincoyiuto on 2016-12-23.
 */
public interface ShapeX {

    public void setPosXY(int posX, int posY);
    public void setVelXY(double velX, double velY);
    public void calcVel(int width, int height);
    public void setSpdIncrement(double spdIncrement);
    public int getW();
    public int getH();
    public int getPosX();
    public int getPosY();
    public boolean containsShape(Point p);
    public Object getObject();
    public void resetPos();


}

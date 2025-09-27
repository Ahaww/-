package org.example.obj;


import org.example.GamePlane;

import java.awt.*;

public class GameObj {
    Image img;
    int x,y;
    int width,height;
    double speed;
    GamePlane frame;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GamePlane getFrame() {
        return frame;
    }

    public void setFrame(GamePlane frame) {
        this.frame = frame;
    }

    public GameObj(int x, int y, double speed, Image img) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.img = img;
    }

    public GameObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObj()
    {

    }
    public GameObj(Image img,int x,int y,int width,int height,double speed,GamePlane frame)
    {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;

    }
    public void paintSelf(Graphics gImage)
    {
        gImage.drawImage(img,x,y,null);
    }
    public Rectangle getRec()
    {
        return new Rectangle(x,y,width,height);
    }


}

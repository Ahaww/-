package org.example;

import java.util.Random;

public class Node {
    private int x;
    private int y;
    public Node(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    //随机生成食物
    public void randomFood()
    {
        Random r=new Random();
        this.x=r.nextInt(39);
        this.y=r.nextInt(39);;
    }
}

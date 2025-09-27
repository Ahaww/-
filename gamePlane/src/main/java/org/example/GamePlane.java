package org.example;

//我要做一个飞机大战游戏
import org.example.obj.*;
import org.example.utils.GameUtils;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePlane extends JFrame{
    public static int state = 0;
    public static int score = 0;
    int height=600;
    int width=600;
    Image offScreenImage=null;
    int count=1;
    int enemyCount=0;
    BgObj bgObj=new BgObj(GameUtils.bgImage,0,-1000,2);
    public PlaneObj planeObj=new PlaneObj(GameUtils.planeImage,190,400,40,40,0,this);
    public BossObj bossObj=null;

    public void launch()
    {
        this.setTitle("飞机大战");
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo( null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);



        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==1&&state==0)
                {
                    state = 1;
                    repaint();
                }

            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE)
                {
                    switch(state)
                    {
                        case 1:
                            state=2;
                            break;
                        case 2:
                            state=1;
                            break;
                        default:
                    }
                }
            }
        });
        while(true) {
            if(state==1)
            {
                createObj();
                repaint();
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void paint(Graphics g)
    {
        if(offScreenImage==null)
        {
            offScreenImage=this.createImage(getWidth(),getHeight());
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.setColor(Color.white);
        gImage.fillRect(0,0,600,600);

        if(state==0)
        {
            gImage.drawImage(GameUtils.bgImage,0,0,getWidth(),getHeight(),null);
            gImage.drawImage(GameUtils.enemyImage,250,120,100,100,null);
            gImage.drawImage(GameUtils.boobImage,250,350,100,100,null);
            GameUtils.drawWord(gImage,"点击开始游戏",Color.CYAN,40,180,300);

        }
        if(state==1)
        {
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);
           for(int i = 0; i < GameUtils.gameObjList.size(); i++)
           {
               GameUtils.gameObjList.get(i).paintSelf(gImage);
           }
           GameUtils.gameObjList.removeAll(GameUtils.removeList);
        }

        if(state==3)
        {
            gImage.drawImage(GameUtils.boobImage, planeObj.getX(), planeObj.getY(), 30,30,null);
            GameUtils.drawWord(gImage,"游戏结束",Color.red,50,180,300);

        }
        if(state==4)
        {
            gImage.drawImage(GameUtils.boobImage, bossObj.getX()+30, bossObj.getY(), 40,40,null);
            GameUtils.drawWord(gImage,"游戏通关",Color.red,50,180,300);

        }
        GameUtils.drawWord(gImage,"分数："+score,Color.black,30,20,80);
        g.drawImage(offScreenImage,0,0,600,600,null);
        count++;


    }

    void createObj()
    {
        if(count%15==0) {
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImage, planeObj.getX() + 3, planeObj.getY() - 16, 14, 29, 5, this));
            GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }
        if(count%15==0) {
            GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImage,(int)(Math.random()*12)*50,0,30,30,5,this));
            GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() - 1));
            enemyCount++;
        }
        if(count%20==0&&bossObj!=null) {
            GameUtils.bulletObjList.add(new BulletObj(GameUtils.bulletImage, bossObj.getX() + 76, bossObj.getY() + 85, 20, 20, 5, this));
            GameUtils.gameObjList.add(GameUtils.bulletObjList.get(GameUtils.bulletObjList.size() - 1));
        }
        if(enemyCount>100&&bossObj== null)
        {
            bossObj = new BossObj(GameUtils.bossImage, 250, 35, 100, 100, 5, this);
            GameUtils.gameObjList.add(bossObj);
        }
    }


    public static void main(String[] args)
    {
        GamePlane gamePlane = new GamePlane();
        gamePlane.launch();
    }
}

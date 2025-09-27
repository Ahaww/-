package org.example.utils;

import org.example.obj.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {
    // 声明多个图片变量
    public static Image bgImage;
    public static Image planeImage;
    public static Image enemyImage;
    public static Image boobImage;
    public static Image shellImage;
    public static Image bossImage;
    public static Image bulletImage;

    public static List<GameObj> gameObjList = new ArrayList<>();

    public static List<ShellObj> shellObjList = new ArrayList<>();

    public static List<EnemyObj> enemyObjList = new ArrayList<>();

    public static List<GameObj> removeList = new ArrayList<>();

    public static List<BulletObj> bulletObjList = new ArrayList<>();

    public static List<ExplodeObj> explodeObjList = new ArrayList<>();

    public static void drawWord(Graphics gImage, String str, Color color, int size, int x, int y)
    {
        gImage.setColor(color);
        gImage.setFont(new Font("仿宋", Font.BOLD, size));
        gImage.drawString(str, x, y);
    }



    static {
        // 加载背景图片
        bgImage = Toolkit.getDefaultToolkit().getImage("img/background.jpg");

        // 加载玩家图片
        planeImage = Toolkit.getDefaultToolkit().getImage("img/hero.png");

        // 加载敌人图片
        enemyImage = Toolkit.getDefaultToolkit().getImage("img/enemy.png");

        boobImage = Toolkit.getDefaultToolkit().getImage("img/boob.png");
        //子弹图片
        shellImage = Toolkit.getDefaultToolkit().getImage("img/shell.png");

        bossImage = Toolkit.getDefaultToolkit().getImage("img/enemy.png");

        bulletImage = Toolkit.getDefaultToolkit().getImage("img/bullet.png");

        // 使用 MediaTracker 确保所有图片加载完成
        MediaTracker tracker = new MediaTracker(new JLabel());
        tracker.addImage(bgImage, 0);
        tracker.addImage(planeImage, 1);
        tracker.addImage(enemyImage, 2);
        tracker.addImage(boobImage, 3);
        tracker.addImage(shellImage, 4);
        tracker.addImage(bossImage, 5);
        tracker.addImage(bulletImage, 6);

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

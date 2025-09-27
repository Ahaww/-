package org.example.obj;

import org.example.GamePlane;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneObj extends GameObj{
    public PlaneObj(int x, int y, double speed, Image img) {
        super(x, y, speed, img);
    }

    public PlaneObj() {
    }

    public PlaneObj(Image img, int x, int y, int width, int height, double speed, GamePlane frame) {
        super(img, x, y, width, height, speed, frame);
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                PlaneObj.super.x=e.getX()-20;
                PlaneObj.super.y=e.getY()-20;
            }
        });
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    @Override
    public void paintSelf(Graphics gImage) {
        if(img != null) {
            // 使用 drawImage 的缩放功能
            gImage.drawImage(img, x, y, width, height, null);
            if(this.frame.bossObj!=null&&this.getRec().intersects(this.frame.bossObj.getRec()))
            {
                GamePlane.state = 3;
            }

        }
    }


    @Override
    public Image getImg() {
        return super.getImg();
    }
}

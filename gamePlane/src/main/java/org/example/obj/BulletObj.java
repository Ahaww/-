package org.example.obj;

import org.example.GamePlane;
import org.example.utils.GameUtils;

import java.awt.*;

public class BulletObj extends GameObj{
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public BulletObj(int x, int y, double speed, Image img) {
        super(x, y, speed, img);
    }

    public BulletObj() {
    }

    public BulletObj(Image img, int x, int y, int width, int height, double speed, GamePlane frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        if(img != null) {
            // 使用 drawImage 的缩放功能
            gImage.drawImage(img, x, y, width, height, null);
            y+=speed;
            if(this.getRec().intersects(this.frame.planeObj.getRec()))
            {
                GamePlane.state = 3;
            }
            if(y>600)
            {
                this.x=-300;
                this.y=300;
                GameUtils.removeList.add(this);
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}

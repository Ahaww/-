package org.example.obj;

import org.example.GamePlane;
import org.example.utils.GameUtils;

import java.awt.*;

public class ShellObj extends GameObj{
    public ShellObj(int x, int y, double speed, Image img) {
        super(x, y, speed, img);
    }

    public ShellObj() {
    }

    public ShellObj(Image img, int x, int y, int width, int height, double speed, GamePlane frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        if(img != null) {
            // 使用 drawImage 的缩放功能
            gImage.drawImage(img, x, y, width, height, null);
            y-=speed;
            if(y<0)
            {
                this.x=-100;
                this.y=100;
                GameUtils.removeList.add(this);
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    @Override
    public Image getImg() {
        return super.getImg();
    }
}

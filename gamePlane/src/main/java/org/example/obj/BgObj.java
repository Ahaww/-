package org.example.obj;

import java.awt.*;

public class BgObj extends GameObj {

    public BgObj() {
        super();
    }

    public BgObj(Image img, int x, int y, double speed) {
        super(x, y, speed, img);
    }

    @Override
    public void paintSelf(Graphics gImage) {

        gImage.drawImage(img, x, y, null);

        gImage.drawImage(img, x, y - img.getHeight(null), null);

        y += speed;

        // 如果图片滚动超出屏幕，重置位置
        if(y >= 0) {
            y = -1500;
        }
    }
}

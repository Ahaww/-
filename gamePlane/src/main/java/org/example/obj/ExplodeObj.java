package org.example.obj;

import org.example.GamePlane;
import org.example.utils.GameUtils;

import java.awt.*;

public class ExplodeObj extends GameObj{
    static Image[] pic = new Image[16];
    int explodeCount=0;

    static {
        for (int i = 0; i < pic.length; i++) {
            pic[i]=Toolkit.getDefaultToolkit().getImage("img/boob.png");
        }
    }

    public ExplodeObj(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics gImage) {

        if(explodeCount<16)
        {
            img=pic[explodeCount];
            gImage.drawImage(img, x, y, 50, 50, null);
            explodeCount++;
        }

    }

}

package org.example.obj;

import org.example.GamePlane;
import org.example.utils.GameUtils;

import java.awt.*;

public class EnemyObj extends GameObj{

    @Override
    public Image getImg() {
        return super.getImg();
    }

    public EnemyObj(int x, int y, double speed, Image img) {
        super(x, y, speed, img);
    }

    public EnemyObj() {
    }

    public EnemyObj(Image img, int x, int y, int width, int height, double speed, GamePlane frame) {
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
                this.x=-200;
                this.y=200;
                GameUtils.removeList.add(this);
            }
            for(ShellObj shellObj : GameUtils.shellObjList)
            {
                if(this.getRec().intersects(shellObj.getRec()))
                {
                    ExplodeObj explodeObj = new ExplodeObj(x,y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    shellObj.setX(-100);
                    shellObj.setY(100);
                    this.x=-200;
                    this.y=200;
                    GameUtils.removeList.add(this);
                    GameUtils.removeList.add(shellObj);
                    GamePlane.score+=1;
                }
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}

package org.example.obj;

import org.example.GamePlane;
import org.example.utils.GameUtils;

import java.awt.*;

public class BossObj extends GameObj{
    int life=10;
    public BossObj() {
        super();
    }

    public BossObj(Image img, int x, int y, int width, int height, double speed, GamePlane frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        if(img != null) {
            gImage.drawImage(img, x, y, width, height, null);
            if(x>550||x<-50) {
                speed=-speed;
            }
            x += speed;
            for(ShellObj shellObj: GameUtils.shellObjList)
            {
                if(this.getRec().intersects(shellObj.getRec()))
                {
                    life--;
                    shellObj.setX(-100);
                    shellObj.setY(100);
                    GameUtils.removeList.add(shellObj);

                }
                if(life==0)
                {
                    life--;
                    GamePlane.score=GamePlane.score+100;
                    GamePlane.state=4;
                }
            }

        }
        gImage.setColor(Color.white);
        gImage.fillRect(20,40,100,10);
        gImage.setColor(Color.red);
        gImage.fillRect(20,40,life*10,10);

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    @Override
    public Image getImg() {
        return super.getImg();
    }

    public BossObj(int x, int y, double speed, Image img) {
        super(x, y, speed, img);
    }
}

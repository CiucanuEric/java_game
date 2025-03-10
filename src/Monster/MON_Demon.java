package Monster;

import Entity.Entity;
import main.GamePanel;

import java.awt.*;

public class MON_Demon extends Entity {
    public MON_Demon(GamePanel gp) {
        super(gp);
        name= "Demon";
        speed=1;
        maxHealth=30;
        currentHealth=maxHealth;
        solidArea=new Rectangle(0,0,48,48);
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        getImage();
        type=2;
    }
    public void getImage()
    {
        up1=setup("/monster/Demon");
        up2=setup("/monster/Demon");
        down1=setup("/monster/Demon");
        down2=setup("/monster/Demon");
        right1=setup("/monster/Demon");
        right2=setup("/monster/Demon");
        left1=setup("/monster/Demon");
        left2=setup("/monster/Demon");
    }
    public void setAction()
    {
        int i= random.nextInt(100)+1; //pick up a number from 1 to 100
        if(actionLockCounter==0) {
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "right";
            }
            if (i > 75 && i <= 100) {
                direction = "left";
            }
            actionLockCounter=120;
        }
        actionLockCounter--;
    }
}

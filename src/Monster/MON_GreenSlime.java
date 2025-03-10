package Monster;

import Entity.Entity;
import main.GamePanel;

import java.awt.*;

public class MON_GreenSlime extends Entity{

    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        name= "Green Slime";
        speed=1;
        maxHealth=30;
        currentHealth=maxHealth;
        solidArea=new Rectangle(3,18,42,30);
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        getImage();
        type=2;
    }
    public void getImage()
    {
        up1=setup("/monster/greenslime_down_1");
        up2=setup("/monster/greenslime_down_2");
        down1=setup("/monster/greenslime_down_1");
        down2=setup("/monster/greenslime_down_2");
        right1=setup("/monster/greenslime_down_1");
        right2=setup("/monster/greenslime_down_2");
        left1=setup("/monster/greenslime_down_1");
        left2=setup("/monster/greenslime_down_2");
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

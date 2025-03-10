package Entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Oldman extends Entity{
    public NPC_Oldman(GamePanel gp)
    {
        super(gp);

        direction= "down";
        speed = 1;
        getImage();
        solidArea=new Rectangle(0,0,48,48);

    }
    public void getImage()
    {
        left1=setup("/npc/oldman_left_1");
        left2=setup("/npc/oldman_left_2");
        right1=setup("/npc/oldman_right_1");
        right2=setup("/npc/oldman_right_2");
        down1=setup("/npc/oldman_down_1");
        down2=setup("/npc/oldman_down_2");
        up1=setup("/npc/oldman_up_1");
        up2=setup("/npc/oldman_up_2");
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

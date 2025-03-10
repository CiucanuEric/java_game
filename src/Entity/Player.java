package Entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.System.exit;

public class Player extends Entity{
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int InvicibilityFrame=120;

    public Player(GamePanel gp,KeyHandler keyH)
    {
        super(gp);
        this.keyH=keyH;
        screenX=gp.screenWidth/2-(32);
        screenY=gp.screenHeight/2-(32);
        solidArea=new Rectangle();
        solidArea.x=20;
        solidArea.y=39;
        solidArea.width=21;
        solidArea.height=25;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collisionOn=true;
        setDefaultValues();
        getPlayerImage();
        maxHealth=100;
        currentHealth=maxHealth;

    }
    public void setDefaultValues()
    {
        worldX = gp.tileSize * 11;
        worldY = gp.tileSize * 23;
        speed = 4;
        getPlayerImage();
        direction= "right";
    }
    public void update()
    {
        if(keyH.upPressed == true || keyH.downPressed==true || keyH.rightPressed==true || keyH.leftPressed==true)
        {
            if(keyH.upPressed==true)
            {
                direction= "up";
            }
            if(keyH.downPressed==true)
            {
                direction= "down";
            }
            if(keyH.rightPressed==true)
            {
                direction = "right";
            }
            if(keyH.leftPressed==true)
            {
                direction = "left";
            }
            //CHECK TILE COLLISION

            collisionOn=false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION

            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //CHECK EVENT COLLISION

            int eventIndex=gp.cChecker.checkEvent(this,true);
            TriggerEvent(eventIndex);
            //CHECK NPC COLLISION
            int npcIndex=gp.cChecker.checkEntity(this, gp.npc,gp.maxNPC);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex=gp.cChecker.checkEntity(this,gp.monster,gp.maxMonster);
                //DEAL DAMAGE METHOD
                contactMonster(monsterIndex);


            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn==false) {
                if (direction == "up") {
                    worldY -= speed;
                }
                if (direction == "down"){
                    worldY += speed;
                }
                if(direction=="left") {
                    worldX -= speed;
                }
                if(direction=="right") {
                    worldX += speed;
                }

            }
            spriteCounter++;
            if(spriteCounter>20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter=0;
            }
        }else
        {
            spriteNum=1;
            spriteCounter=0;
        }
    }
    public void pickUpObject(int index)
    {
        if(index!=11) {
            if (gp.obj[gp.worldIndex][index].name == "Chest")
            {
                gp.playSE(2);
                maxHealth+=20;
                currentHealth+=20;
                gp.obj[gp.worldIndex][index]=null;
                gp.UI.showMessage("+20 HP");
                gp.test.insertInt(currentHealth,"CURRENT_HP");
                gp.test.insertInt(maxHealth,"MAX_HP");
            }
            else if(gp.obj[gp.worldIndex][index].name == "Key")
            {
                gp.playSE(1);
                currentHealth-=40;
                gp.obj[gp.worldIndex][index]=null;
                gp.UI.showMessage("-40 HP");
            }
            else if(gp.obj[gp.worldIndex][index].name == "Boots")
            {
                gp.playSE(3);
                speed+=2;
                gp.obj[gp.worldIndex][index]=null;
                gp.UI.showMessage("+2 Speed");
            }
        }
    }
    public void interactNPC(int i)
    {
        if(i!=11)
        {
            System.out.println("You are hitting an NPC!");
        }
    }
    public void contactMonster(int i)
    {
        if (i != 11) {
            if(InvicibilityFrame<=0) {
                switch (gp.monster[gp.worldIndex][i].name) {
                    case ("Green Slime"):
                        currentHealth -= 20;
                        gp.playSE(3);
                        break;
                    case("Demon"):
                        currentHealth -= 100;
                        gp.playSE(3);
                        break;
                }
                InvicibilityFrame=60;
                System.out.println("I ran into it");
                gp.test.insertInt(currentHealth,"CURRENT_HP");
                gp.test.insertInt(maxHealth,"MAX_HP");
            }
            Death();
        }
    }
    public void TriggerEvent(int index)
    {
        if(index!=11)
        {
            switch(gp.eventArray[gp.worldIndex][index].name)
            {
                case("Teleport"):
                    gp.eventArray[gp.worldIndex][index].EventTrigger(this);
                    break;
                case("MapChanger"):
                    int TempWorldIndex=gp.worldIndex;
                    gp.eventArray[gp.worldIndex][index].EventTrigger(this);
                    gp.eventArray[TempWorldIndex][index]=null;
                    break;
            }
        }
    }
    public void Death()
    {
        if(currentHealth<=0)
        {
            System.out.println("You died");
            exit(0);
        }
    }


    public void draw(Graphics2D g2)
    {

        BufferedImage image = null;
        switch(direction)
        {
            case "left":
                if(spriteNum==1) {
                    image = left1;
                }
                if(spriteNum==2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum==1) {
                    image = right1;
                }
                if(spriteNum==2) {
                    image = right2;
                }
                break;
            case "up":
                if(spriteNum==1) {
                    image = left1;
                }
                if(spriteNum==2) {
                    image = left2;
                }
                break;
            case "down":
                if(spriteNum==1) {
                    image = right1;
                }
                if(spriteNum==2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY,null);
        g2.setColor(Color.white);
    }
    public void getPlayerImage()
    {
        left1=setup("/player/player-left-02");
        left2=setup("/player/player-left-01");
        right1=setup("/player/player-right-02");
        right2=setup("/player/player-right-01");
    }

}


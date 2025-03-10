package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Entity {
    public String name="";
    public Random random = new Random();
    public GamePanel gp;
    public int maxHealth;
    public int currentHealth;
    public int worldX, worldY;
    public int speed;
    public BufferedImage left1,left2,right1,right2,up1,up2,down1,down2;
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn=false;
    public int actionLockCounter=0;
    public int type; // 0 = player // 1 = npc // 2 = monster
    public boolean contactPlayer;
    public void setAction(){
    }
    public void update(){
        setAction();

        collisionOn=false;
        gp.cChecker.checkTile(this); //Entity verifica coliziunea cu Tile-uri
        gp.cChecker.checkObject(this,false); //Entity verifica coliziunea cu Obiectele
        contactPlayer= gp.cChecker.checkPlayer(this); //Entity verifica coliziunea cu Player-ul
        if(this.type==2&&contactPlayer)
        {
            if(gp.player.InvicibilityFrame<=0)
            {
                switch(this.name) {
                    case ("Green Slime"):
                        gp.player.currentHealth -= 20;
                        break;
                    case("Demon"):
                        gp.player.currentHealth -=100;
                }
                System.out.println("I dealt damage");
                gp.player.InvicibilityFrame=120;
                gp.test.insertInt(gp.player.currentHealth,"CURRENT_HP");
                gp.test.insertInt(gp.player.maxHealth,"MAX_HP");
            }
            gp.player.Death();
        }
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
    }
    public Entity(GamePanel gp)
    {
        this.gp=gp;
    }
    public BufferedImage setup(String filePath)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image=null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(filePath + ".png"));
            image = uTool.scaleImage(image,64,64);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;

    }
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        int screenX= worldX-gp.player.worldX + gp.player.screenX;
        int screenY= worldY-gp.player.worldY + gp.player.screenY;
        if(worldX>=gp.player.worldX - gp.player.screenX -gp.tileSize*2 && worldX<=gp.player.worldX + gp.player.screenX +gp.tileSize*2 && worldY>=gp.player.worldY - gp.player.screenY -gp.tileSize*2&& worldY<=gp.player.worldY + gp.player.screenY+gp.tileSize*2)
        {
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
                        image = up1;
                    }
                    if(spriteNum==2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum==1) {
                        image = down1;
                    }
                    if(spriteNum==2) {
                        image = down2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}

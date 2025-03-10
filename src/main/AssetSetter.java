package main;

import Entity.NPC_Oldman;
import Monster.MON_Demon;
import Monster.MON_GreenSlime;
import event.Event_MapChanger;
import event.Event_Teleport;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;


public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp)
    {
        this.gp=gp;
    }
    public void setObject()
    {
        //WORLD 0
        gp.obj[0][0] = new OBJ_Boots(gp);
        gp.obj[0][0].worldX = 4*gp.tileSize;
        gp.obj[0][0].worldY = 6*gp.tileSize;
        gp.obj[0][2] = new OBJ_Chest(gp);
        gp.obj[0][2].worldX = 25*gp.tileSize;
        gp.obj[0][2].worldY = 10*gp.tileSize;
        gp.obj[0][1] = new OBJ_Chest(gp);
        gp.obj[0][1].worldX = 31*gp.tileSize;
        gp.obj[0][1].worldY = 5*gp.tileSize;
        gp.obj[0][3] = new OBJ_Door(gp);
        gp.obj[0][3].worldX = 26*gp.tileSize;
        gp.obj[0][3].worldY = 22*gp.tileSize;

        //WORLD 1
        gp.obj[1][0] = new OBJ_Chest(gp);
        gp.obj[1][0].worldX = 39*gp.tileSize;
        gp.obj[1][0].worldY = 21*gp.tileSize;
        gp.obj[1][1] = new OBJ_Chest(gp);
        gp.obj[1][1].worldX = 138*gp.tileSize;
        gp.obj[1][1].worldY = 36*gp.tileSize;
        gp.obj[1][2] = new OBJ_Chest(gp);
        gp.obj[1][2].worldX = 175*gp.tileSize;
        gp.obj[1][2].worldY = 59*gp.tileSize;

        //WORLD 2

        gp.obj[2][0] = new OBJ_Chest(gp);
        gp.obj[2][0].worldX = 35*gp.tileSize;
        gp.obj[2][0].worldY = 5*gp.tileSize;
    }
    public void setNPC()
    {
        //WORLD 0
        gp.npc[0][0] = new NPC_Oldman(gp);
        gp.npc[0][0].worldX= gp.tileSize*26;
        gp.npc[0][0].worldY=gp.tileSize*24;

        //WORLD 1
        gp.npc[1][0] = new NPC_Oldman(gp);
        gp.npc[1][0].worldX= gp.tileSize*68;
        gp.npc[1][0].worldY=gp.tileSize*73;
    }

    public void setEvent()
    {
        //WORLD 0
        gp.eventArray[0][0]= new Event_MapChanger(1,"/maps/world02.png",200,128,19,110);
        gp.eventArray[0][0].setEventLocation(40*gp.tileSize,0*gp.tileSize,7*gp.tileSize,3*gp.tileSize);

        //WORLD 1
        gp.eventArray[1][0]= new Event_Teleport(99*gp.tileSize,52*gp.tileSize);
        gp.eventArray[1][0].setEventLocation(19*gp.tileSize,18*gp.tileSize,6*gp.tileSize,5*gp.tileSize);
        gp.eventArray[1][1]= new Event_MapChanger(2,"/maps/world03.png",50,28,1,23);
        gp.eventArray[1][1].setEventLocation(187*gp.tileSize,12*gp.tileSize,13*gp.tileSize,7*gp.tileSize);
    }
    public void setMonster()
    {   //WORLD 0
        gp.monster[0][0]= new MON_GreenSlime(gp);
        gp.monster[0][0].worldX=gp.tileSize*41;
        gp.monster[0][0].worldY=gp.tileSize*7;
        gp.monster[0][1]= new MON_GreenSlime(gp);
        gp.monster[0][1].worldX=gp.tileSize*39;
        gp.monster[0][1].worldY=gp.tileSize*16;
        gp.monster[0][2]= new MON_GreenSlime(gp);
        gp.monster[0][2].worldX=gp.tileSize*20;
        gp.monster[0][2].worldY=gp.tileSize*12;
        gp.monster[0][3]= new MON_GreenSlime(gp);
        gp.monster[0][3].worldX=gp.tileSize*23;
        gp.monster[0][3].worldY=gp.tileSize*4;
        gp.monster[0][4]= new MON_GreenSlime(gp);
        gp.monster[0][4].worldX=gp.tileSize*12;
        gp.monster[0][4].worldY=gp.tileSize*12;
        gp.monster[0][5]= new MON_GreenSlime(gp);
        gp.monster[0][5].worldX=gp.tileSize*20;
        gp.monster[0][5].worldY=gp.tileSize*18;
        //WORLD 1
        gp.monster[1][0]= new MON_GreenSlime(gp);
        gp.monster[1][0].worldX=gp.tileSize*20;
        gp.monster[1][0].worldY=gp.tileSize*101;
        gp.monster[1][1]= new MON_GreenSlime(gp);
        gp.monster[1][1].worldX=gp.tileSize*95;
        gp.monster[1][1].worldY=gp.tileSize*82;
        gp.monster[1][2]= new MON_GreenSlime(gp);
        gp.monster[1][2].worldX=gp.tileSize*168;
        gp.monster[1][2].worldY=gp.tileSize*85;
        gp.monster[1][3]= new MON_GreenSlime(gp);
        gp.monster[1][3].worldX=gp.tileSize*163;
        gp.monster[1][3].worldY=gp.tileSize*16;
        gp.monster[1][4]= new MON_GreenSlime(gp);
        gp.monster[1][4].worldX=gp.tileSize*111;
        gp.monster[1][4].worldY=gp.tileSize*29;
        gp.monster[1][5]= new MON_GreenSlime(gp);
        gp.monster[1][5].worldX=gp.tileSize*20;
        gp.monster[1][5].worldY=gp.tileSize*30;
        //WOLRD 2
        gp.monster[2][2]= new MON_Demon(gp);
        gp.monster[2][2].worldX=gp.tileSize*45;
        gp.monster[2][2].worldY=gp.tileSize*12;
    }

}

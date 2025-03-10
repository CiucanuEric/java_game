package main;
import Entity.Entity;
import tile.TileManager;
public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp)
    {
        this.gp=gp;
    }
    public void checkTile(Entity entity)
    {
        int entityLeftWorldX=entity.worldX+entity.solidArea.x;
        int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY=entity.worldY+entity.solidArea.y;
        int entityBottomWorldY=entity.worldY +entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.tileSize;
        int entityRightCol=entityRightWorldX/gp.tileSize;
        int entityTopRow=entityTopWorldY/gp.tileSize;
        int entityBottomRow=entityBottomWorldY/gp.tileSize;

        int tileNum1,tileNum2;
        try {
            switch (entity.direction) {
                case "up":
                    entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                    break;
                case "down":
                    entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                    break;
                case "left":
                    entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                    break;
                case "right":
                    entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                    break;
            }
        }catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("---------CollisionChecker: checkTile()---------");
            System.out.println("Left:" + entityLeftCol);
            System.out.println("Right:" + entityRightCol);
            System.out.println("Up:" + entityTopRow);
            System.out.println("Down:" + entityBottomRow);
        }
    }
    public int checkObject(Entity entity,boolean Player)
    {
        int index=11;
        for(int i=0;i<gp.maxObject;i++)
        {
            if(gp.obj[gp.worldIndex][i]!=null)
            {
                // Get entity's solid area position
                entity.solidArea.x+=entity.worldX;
                entity.solidArea.y+=entity.worldY;
                // Get the object's solid area position
                gp.obj[gp.worldIndex][i].solidArea.x+=gp.obj[gp.worldIndex][i].worldX;
                gp.obj[gp.worldIndex][i].solidArea.y+=gp.obj[gp.worldIndex][i].worldY;

                switch(entity.direction)
                {
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.worldIndex][i].solidArea)) {
                            if (gp.obj[gp.worldIndex][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (Player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.worldIndex][i].solidArea)) {
                            if (gp.obj[gp.worldIndex][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (Player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.worldIndex][i].solidArea)) {
                            if (gp.obj[gp.worldIndex][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (Player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[gp.worldIndex][i].solidArea)) {
                            if (gp.obj[gp.worldIndex][i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (Player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.obj[gp.worldIndex][i].solidArea.x=gp.obj[gp.worldIndex][i].solidAreaDefaultX;
                gp.obj[gp.worldIndex][i].solidArea.y=gp.obj[gp.worldIndex][i].solidAreaDefaultY;

            }
        }
        return index;
    }
    public int checkEvent(Entity entity,boolean Player)
    {
        int index=11;
        for(int i=0;i<gp.maxEvent;i++)
        {
            if(gp.eventArray[gp.worldIndex][i]!=null)
            {
                // Get entity's solid area position
                entity.solidArea.x+=entity.worldX;
                entity.solidArea.y+=entity.worldY;


                gp.eventArray[gp.worldIndex][i].solidArea.x+=gp.eventArray[gp.worldIndex][i].worldX;
                gp.eventArray[gp.worldIndex][i].solidArea.y+=gp.eventArray[gp.worldIndex][i].worldY;

                switch(entity.direction)
                {
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.eventArray[gp.worldIndex][i].solidArea)) {
                            if (Player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.eventArray[gp.worldIndex][i].solidArea)) {
                            if (Player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.eventArray[gp.worldIndex][i].solidArea)) {
                            if (Player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.eventArray[gp.worldIndex][i].solidArea)) {
                            if (Player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.eventArray[gp.worldIndex][i].solidArea.x=gp.eventArray[gp.worldIndex][i].solidAreaDefaultX;
                gp.eventArray[gp.worldIndex][i].solidArea.y=gp.eventArray[gp.worldIndex][i].solidAreaDefaultY;

            }
        }
        return index;
    }
    public int checkEntity(Entity entity,Entity [][]target,int maxLength)
    {
        int index=11;
        for(int i=0;i<maxLength;i++)
        {
            if(target[gp.worldIndex][i]!=null)
            {
                // Get entity's solid area position
                entity.solidArea.x+=entity.worldX;
                entity.solidArea.y+=entity.worldY;

                // Get the NPC's solid area position

                target[gp.worldIndex][i].solidArea.x+=target[gp.worldIndex][i].worldX;
                target[gp.worldIndex][i].solidArea.y+=target[gp.worldIndex][i].worldY;

                switch(entity.direction)
                {
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(target[gp.worldIndex][i].solidArea)) {
                                entity.collisionOn = true;
                                index=i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(target[gp.worldIndex][i].solidArea)) {
                                entity.collisionOn = true;
                            index=i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(target[gp.worldIndex][i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(target[gp.worldIndex][i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                target[gp.worldIndex][i].solidArea.x=target[gp.worldIndex][i].solidAreaDefaultX;
                target[gp.worldIndex][i].solidArea.y=target[gp.worldIndex][i].solidAreaDefaultY;

            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity)
    {
        boolean contactPlayer=false;
        entity.solidArea.x+=entity.worldX;
        entity.solidArea.y+=entity.worldY;

        // Get the NPC's solid area position

        gp.player.solidArea.x+=gp.player.worldX;
        gp.player.solidArea.y+=gp.player.worldY;

        switch(entity.direction)
        {
            case "up":
                entity.solidArea.y-=entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    contactPlayer=true;
                }
                break;
            case "down":
                entity.solidArea.y+=entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    contactPlayer=true;
                }
                break;
            case "left":
                entity.solidArea.x-=entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    contactPlayer=true;
                }
                break;
            case "right":
                entity.solidArea.x+=entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    contactPlayer=true;
                }
                break;
        }
        entity.solidArea.x=entity.solidAreaDefaultX;
        entity.solidArea.y=entity.solidAreaDefaultY;
        gp.player.solidArea.x=gp.player.solidAreaDefaultX;
        gp.player.solidArea.y=gp.player.solidAreaDefaultY;
        return contactPlayer;
    }

}

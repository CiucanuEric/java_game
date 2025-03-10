package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Map world;
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManager(GamePanel gp,Map map)
    {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum=new int [map.maxWorldCol][map.maxWorldRow];
        getTileImage();
        loadMap(map);
    }
    public void getTileImage()
    {
            setup(0,"grass",false);
            setup(1,"wall",true);
            setup(2,"water",true);
            setup(3,"earth",false);
            setup(4,"tree",true);
            setup(5,"sand",false);
    }

    public void setup(int index,String imageName,boolean collision)
    {
        UtilityTool uTool=new UtilityTool();

        try
        {
            tile[index]=new Tile();
            tile[index].image=ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName +".png"));
            tile[index].image= uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].collision=collision;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void loadMap(Map map)
    {
        this.world=map;
        gp.worldIndex=map.worldIndex;
        gp.maxWorldRow=map.maxWorldRow;
        gp.maxWorldCol=map.maxWorldCol;
        int col=0;
        int row=0;
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        try
        {
            BufferedImage mapImage = ImageIO.read(getClass().getResourceAsStream(map.filePath));



            while(col <gp.maxWorldCol && row <gp.maxWorldRow)
            {
                while(col <gp.maxWorldCol)
                {
                    switch(Integer.toHexString(mapImage.getRGB(col,row)))
                    {
                        case "ff6abe30":
                            mapTileNum[col][row]=0;
                            break;
                        case "ff696a6a":
                            mapTileNum[col][row]=1;
                            break;
                        case "ff5fcde4":
                            mapTileNum[col][row]=2;
                            break;
                        case "ff8f563b":
                            mapTileNum[col][row]=3;
                            break;
                        case "ff4b692f":
                            mapTileNum[col][row]=4;
                            break;
                        case "ffeec39a":
                            mapTileNum[col][row]=5;
                            break;
                        default:
                            mapTileNum[col][row]=0;
                            break;
                    }
                    col++;

                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
        } catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("--------TileManager: loadMap()--------");
            System.out.println(col + " " + row);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;
        while(worldCol <gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum=mapTileNum[worldCol][worldRow];
            int worldX = worldCol *gp.tileSize;
            int worldY = worldRow *gp.tileSize;
            int screenX= worldX-gp.player.worldX + gp.player.screenX;
            int screenY= worldY-gp.player.worldY + gp.player.screenY;
            if(worldX>=gp.player.worldX - gp.player.screenX -gp.tileSize*2 && worldX<=gp.player.worldX + gp.player.screenX +gp.tileSize*2 && worldY>=gp.player.worldY - gp.player.screenY -gp.tileSize*2&& worldY<=gp.player.worldY + gp.player.screenY+gp.tileSize*2) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;
            if(worldCol==gp.maxWorldCol)
            {
                worldRow++;
                worldCol=0;
            }
        }
    }




}

package event;

import java.awt.*;
import Entity.Entity;

public abstract class Event {
    public String name;
    public int worldX,worldY;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public void setEventLocation(int x,int y,int sizeWidth,int sizeHeight)
    {
        worldX=x;
        worldY=y;
        solidArea = new Rectangle(0,0,sizeWidth,sizeHeight);
    }

    public void EventTrigger(Entity entity)
    {
    }

}

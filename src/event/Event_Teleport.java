package event;
import Entity.Entity;

//Event pentru teleportarea Player-ului pe harta
public class Event_Teleport extends Event{
     int TeleportX,TeleportY;
     public Event_Teleport(int TeleportX, int TeleportY)
     {
         this.TeleportX=TeleportX;
         this.TeleportY=TeleportY;
         name="Teleport";

     }
     public void EventTrigger(Entity entity)
     {
        System.out.println("I work");
        entity.worldX=TeleportX;
        entity.worldY=TeleportY;
     }
}

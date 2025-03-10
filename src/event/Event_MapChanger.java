package event;
import Entity.Entity;
import tile.Map;
import tile.TileManager;
import main.GamePanel;

//Event-ul pentru trecerea de la un nivel la altul
public class Event_MapChanger extends Event{
    Map world;
    Event_Teleport PlayerTeleport;

    public Event_MapChanger(int worldIndex, String filePath,int maxWorldCol,int maxWorldRow,int PlayerTeleportX,int PlayerTeleportY)
    {
        name="MapChanger";
        world=new Map(worldIndex,maxWorldCol,maxWorldRow,filePath);
        PlayerTeleport=new Event_Teleport(PlayerTeleportX*48,PlayerTeleportY*48);
    }
    public void EventTrigger(Entity entity)
    {
        entity.gp.tileM.loadMap(world);
        PlayerTeleport.EventTrigger(entity);
    }



}

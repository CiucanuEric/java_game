package tile;

public class Map {
    public String filePath;
    public int maxWorldCol;
    public int maxWorldRow;
    public int worldIndex;

    public Map(int worldIndex,int maxWorldCol,int maxWorldRow,String filePath)
    {
        this.worldIndex=worldIndex;
        this.maxWorldCol=maxWorldCol;
        this.maxWorldRow=maxWorldRow;
        this.filePath=filePath;
    }


}

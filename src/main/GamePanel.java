package main;

import Entity.Player;
import Entity.Entity;
import object.SuperObject;
import tile.Map;
import tile.TileManager;
import event.Event;
import javax.swing.JPanel;
import java.awt.*;
import database.DatabaseManager;

public class GamePanel extends JPanel implements Runnable
{
    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize= originalTileSize * scale; //Marimea un Tile
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth= tileSize *maxScreenCol;
    public final int screenHeight= tileSize* maxScreenRow;

    //DATABASE
    public DatabaseManager test=new DatabaseManager(); //Baza de date

    //WORLD SETTINGS
    public int maxWorldCol; //Lungimea hartii pe latime
    public int maxWorldRow; //Lungimea hartii pe lungime

    public int worldIndex; //Lumea de joc curenta
    // FPS
    int FPS=60;
    Map world01 = new Map(0,50,28,"/maps/world01.png");
    public TileManager tileM = new TileManager(this,world01); //Prima harta loadata
    public UI UI= new UI(this); //Display-ul pentru Viata si coordonate
    KeyHandler keyH= new KeyHandler(); //Manager-ul pentru Tastatura
    Sound music = new Sound(); //Muzica jocului
    Sound se = new Sound(); //Sunetul Jocului
    public CollisionChecker cChecker=new CollisionChecker(this); //Verificator de Checker
    public AssetSetter aSetter=new AssetSetter(this); //Clasa care se ocupa pentru punerea pe harti a obiectelor, monstriilor ,npc-urilor si a Event-urilor
    Thread gameThread;

    //ENTITY, OBJECT, MONSTER AND EVENT

    public int maxObject=10; //Maximul de obiecte pe lume
    public int maxNPC=10; //Maximul de NPC pe lume
    public int maxEvent=10; //Maximul de Event-uri pe lume
    public int maxMonster=10; //Maximul de Monstrii pe lume
    public Event eventArray[][]= new Event[3][maxEvent]; //Vectorul pentru event-uri (Teleport, Schimbare de Harta)
    public Player player= new Player(this,keyH); //Instantierea de controale ale jucatorului
    public SuperObject obj[][]=new SuperObject[3][maxObject]; //Vectorul pentru obiecte
    public Entity npc[][]=new Entity[3][maxNPC]; //Vectorul pentru NPC-uri
    public Entity monster[][]=new Entity[3][maxMonster]; //Vectorul pentru Monstrii


    double drawInterval= 1000000000/FPS;
    double nextDrawTime = System.nanoTime() + drawInterval;
    long timer = 0;
    int drawCount=0;
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    // Punerea de monstrii
    public void setupGame()
    {

        aSetter.setObject();
        aSetter.setEvent();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
    }

    public void startGameThread()
    {
        gameThread=new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        while(gameThread!=null)
        {
            //1 UPDATE: update information such as character positions
            update();
            //2 DRAW: draw the screen with the updated information
            repaint();
//            timer+=drawInterval;
//            if(timer>=1000000000)
//            {
//                System.out.println(drawCount);
//                timer=0;
//                drawCount=0;
//            }
//            drawCount++;
            try {
                double remainingTime= nextDrawTime- System.nanoTime();
                remainingTime=remainingTime/10000000;
                if(remainingTime<0)
                {
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime +=drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update()
    {
        player.update();

        for(int i=0;i<maxNPC;i++)
        {
            if(npc[worldIndex][i] !=null)
            {
                npc[worldIndex][i].update();
            }
        }
        for(int i=0;i<maxMonster;i++)
        {
            if(monster[worldIndex][i] !=null)
            {
                monster[worldIndex][i].update();
            }
        }
        MusicKeyChecker();
        player.InvicibilityFrame-=1;
    }
    public void paintComponent(Graphics g)  //Randeaza ecranul
    {
        super.paintComponent(g);

        // DEBUG
        long startTime=0;
        if(keyH.checkDrawTime==true)
        {
            startTime=System.nanoTime();
        }


        Graphics2D g2=(Graphics2D)g;
        tileM.draw(g2);

        //OBJECT ARRAY
        for(int i=0;i<maxObject;i++) {
            if(obj[worldIndex][i]!=null)
            {
                obj[worldIndex][i].draw(g2,this);
            }
        }
        for(int i=0;i<maxNPC;i++)
        {
            if(npc[worldIndex][i]!=null)
            {
                npc[worldIndex][i].draw(g2);
            }
        }
        for(int i=0;i<maxMonster;i++)
        {
            if(monster[worldIndex][i]!=null)
            {
                monster[worldIndex][i].draw(g2);
            }
        }
        player.draw(g2);
        UI.draw(g2);
        long endTime;
        long passed;
        if(keyH.checkDrawTime==true) {
            endTime = System.nanoTime();
            passed = endTime - startTime;

            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, screenHeight - 10);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose();
    }
    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    //Opreste Muzica
    public void stopMusic()
    {
        music.stop();
    }
    //Verifica daca F2 este apasat pentru a oprii muzica
    public void MusicKeyChecker()
    {
        if(keyH.stopMusic==true)
        {
            stopMusic();
        }
    }
    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }


}

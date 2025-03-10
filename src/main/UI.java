package main;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI
{
    Font arial_20;
    GamePanel gp;
    BufferedImage keyImage;

    double playTime;
    DecimalFormat dFormat=new DecimalFormat("#0.00");


    public boolean messageOn= false;
    public String message= "";
    int messageCounter;
    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_20=new Font("Arial",Font.PLAIN,20);
        try {
            keyImage= ImageIO.read(getClass().getResourceAsStream("/UI/heart.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showMessage(String text)
    {
        messageCounter=0;
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2)
    {
        g2.setFont(arial_20);
        g2.setColor(Color.white);
        g2.drawString(Integer.toString((gp.player.worldX+32)/48) + " " + Integer.toString((gp.player.worldY+32)/48),10,30);
        g2.drawString(":" + Integer.toString(gp.player.currentHealth) + "/" + Integer.toString(gp.player.maxHealth),55,55);
        g2.drawImage(keyImage,gp.tileSize/2,36,gp.tileSize/2,gp.tileSize/2,null);

        //TIME

        playTime+=(double)1/60;
        g2.drawString("Time: "+dFormat.format(playTime), gp.screenWidth-200,50);

        //NOTIFICATION
        if(messageOn==true)
        {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message,10,100);
            messageCounter++;
            if(messageCounter>120)
            {
                messageCounter=0;
                messageOn=false;
            }
        }

    }
}

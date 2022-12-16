package com.hugo.game;

import com.hugo.engine.AbstractGame;
import com.hugo.engine.GameContainer;
import com.hugo.engine.Renderer;
import com.hugo.engine.audio.SoundClip;
import com.hugo.engine.gfx.Image;
import com.hugo.engine.gfx.ImageTile;
import com.hugo.engine.gfx.Light;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame
{
    private Image image;
    private ImageTile image2;
    private Light light;

    private SoundClip clip;

    public GameManager()
    {
        image = new Image("/test3.png");
        image.setLightBlock(Light.FULL);
        image.setAlpha(true);
        image2 = new ImageTile("/bg.png", 16, 16);
        image2.setAlpha(true);

        light = new Light(100, 0xff00ff00); // Hello

        clip = new SoundClip("/audio/test.wav");
        clip.setVolume(-20);
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
        if (gc.getInput().isKeyDown(KeyEvent.VK_A))
        {
            clip.play();
        }

        temp += dt * 10;

        if (temp > 3)
        {
            temp = 0;
        }
    }

    float temp = 0;

    @Override
    public void render(GameContainer gc, Renderer r)
    {

        //r.drawImageTile(image, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 8, (int)temp, 0);
        r.setzDepth(0);
        r.drawImage(image2, 0, 0);
        r.drawImage(image, 100, 100);

        r.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());
        //r.setzDepth(0);
        //r.drawImage(image, 10, 10);
        //r.drawFillRect(gc.getInput().getMouseX() - 200, gc.getInput().getMouseY() - 200, 400, 400, 0xffffccff);
    }

    public static void main(String[] args)
    {
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}

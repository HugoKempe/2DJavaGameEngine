package com.hugo.game;

import com.hugo.engine.AbstractGame;
import com.hugo.engine.GameContainer;
import com.hugo.engine.Renderer;
import com.hugo.engine.audio.SoundClip;
import com.hugo.engine.gfx.Image;
import com.hugo.engine.gfx.ImageTile;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame
{
    private Image image;
    private ImageTile image2;

    private SoundClip clip;

    public GameManager()
    {
        image = new Image("/GIMP/NoAlpha_1.png");
        image2 = new ImageTile("/GIMP/NoAlpha_2.png", 16, 16);
        image2.setAlpha(true);

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
        r.setzDepth(1);
        r.drawImageTile(image2, gc.getInput().getMouseX(), gc.getInput().getMouseY(), 1, 1);
        r.setzDepth(0);
        r.drawImage(image, 10, 10);
        //r.drawFillRect(gc.getInput().getMouseX() - 200, gc.getInput().getMouseY() - 200, 400, 400, 0xffffccff);
    }

    public static void main(String[] args)
    {
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}

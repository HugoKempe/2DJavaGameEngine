package com.hugo.game;

import com.hugo.engine.AbstractGame;
import com.hugo.engine.GameContainer;
import com.hugo.engine.Renderer;
import com.hugo.engine.audio.SoundClip;
import com.hugo.engine.gfx.Image;
import com.hugo.engine.gfx.ImageTile;
import com.hugo.engine.gfx.Light;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameManager extends AbstractGame
{
    private int[] collision;
    private int levelW, levelH;
    private ArrayList<GameObject> objects = new ArrayList<>();

    public GameManager()
    {
        objects.add(new Player(2, 2));
        loadLevel("/level.png");
    }

    @Override
    public void init(GameContainer gc)
    {
        gc.getRenderer().setAmbientColor(-1);
    }

    @Override
    public void update(GameContainer gc, float dt)
    {
        for (int i = 0; i < objects.size(); i++)
        {
            objects.get(i).update(gc, dt);
            if (objects.get(i).isDead())
            {
                objects.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r)
    {
        for (int y = 0; y < levelH; y++)
        {
            for (int x = 0; x < levelW; x++)
            {
                if (collision[x + y * levelW] == 1)
                {
                    r.drawFillRect(x * 16, y * 16, 16, 16, 0xff0f0f0f);
                }
                else
                {
                    r.drawFillRect(x * 16, y * 16, 16, 16, 0xfff9f9f9);
                }
            }
        }

        for (GameObject object : objects)
        {
            object.render(gc, r);
        }
    }

    public void loadLevel(String path)
    {
        Image levelImage = new Image(path);

        levelW = levelImage.getW();
        levelH = levelImage.getH();
        collision = new int[levelW * levelH];

        for (int y = 0; y < levelImage.getH(); y++)
        {
            for (int x = 0; x < levelImage.getW(); x++)
            {
                if (levelImage.getP()[x + y * levelImage.getW()] == 0xff000000)
                {
                    collision[x + y * levelImage.getW()] = 1;
                }
                else
                {
                    collision[x + y * levelImage.getW()] = 0;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        GameContainer gc = new GameContainer(new GameManager());
        gc.setWidth(320);
        gc.setHeight(240);
        gc.setScale(3f);
        gc.start();
    }
}

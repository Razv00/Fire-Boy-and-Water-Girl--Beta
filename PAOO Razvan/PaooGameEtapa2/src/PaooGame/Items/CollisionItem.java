package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CollisionItem extends Item {
    private BufferedImage image;

    public CollisionItem(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
    }

    public void LoadImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void Update(){

    }

    @Override
    public int GetHeight(){
        return image.getHeight();
    }

    @Override
    public int GetWidth(){
        return image.getWidth();
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x , (int)y, width, height, null);
    }
}


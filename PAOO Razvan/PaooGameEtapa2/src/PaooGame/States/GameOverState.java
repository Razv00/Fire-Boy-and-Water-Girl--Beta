package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

public class GameOverState extends State{
    public GameOverState(RefLinks refLink) {
        super(refLink);
    }

    private Rectangle menuButton = new Rectangle(refLink.GetWidth() / 2 - 70, 400, 130, 50);
    private Rectangle playAgain = new Rectangle(refLink.GetWidth() / 2 - 70, 350, 130, 50);

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.gameOverScreen,0,0,null);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.draw(menuButton);
        //g2d.draw(playAgain);

        //g.drawImage(Assets.gameMenu,0,0,null);


    }
}

package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class NextLevelState extends State
{
    private Rectangle playButton = new Rectangle(refLink.GetWidth() / 2 - 70, 150, 130, 50);

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public NextLevelState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza.
        super(refLink);
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {   Graphics2D g2d = (Graphics2D) g;
        g2d.draw(playButton);

        g.drawImage(Assets.nextLevel,0,0,null);




    }
}

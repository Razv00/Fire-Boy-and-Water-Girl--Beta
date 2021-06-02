package PaooGame.States;


import PaooGame.Items.Hero;
import PaooGame.Items.Scor;
import PaooGame.Items.Water_Hero;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

import java.awt.*;

import static java.awt.Font.BOLD;

//import PaooGame.scene.media.MediaPlayer;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Water_Hero water_hero;
    private Map map;    /*!< Referinta catre harta curenta.*/
    private String scor ;



    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        map = new Map(refLink);


            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
            ///Construieste eroul
        hero = new Hero(refLink,100, 90);
        water_hero =  new Water_Hero(refLink , 50, 90);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {

            map.Update();
            hero.Update();
            water_hero.Update();
        }


    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
        hero.Draw(g);
        water_hero.Draw(g);

        Font font = new Font("Arial", BOLD,20);
        g.setFont(font);
        g.setColor(Color.GRAY);
        g.drawString("SCOR:"+String.valueOf(Scor.GetInstance().getScor()),20, 35);



    }


}


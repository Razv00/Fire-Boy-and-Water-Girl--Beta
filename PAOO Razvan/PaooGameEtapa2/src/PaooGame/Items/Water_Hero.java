package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.GameOverState;
import PaooGame.States.State;
import PaooGame.Timer;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Water_Hero extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private boolean isJumping = false;
    private Timer jumpingTimer;
    private double jumpSpeed;
    private int jumpDirection; /* -1 = up, 1 = down */

    private ArrayList<CollisionItem> lista_static_element;//lista pt podele
    private ArrayList<CollisionItem> lista_dinamic_element;//lista pt "stele"
    private ArrayList<CollisionItem> list_fire;//lista pt "stele"

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Water_Hero(RefLinks refLink, float x, float y)
    {
        ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        ///Seteaza imaginea de start a eroului
        image = Assets.heroLeft;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;

        jumpingTimer = new Timer();

        lista_dinamic_element = new ArrayList<CollisionItem>();

        lista_static_element = new ArrayList<CollisionItem>();
        list_fire = new ArrayList<CollisionItem>();
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        double nextY;
        if(isJumping) {
            if(jumpSpeed > 1 && jumpDirection == -1) {
                jumpDirection = 1;
                jumpSpeed = 0;
            }
            else if(jumpDirection == 1) {
                if(jumpSpeed < 1) {
                    jumpSpeed += 0.04;
                }
            } else {
                jumpSpeed += 0.04;
            }
            nextY = y + jumpDirection * GetSpeed(jumpSpeed) * 4;

            lista_static_element = refLink.GetMap().getList_static_element();

            for(int i = 0; i< lista_static_element.size(); i++){
                if(lista_static_element.get(i).GetRectangle().intersects(x , nextY , width , height)){
                    isJumping = false;
                    return;
                    /*lista_static_element.remove(lista_static_element.get(i));
                    refLink.GetMap().setList_dinamic_element(lista_static_element);*/
                }

            }


            lista_dinamic_element = refLink.GetMap().getList_star_blue();

            for(int i = 0; i< lista_dinamic_element.size(); i++){
                if(lista_dinamic_element.get(i).GetRectangle().intersects(x , nextY , width , height)){
                    lista_dinamic_element.remove(lista_dinamic_element.get(i));
                    refLink.GetMap().setList_star_blue(lista_dinamic_element);

                    Scor.GetInstance().Update_Scor();

                    //State.SetState(new GameOverState(refLink));
                }
            }

            list_fire = refLink.GetMap().getList_fire();
            for(int i = 0; i< list_fire.size(); i++){
                if(list_fire.get(i).GetRectangle().intersects(x , nextY , width , height)){
                    list_fire.remove(list_fire.get(i));
                    refLink.GetMap().setList_star_blue(list_fire);

                    Scor.GetInstance().Update_Scor();

                    State.SetState(new GameOverState(refLink));
                }
            }



            /*if(refLink.GetMap().CheckCollision((int)x+50,(int)y)){
                isJumping = false;
                return;
            }*/

            y = (float)nextY;

            if(y > 415) {
                isJumping = false;
                y = 415;
            }
        }
        ///Verifica daca a fost apasata o tasta
        GetInput();
        ///Actualizeaza pozitia
        Move();
        ///Actualizeaza imaginea
        if(refLink.GetKeyManager().left_arrow)
        {
            image = Assets.water_hero_left;
        }
        else if(refLink.GetKeyManager().right_arrow) {
            image = Assets.water_hero_right;
        }
        else {
            image = Assets.water_hero;
        }
        if(lista_dinamic_element.isEmpty() )
            Map.level = 2;
    }

    private double GetSpeed(double number) {
        return 1 - Math.pow(1 - number, 3);
    }


    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up_arrow && !isJumping)
        {
            jumpDirection = -1;
            isJumping = true;
            jumpSpeed = 0;
        }
        /*
            ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }*/
        ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left_arrow && x + xMove > 15)
        {
            xMove = -speed;
        }
        ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right_arrow && x + xMove < 900)
        {
            xMove = speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);


        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}

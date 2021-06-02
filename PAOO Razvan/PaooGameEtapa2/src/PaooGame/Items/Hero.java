package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.ArrayList;

import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.GameOverState;
import PaooGame.States.NextLevelState;
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
public class Hero extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private boolean isJumping = false;
    private Timer jumpingTimer;
    private double jumpSpeed;
    private int jumpDirection; /* -1 = up, 1 = down */
    private boolean isIntersection = false;
    private float gravity;
    private boolean static_intersection = false;

   /* private float velocity;
    private float accelartion;

    private float inaltime;
    private float ypos;
    private boolean isDead;
*/


    private ArrayList<CollisionItem> lista_static_element;//lista pt podele
    private ArrayList<CollisionItem> list_next_level;//lista pt podele
    private ArrayList<CollisionItem> list_star;//lista pt "stele"
    private ArrayList<CollisionItem> list_fire;//lista pt fire


    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
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

        list_star = new ArrayList<CollisionItem>();

        list_fire = new ArrayList<CollisionItem>();

        lista_static_element = new ArrayList<CollisionItem>();

        list_next_level = new ArrayList<CollisionItem>();

        gravity = 0;

    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */

    private void Gravity(){
        if(static_intersection){
            return;
        }

        y += 1;
    }



    @Override
    public void Update()
    {
        double nextY;

        lista_static_element = refLink.GetMap().getList_static_element();
        list_star = refLink.GetMap().getList_star();
        list_next_level = refLink.GetMap().getList_next_level();

        if(isJumping)
        {
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


                //coliziune obiecte statice

                for(int i = 0; i< lista_static_element.size(); i++){
                    if((lista_static_element.get(i).GetRectangle().intersects((int)x + 50 , (int)nextY, width , height) ||
                        lista_static_element.get(i).GetRectangle().intersects((int)xMove ,(int) yMove , width , height) )

                    ){

                        isJumping = false;
                        static_intersection = true;

                        return;
                        /*lista_static_element.remove(lista_static_element.get(i));
                        refLink.GetMap().setList_dinamic_element(lista_static_element);*/
                    }

                }

                //intersectie nivel urmator

                for(int i = 0; i< list_next_level.size(); i++){
                    if((list_next_level.get(i).GetRectangle().intersects((int)x + 50 , (int)nextY, width , height) ||
                            list_next_level.get(i).GetRectangle().intersects((int)xMove ,(int) yMove , width , height) ) &&
                            Scor.GetInstance().getScor() == 2

                    ){
                        State.SetState(new NextLevelState(refLink));
                        Map_level.GetInstance().IncrementLevel();
                        System.out.println(Map_level.GetInstance().getLevel());

                        if(Map_level.GetInstance().getLevel() >2 ){
                            State.SetState(new GameOverState(refLink));
                        }

                        return;

                    }

                }








                //coliziune diamant
                for(int i = 0; i< list_star.size(); i++){
                    if(list_star.get(i).GetRectangle().intersects((int)x ,(int) nextY , width , height)){
                        list_star.remove(list_star.get(i));
                        refLink.GetMap().setList_star(list_star);

                        Scor.GetInstance().Update_Scor();
                    }

                }

                list_fire = refLink.GetMap().getList_fire();
                for(int i = 0; i<list_fire.size() ; i++){
                    if(list_fire.get(i).GetRectangle().intersects(x  ,(int) nextY , width,height) ||
                        list_fire.get(i).GetRectangle().intersects(x,yMove , width, height)
                    ){
                        isIntersection = true;
                        //State.SetState(new GameOverState(refLink));
                    }
                }


                /*if(refLink.GetMap().CheckCollision((int)x+50,(int)y)){
                    isJumping = false;
                    return;
                }*/

                y = (float)nextY;

                //y += 0.5;



                if(y > 415 ) {
                    isJumping = false;
                    y = 415;
                }

            }
        /*else{
            for(int i = 0; i< lista_static_element.size(); i++){
                if(!(lista_static_element.get(i).GetRectangle().intersects(x , y + height + 5, width , height) ||
                        lista_static_element.get(i).GetRectangle().intersects(xMove , yMove , width , height) )

                ){

                    isJumping = true;
                    jumpDirection = 1;
                    jumpSpeed = 0;
                    static_intersection = true;

                    return;
                        /*lista_static_element.remove(lista_static_element.get(i));
                        refLink.GetMap().setList_dinamic_element(lista_static_element);
                }

            }
        }
        */

            ///Verifica daca a fost apasata o tasta
        GetInput();
            ///Actualizeaza pozitia
        Move();
            ///Actualizeaza imaginea
        if(refLink.GetKeyManager().left)
        {
            image = Assets.heroLeft;
        }
        else if(refLink.GetKeyManager().right) {
            image = Assets.heroRight;
        }
        else {
            image = Assets.hero;
        }
        //Gravity();

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
        //isJumping = true;
            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up && !isJumping)
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
        else if(refLink.GetKeyManager().left && x + xMove > 15)
        {
            xMove = -speed;
        }
            ///Verificare apasare tasta "dreapta"
        else if(refLink.GetKeyManager().right && x + xMove < 900)
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

    public int getScor(){
        return Scor.GetInstance().getScor();
    }
}
